package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Agent;
import com.repositories.AgentRepository;
import com.services.AgentService;

@Service
public class AgentServiceImpl implements AgentService{
	@Autowired
	AgentRepository agentRepo;
	
	@Override
	public Agent findOne(Long id) {
		return agentRepo.findByHjid(id);
	}

	@Override
	public List<Agent> findAll() {
		return agentRepo.findAll();
	}

	@Override
	public Agent save(Agent a) {
		return agentRepo.save(a);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		agentRepo.deleteAll();
		
	}

	@Override
	public Agent findByUsername(String username) {
		return agentRepo.findByUsername(username);
	}

}
