package com.nttdata.domain.dao;

import com.nttdata.util.PayMode;
import com.nttdata.util.ProductState;
import com.nttdata.util.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class ProductOffer extends AbstractDocument{

    private ProductState productState;
    private PayMode payMode;
    private TypeOperation typeOperation;
    private Long amountBootcoin;
    private BigDecimal total=new BigDecimal(1);
}
