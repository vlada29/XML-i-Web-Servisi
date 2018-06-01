package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Agent;
import com.model.KategorijaSmestaja;

@Repository
@Transactional
public interface CategoryRepository extends MongoRepository<KategorijaSmestaja, Long>{

}
