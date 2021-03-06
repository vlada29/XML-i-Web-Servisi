package com.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.SmestajnaJedinica;
import com.repositories.UnitRepository;
import com.services.UnitService;
@Service
public class UnitServiceImpl implements UnitService{

	@Autowired
	private UnitRepository unitRepository;

	@Override
	public SmestajnaJedinica findOne(Long id) {
		Optional<SmestajnaJedinica> unit = unitRepository.findById(id);
		if(unit.isPresent()) {
			return unit.get();
		} else {
			return null;
		}
	}

	@Override
	public List<SmestajnaJedinica> findAll() {
		return unitRepository.findAll();
	}

	@Override
	public SmestajnaJedinica save(SmestajnaJedinica t) {
		return unitRepository.save(t);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Optional<SmestajnaJedinica> unit = unitRepository.findById(id);
		if (!unit.isPresent()) {
			throw new IllegalArgumentException(String.format(
					"Unit with id=%d does not exist.", id));
		}
		unitRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteAll(){
		unitRepository.deleteAll();
	}

	@Override
	public List<SmestajnaJedinica> findByAgentUsername(String username) {
		return unitRepository.findByAgentUsername(username);
	}

	@Override
	public Long deleteSmestajnaJedinicaByHjid(Long hjid) {
		return unitRepository.deleteSmestajnaJedinicaByHjid(hjid);
	}

	@Override
	public SmestajnaJedinica findOneByHjid(Long hjid) {
		return unitRepository.findOneByHjid(hjid);
	}
	
	
}
