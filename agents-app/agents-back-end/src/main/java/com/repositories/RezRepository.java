package com.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Rezervacija;
import com.model.SmestajnaJedinica;

@Repository
@Transactional
public interface RezRepository extends MongoRepository<Rezervacija, Long>{
	//@Query(value = "{ 'agent.username' : ?0 }")
	//public List<SmestajnaJedinica> findByAgentUsername(String username);


}