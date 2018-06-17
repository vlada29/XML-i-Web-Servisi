package com.services;

import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.dto.AdvancedSearchDto;
import com.model.dto.SearchDto;

public interface HomeService {
	
	public ArrayList<SmestajnaJedinica> findSearch(SearchDto searchDto) throws ParseException, DatatypeConfigurationException;
	public boolean reserve(Long id, Long idUser, XMLGregorianCalendar d1, XMLGregorianCalendar d2);
	public ArrayList<SmestajnaJedinica> advancedSearch(AdvancedSearchDto asearchDto) throws ParseException, DatatypeConfigurationException;

}