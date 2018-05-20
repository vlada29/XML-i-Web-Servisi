package com.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.model.Agent;


@Service
public interface AgentService {
	Agent findOne(Long id);
	Agent findByUsername(String username);
	List<Agent> findAll();
	Agent save(Agent t);
	void remove(Long id) throws IllegalArgumentException;
}	
