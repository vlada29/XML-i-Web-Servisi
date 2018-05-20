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
	Agent findByHjid(Long id);
	Agent findByUsername(String username);
	void delete(Agent entity);
	
}
