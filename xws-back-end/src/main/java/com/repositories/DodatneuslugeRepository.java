package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.DodatneUsluge;

@Repository
@Transactional
public interface DodatneuslugeRepository extends JpaRepository<DodatneUsluge, Long> {
	
	ArrayList<DodatneUsluge> findAll();
	DodatneUsluge findOneByHjid(Long id);
	void delete(DodatneUsluge entity);
	
}
