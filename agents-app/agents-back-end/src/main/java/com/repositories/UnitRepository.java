package com.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Agent;
import com.model.SmestajnaJedinica;
@Repository
@Transactional
public interface UnitRepository extends MongoRepository<SmestajnaJedinica, Long>{
	@Query(value = "{ 'agent.username' : ?0 }")
	public List<SmestajnaJedinica> findByAgentUsername(String username);
	public Long deleteSmestajnaJedinicaByHjid(Long hjid);
	public SmestajnaJedinica findOneByHjid(Long hjid);
}
