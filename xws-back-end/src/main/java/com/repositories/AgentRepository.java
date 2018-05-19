package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Agent;

@Repository
@Transactional
public interface AgentRepository extends JpaRepository<Agent, Long> {

	ArrayList<Agent> findAll();
	Agent findOneById(Long id);
	void delete(Agent entity);
	
}
