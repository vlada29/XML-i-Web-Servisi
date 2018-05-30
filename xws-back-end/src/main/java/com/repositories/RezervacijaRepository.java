package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Rezervacija;
import com.model.TipSmestaja;

@Repository
@Transactional
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long>{
	@Modifying
	@Query("update Rezervacija r set r.realizovana = true where r.hjid = ?1")
	void confirmArrival(Long hjid);
}

