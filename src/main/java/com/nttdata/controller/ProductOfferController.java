package com.nttdata.controller;

import com.nttdata.domain.dao.ProductOffer;
import com.nttdata.domain.request.BuyBootCoinRequest;
import com.nttdata.domain.request.ProductOfferRequest;
import com.nttdata.domain.response.BuyBootCoinResponse;
import com.nttdata.domain.response.ProductOfferResponse;
import com.nttdata.service.ProductOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/productoffers")
@RestController
public class ProductOfferController {

    private final ProductOfferService productOfferService;
    public ProductOfferController(ProductOfferService productOfferService){
        this.productOfferService=productOfferService;

    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductOfferResponse> bootCointOffer(@RequestBody ProductOfferRequest productOfferRequest){
        return productOfferService.addOffer(productOfferRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductOffer> getAllOffer(){
       return Flux.fromIterable(productOfferService.getAllOffer());
    }

    @PostMapping(path = "/buy")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BuyBootCoinResponse> buyBootCoin(@RequestBody BuyBootCoinRequest request){
      return Mono.just(productOfferService.buyBootCoins(request));
    }



}
