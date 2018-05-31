package com.services;

import java.util.List;

import com.model.Agent;
import com.model.Message;

public interface MessageService {
	Message findOne(Long id);
	List<Message> findAll();
	Message save(Message t);
	void remove(Long id) throws IllegalArgumentException;
	List<Message> findByAgentHjid(Long hjid);
	List<Message> findByUserHjid(Long hjid);
}
