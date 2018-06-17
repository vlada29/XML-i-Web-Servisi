package com.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.model.PlanCena;

@Repository
@Transactional
public interface PlanCenaRepository extends JpaRepository<PlanCena, Long> {
	

}
