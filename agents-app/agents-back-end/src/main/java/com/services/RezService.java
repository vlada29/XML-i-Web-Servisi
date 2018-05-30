package com.services;

import java.util.List;

import com.model.Rezervacija;

public interface RezService {
	Rezervacija findOne(Long id);
	List<Rezervacija> findAll();
	Rezervacija save(Rezervacija t);
	void remove(Long id) throws IllegalArgumentException;
	void deleteAll();
	public List<Rezervacija> findByAgentUsername(String username);
}
