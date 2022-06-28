package com.nttdata.webclient.dao;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponsePrice {

    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
}
