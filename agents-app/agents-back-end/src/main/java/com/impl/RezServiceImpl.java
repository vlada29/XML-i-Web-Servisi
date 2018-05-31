package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.repositories.RezRepository;
import com.services.RezService;

@Service
public class RezServiceImpl implements RezService{
	
	@Autowired
	RezRepository rezRepo;
		
	@Override
	public Rezervacija findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rezervacija> findAll() {
		return rezRepo.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija r) {
		return rezRepo.save(r);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		rezRepo.deleteAll();
		
	}

	@Override
	public List<Rezervacija> findByAgentUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
