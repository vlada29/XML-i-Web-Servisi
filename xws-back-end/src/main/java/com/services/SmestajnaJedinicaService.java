package com.services;

import java.util.ArrayList;

import com.model.SmestajnaJedinica;

public interface SmestajnaJedinicaService {
	
	public SmestajnaJedinica findOneByHjid(Long id);
	public ArrayList<SmestajnaJedinica> findAll();
}