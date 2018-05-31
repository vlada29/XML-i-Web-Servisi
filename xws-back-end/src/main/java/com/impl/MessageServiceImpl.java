package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Message;
import com.repositories.MessageRepository;
import com.services.MessageService;
@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messRepo;
	
	@Override
	public Message findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll() {
		return messRepo.findAll();
	}

	@Override
	public Message save(Message t) {
		return messRepo.save(t);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Message> findByAgentHjid(Long hjid) {
		return messRepo.findByAgentHjid(hjid);
	}

	@Override
	public List<Message> findByUserHjid(Long hjid) {
		return messRepo.findByUserHjid(hjid);
	}

}
