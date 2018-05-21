package com.repositories;


import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Admin;



@Repository
@Transactional
public interface AdminRepository  extends JpaRepository<Admin, Long>{
	
	ArrayList<Admin> findAll();
	Admin findOneByHjid(Long id);
	@Query("from Admin where username like :username AND password like :password")
	ArrayList<Admin> findByFirstNameAndLastName(@Param("username")String username,@Param("password") String password);
}
