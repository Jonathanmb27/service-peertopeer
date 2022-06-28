package com.nttdata.service.impl;

import com.nttdata.domain.dao.AbstractDocument;
import com.nttdata.repository.AbstractRepository;
import com.nttdata.service.AbstractService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl<T extends AbstractDocument> implements AbstractService<T> {
    public List<T> findAll(){
        return  getRepo().findAll();
    }
    public Optional<T> create(T t){

        return Optional.of(getRepo().save(t));
    }
    public Optional<T> update(T t){
        return Optional.of(getRepo().save(t));
    }
    public void delete(T t){
        getRepo().delete(t);

    }

    abstract AbstractRepository<T> getRepo();
}
