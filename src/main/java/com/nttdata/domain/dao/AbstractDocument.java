package com.nttdata.domain.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AbstractDocument implements Serializable {
    @Id
    String id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
   // private int state;
    public AbstractDocument(){
        createdAt= LocalDateTime.now();
        modifiedAt= LocalDateTime.now();
      //  state=1;
    }
}
