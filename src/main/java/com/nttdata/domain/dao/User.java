package com.nttdata.domain.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

@Document
public class User extends AbstractDocument{
    private Long dni;
    private Long phoneNumber;
    private String email;
    private List<String> productOfferId;
    public User(){
        productOfferId=new ArrayList<>();
    }

}
