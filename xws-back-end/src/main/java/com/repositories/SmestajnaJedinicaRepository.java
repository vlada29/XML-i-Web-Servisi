package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;

@Repository
@Transactional
public interface SmestajnaJedinicaRepository extends JpaRepository<SmestajnaJedinica,Long>{
	
	ArrayList<SmestajnaJedinica> findAll();
	SmestajnaJedinica findOneByHjid(Long id);
	void delete(SmestajnaJedinica entity);
	
	 
}
