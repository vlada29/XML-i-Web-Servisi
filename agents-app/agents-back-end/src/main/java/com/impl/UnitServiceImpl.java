package com.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.model.SmestajnaJedinica;
import com.repositories.UnitRepository;
import com.services.UnitService;

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
					"Category with id=%d does not exist.", id));
		}
		unitRepository.deleteById(id);
		
	}


}
