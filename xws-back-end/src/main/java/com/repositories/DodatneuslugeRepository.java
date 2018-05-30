package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.DodatnaUsluga;

@Repository
@Transactional
public interface DodatneuslugeRepository extends JpaRepository<DodatnaUsluga, Long> {
	
	ArrayList<DodatnaUsluga> findAll();
	DodatnaUsluga findOneByHjid(Long id);
	void delete(DodatnaUsluga entity);
	
}
