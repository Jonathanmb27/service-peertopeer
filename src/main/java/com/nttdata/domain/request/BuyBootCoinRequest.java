package com.nttdata.domain.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyBootCoinRequest {
    private String coinsId;
    private Long dni;
    private BigDecimal amount;
}
