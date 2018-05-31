package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;

@Repository
@Transactional
public interface AvailableRepository extends MongoRepository<ZauzetostJedinice, Long>{

}
