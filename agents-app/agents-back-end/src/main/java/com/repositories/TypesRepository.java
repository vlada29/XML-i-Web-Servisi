package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Agent;
import com.model.TipSmestaja;

@Repository
@Transactional
public interface TypesRepository extends MongoRepository<TipSmestaja, Long>{

}
