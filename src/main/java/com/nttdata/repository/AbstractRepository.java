package com.nttdata.repository;

import com.nttdata.domain.dao.AbstractDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbstractRepository<T extends AbstractDocument> extends MongoRepository<T, String> {
}
