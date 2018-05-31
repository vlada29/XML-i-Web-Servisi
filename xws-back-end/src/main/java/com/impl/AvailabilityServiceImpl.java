package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.ZauzetostJedinice;
import com.repositories.AvailabilityRepository;
import com.services.AvailabilityService;
@Service
public class AvailabilityServiceImpl implements AvailabilityService{
	
	@Autowired
	AvailabilityRepository avaRepo;
	
	@Override
	public ZauzetostJedinice findOne(Long id) {
		return null;
	}

	@Override
	public List<ZauzetostJedinice> findAll() {
		return avaRepo.findAll();
	}

	@Override
	public ZauzetostJedinice save(ZauzetostJedinice t) {
		return avaRepo.save(t);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		
		
	}

}
