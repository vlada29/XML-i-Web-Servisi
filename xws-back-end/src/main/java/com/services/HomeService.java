package com.services;

import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.datatype.DatatypeConfigurationException;

import com.model.SmestajnaJedinica;
import com.model.dto.SearchDto;

public interface HomeService {
	
	public ArrayList<SmestajnaJedinica> findSearch(SearchDto searchDto) throws ParseException, DatatypeConfigurationException;
	

}