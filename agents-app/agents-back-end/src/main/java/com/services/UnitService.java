package com.services;

import java.util.List;

import com.model.SmestajnaJedinica;

public interface UnitService {
	SmestajnaJedinica findOne(Long id);
	List<SmestajnaJedinica> findAll();
	SmestajnaJedinica save(SmestajnaJedinica t);
	void remove(Long id) throws IllegalArgumentException;
	void deleteAll();
	public List<SmestajnaJedinica> findByAgentUsername(String username);
	public Long deleteSmestajnaJedinicaByHjid(Long hjid);
	public SmestajnaJedinica findOneByHjid(Long hjid);
}
