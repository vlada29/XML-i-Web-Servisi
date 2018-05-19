package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.model.User;
import com.repositories.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRep;
	
	@GetMapping("/getAllUsers")
	public String getAllUsers() {
		String ret = "[";
		ArrayList<User> a = userRep.findAll();
		Gson g = new Gson();
		for(int i=0;i<a.size();i++) {
			if(i!=0) {
				ret+=",";
			}
			ret+=g.toJson(a.get(i));
		}
		ret +="]";	
		return ret;
	}
	
	@RequestMapping(
			value = "/removeUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void removeUser(@RequestBody User user, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			userRep.delete(user);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/activateUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void activateUser(@RequestBody User user, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			user.setActive(true);
			userRep.save(user);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/blockUser",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value=HttpStatus.OK)
	public void blockUser(@RequestBody User user, HttpServletResponse response,HttpSession session) throws IOException {
		try {
			user.setActive(false);
			userRep.save(user);
		}catch(Exception e) {
			response.sendError(Response.SC_FORBIDDEN);
		}
	}
	
	
}
