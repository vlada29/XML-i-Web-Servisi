package com.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	
	ArrayList<User> findAll();
	User findOneByHjid(Long id);
	void delete(User entity);
	
}
