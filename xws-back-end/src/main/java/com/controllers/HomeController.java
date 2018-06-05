package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.model.dto.LoginUserDto;
import com.model.dto.SearchDto;



@RestController 
@CrossOrigin
public class HomeController {
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> search(
			@RequestBody SearchDto searchDto,
			HttpServletRequest request) {
		
			System.out.println( searchDto);
		

			return new ResponseEntity(HttpStatus.OK);
			
		}

	

	
}
