package com.nttdata.events;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyBootCoins {

    private String coinsId;
    private Long dni;
    private BigDecimal amount;

}
