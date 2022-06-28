package com.nttdata.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductClientResultImpl implements ProductClientResult{

    private final String clientHost;
    private final String productHost;
    public ProductClientResultImpl(@Value("${clientHost}")String clientHost,
                                   @Value("${productHost}")String productHost){
        this.clientHost=clientHost;
        this.productHost=productHost;
    }

    @Override
    public WebClient retrievePersonResult() {
        WebClient client=WebClient.builder()
                .baseUrl(clientHost)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client;
    }

    @Override
    public WebClient retrieveProductResult() {
        WebClient client=WebClient.builder()
                .baseUrl(productHost)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client;
    }
}
