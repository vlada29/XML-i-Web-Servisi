package com.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Message;
import com.model.SmestajnaJedinica;

@Repository
@Transactional
public interface MessageRepository extends MongoRepository<Message, Long>{
	public List<Message> findByAgentHjid(Long hjid);
}
