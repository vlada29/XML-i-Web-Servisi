package com.controllers;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.SmestajnaJedinica;
import com.model.User;
import com.model.dto.LoginUserDto;
import com.model.dto.SearchDto;
import com.services.HomeService;



@RestController 
@CrossOrigin
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> search(
			@RequestBody SearchDto searchDto,
			HttpServletRequest request) throws ParseException, DatatypeConfigurationException {
		
			System.out.println(searchDto);
			ArrayList<SmestajnaJedinica> smestajneJedinice = homeService.findSearch(searchDto);
			if (smestajneJedinice!=null) {
				return new ResponseEntity(smestajneJedinice, HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);	
			}
			
		

			
			
		}

	

	
}
