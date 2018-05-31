package com.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.SmestajnaJedinica;


public interface UnitService {
	SmestajnaJedinica findOne(Long id);
	List<SmestajnaJedinica> findAll();
	SmestajnaJedinica save(SmestajnaJedinica t);
	void remove(Long id) throws IllegalArgumentException;
	public SmestajnaJedinica findByHjid(Long hjid);
}
