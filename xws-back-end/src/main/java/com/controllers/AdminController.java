package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.Admin;
import com.repositories.AdminRepository;

@RestController
public class AdminController {
	
	@Autowired
	AdminRepository adminRep;
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void snimiTip(@RequestBody Admin admin, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			System.out.println("admin pre trazenja "+admin.getFirstName() +" "+ admin.getLastName() + " "+admin.getUsername());
			ArrayList<Admin> a = adminRep.findByFirstNameAndLastName(admin.getUsername(), admin.getPassword());
			if(a.size() > 0) {
				System.out.println(a.get(0).getFirstName() +" "+a.get(0).getLastName());
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
}
