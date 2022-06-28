package com.nttdata.client.dao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nttdata.client.ClientResultDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ClientResultDeserializer.class)
public class ClientResult {
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;

}
