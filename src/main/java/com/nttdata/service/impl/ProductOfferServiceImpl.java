package com.nttdata.service.impl;

import com.nttdata.client.ClientResultClient;
import com.nttdata.client.dao.ClientResult;
import com.nttdata.domain.dao.ProductOffer;
import com.nttdata.domain.dao.User;
import com.nttdata.domain.request.BuyBootCoinRequest;
import com.nttdata.domain.request.ProductOfferRequest;
import com.nttdata.domain.response.BuyBootCoinResponse;
import com.nttdata.domain.response.ProductOfferResponse;
import com.nttdata.events.BuyBootCoins;
import com.nttdata.events.EventDispatcher;
import com.nttdata.repository.AbstractRepository;
import com.nttdata.repository.ProductOfferRepository;
import com.nttdata.service.ProductOfferService;
import com.nttdata.service.UserService;
import com.nttdata.util.PayMode;
import com.nttdata.util.ProductState;
import com.nttdata.util.TypeOperation;
import com.nttdata.webclient.ProductClientResult;
import com.nttdata.webclient.dao.ClientYanquiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductOfferServiceImpl extends AbstractServiceImpl<ProductOffer> implements ProductOfferService {
    private final Logger LOGGER= LoggerFactory.getLogger("ProductOfferServiceImpl");
    private final ProductOfferRepository productOfferRepository;
    private final ProductClientResult productClientResult;
    private final ClientResultClient client;
    private final UserService userService;
    private final EventDispatcher eventDispatcher;

    public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository,UserService userService,
                                   ProductClientResult productClientResult,ClientResultClient client,
                                   EventDispatcher eventDispatcher){
        this.productOfferRepository=productOfferRepository;
        this.userService=userService;
        this.productClientResult=productClientResult;
        this.client=client;
        this.eventDispatcher=eventDispatcher;
    }
    @Override
    AbstractRepository<ProductOffer> getRepo() {
        return productOfferRepository;
    }


    private Mono<ProductOfferResponse> verifyUser(ProductOfferRequest request) {
        ProductOfferResponse productOfferResponse=new ProductOfferResponse();
        Optional<ProductOffer> offer=convertToOffer(request);

        if(offer.isPresent()){

            addUser( request,offer.get().getId()).ifPresent(s->{
              s.getProductOfferId().add(offer.get().getId());
                userService.create(s);
            });
            productOfferResponse.setMessage("Successfully");


        }else{
            productOfferResponse.setMessage("Error");
        }
        return Mono.just(productOfferResponse);

    }

    /**
     * verificamos el precion del bootcoin para
     * mostrarlo
     * */
    @Override
    public List<ProductOffer> getAllOffer() {
      ClientResult result= client.retrieveResult();
       return productOfferRepository.findAll().stream().map(s->{
            if(s.getTypeOperation()==TypeOperation.VENTA)
                s.setTotal(result.getSalePrice().multiply(new BigDecimal(s.getAmountBootcoin())));
            else
                s.setTotal(result.getPurchasePrice().multiply(new BigDecimal(s.getAmountBootcoin())));
            return s;
        }).collect(Collectors.toList());
    }

    @Override
    public BuyBootCoinResponse buyBootCoins(BuyBootCoinRequest request) {

       Optional<ProductOffer> productOffer= productOfferRepository
               .findAll()
               .stream()
               .filter(s->s.getId().equals(request.getCoinsId())).findFirst();
       if(productOffer.isPresent()){
           BuyBootCoins coin=new BuyBootCoins();
           coin.setDni(request.getDni());
           coin.setAmount(request.getAmount());
           coin.setCoinsId(request.getCoinsId());
           eventDispatcher.publish(coin);

           ProductOffer pro=productOffer.get();
           pro.setAmountBootcoin(pro.getAmountBootcoin()-request.getAmount().longValue());
           productOfferRepository.save(pro);
           return new BuyBootCoinResponse("Transaccion generada");
       }else return new BuyBootCoinResponse("BootCoin no existe");

    }

    /**
     * Un usuario vendedor tiene que poseer un monedero móvil Yanki o
     * tener una cuenta bancaria de ahorro o corriente en el banco.
     * */
    @Override
    public Mono<ProductOfferResponse> addOffer (ProductOfferRequest request){
       if(request.getTypeOperation()==2){
           return   productClientResult.retrievePersonResult().get()
                   .uri("/persons/yanqui/{dni}",request.getDni())
                   .retrieve()
                   .bodyToMono(ClientYanquiResponse.class).flatMap(s->{
                       if (s.isExist()) {
                           return verifyUser(request);
                       }else {return Mono.just(new ProductOfferResponse("Error usurio debe de tener Yanqui"));}

                   });
       }else {
           return verifyUser(request);
       }

    }
    private Optional<ProductOffer> convertToOffer(ProductOfferRequest request){

        ProductOffer offer=new ProductOffer();
        offer.setProductState(ProductState.ACTIVO);
        if(request.getPayMode()==1)
             offer.setPayMode(PayMode.YANQUI);
        else offer.setPayMode(PayMode.TRANSFERENCIA);
        if(request.getTypeOperation()==1)
            offer.setTypeOperation(TypeOperation.COMPRA);
        else offer.setTypeOperation(TypeOperation.VENTA);
        offer.setAmountBootcoin(request.getAmountBootcoin());
        return Optional.of(productOfferRepository.save(offer));
    }
    private Optional<User> addUser(ProductOfferRequest request, String id){
        User user=new User();
        user.setDni(request.getDni());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
       Optional<User> userOptional= userService.findAll().stream().filter(s->s.getDni().equals(request.getDni())).findFirst();

        if (userOptional.isPresent()) return userOptional;
        else return userService.create(user);

    }
}
