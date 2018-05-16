package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;

@Repository
@Transactional
public interface AdminRepository  extends JpaRepository<Admin, Long>{
	
	ArrayList<Admin> findAll();
	Admin findOneById(Long id);

}
