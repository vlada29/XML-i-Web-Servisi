package com.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.model.Ocena;



@Repository
@Transactional
public interface OcenaRepository  extends JpaRepository<Ocena, Long>{
	
	
}
