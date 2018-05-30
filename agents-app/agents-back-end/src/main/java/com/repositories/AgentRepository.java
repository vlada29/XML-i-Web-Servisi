package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Agent;
import com.model.SmestajnaJedinica;

@Repository
@Transactional
public interface AgentRepository extends MongoRepository<Agent, Long>{
	Agent findByHjid(Long hjid);
	Agent findByUsername(String username);
}
