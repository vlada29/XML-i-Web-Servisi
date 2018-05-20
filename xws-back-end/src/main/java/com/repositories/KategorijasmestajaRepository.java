package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.KategorijaSmestaja;

@Repository
@Transactional
public interface KategorijasmestajaRepository extends JpaRepository<KategorijaSmestaja, Long> {
	
	ArrayList<KategorijaSmestaja> findAll();
	KategorijaSmestaja findOneByHjid(Long id);
	void delete(KategorijaSmestaja entity);
}
