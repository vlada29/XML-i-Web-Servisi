package com.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repositories.AgentRepository;
import com.services.AgentService;
import com.model.Agent;

@Service
public class AgentServiceImpl implements AgentService{
	@Autowired
	private AgentRepository agentRepository;

	@Override
	public Agent findOne(Long id) {
		Optional<Agent> agent = agentRepository.findById(id);
		if(agent.isPresent()) {
			return agent.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Agent> findAll() {
		return agentRepository.findAll();
	}

	@Override
	public Agent save(Agent t) {
		return agentRepository.save(t);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Agent findByUsername(String username) {
		return agentRepository.findByUsername(username);
	}
}
