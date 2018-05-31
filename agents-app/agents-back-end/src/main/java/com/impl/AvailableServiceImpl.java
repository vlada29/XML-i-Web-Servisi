package com.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.ZauzetostJedinice;
import com.repositories.AvailableRepository;
import com.services.AvailableService;
@Service
public class AvailableServiceImpl implements AvailableService {
	@Autowired
	AvailableRepository avaRepo;
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
