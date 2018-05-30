package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.repositories.RezervacijaRepository;
import com.services.RezService;

@Service
public class RezServiceImpl implements RezService {
	
	@Autowired
	private RezervacijaRepository rezRepository;
	
	@Override
	public Rezervacija findOne(Long id) {
		return null;
	}

	@Override
	public List<Rezervacija> findAll() {
		return rezRepository.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija t) {
		return rezRepository.save(t);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmArrival(Long hjid) {
		rezRepository.confirmArrival(hjid);	
	}

}
