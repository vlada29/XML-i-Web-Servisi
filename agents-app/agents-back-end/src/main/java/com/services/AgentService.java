package com.services;

import java.util.List;

import com.model.Agent;
import com.model.SmestajnaJedinica;

public interface AgentService {
	Agent findOne(Long id);
	List<Agent> findAll();
	Agent save(Agent a);
	void remove(Long id) throws IllegalArgumentException;
	void deleteAll();
	Agent findByUsername(String username);
}
