package com.nttdata.service;

import com.nttdata.domain.dao.ProductOffer;
import com.nttdata.domain.request.BuyBootCoinRequest;
import com.nttdata.domain.request.ProductOfferRequest;
import com.nttdata.domain.response.BuyBootCoinResponse;
import com.nttdata.domain.response.ProductOfferResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductOfferService extends AbstractService<ProductOffer>{


    Mono<ProductOfferResponse> addOffer(ProductOfferRequest request);

    List<ProductOffer> getAllOffer();
    BuyBootCoinResponse buyBootCoins(BuyBootCoinRequest request);
}
