package com.nttdata.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T> {
    List<T>findAll();
    Optional<T> create(T t);
    Optional<T> update(T t);
    void delete(T t);
}
