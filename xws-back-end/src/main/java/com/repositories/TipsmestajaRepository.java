package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.TipSmestaja;

@Repository
@Transactional
public interface TipsmestajaRepository extends JpaRepository<TipSmestaja,Long>{
	
	ArrayList<TipSmestaja> findAll();
	TipSmestaja findOneById(Long id);
	void delete(TipSmestaja entity);
}
