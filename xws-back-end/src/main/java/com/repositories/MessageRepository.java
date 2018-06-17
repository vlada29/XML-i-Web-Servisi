package com.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Message;
import com.model.SmestajnaJedinica;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long>{
	List<Message> findByAgentHjid(Long hjid);
	List<Message> findByUserHjid(Long hjid);
	Message findByHjid(Long hjid);
}
