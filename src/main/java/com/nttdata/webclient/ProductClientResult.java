package com.nttdata.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public interface ProductClientResult {
    WebClient retrievePersonResult();
    WebClient retrieveProductResult();
}
