package com.nttdata.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOfferRequest {
    private Long dni;
    private Long phoneNumber;
    private String email;
    /**
     * 1 active
     * 0 inactive
     * */
    private int productState;
    /**
     * 1 yanqui
     * 2 transferencia
     * */
    private int payMode;

    /**
     * 1 compra
     * 2 venta
     * */
    private int typeOperation;

    /**
     * amount of bootcoin
     * */
    private Long amountBootcoin;
}
