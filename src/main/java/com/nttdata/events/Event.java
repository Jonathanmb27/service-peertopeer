package com.nttdata.events;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Event <T>{
    private String id;
    private LocalDateTime createdAt;
    private T data;
}
