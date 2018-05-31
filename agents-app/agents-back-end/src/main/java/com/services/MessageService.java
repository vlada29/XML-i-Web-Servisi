package com.services;

import java.util.List;

import com.model.Message;
import com.model.SmestajnaJedinica;

public interface MessageService {
	Message findOne(Long id);
	List<Message> findAll();
	Message save(Message t);
	void remove(Long id) throws IllegalArgumentException;
	void deleteAll();
	public List<Message> findByAgentHjid(Long hjid);
	
}
