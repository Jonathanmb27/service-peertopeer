package com.nttdata.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOfferResponse {

   // private String offerId;
    private String message;
   // private String userId;
}
