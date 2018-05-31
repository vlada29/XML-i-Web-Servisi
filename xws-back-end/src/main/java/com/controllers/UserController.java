package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.model.User;
import com.model.dto.RegistrationUserDto;
import com.model.dto.LoginUserDto;
import com.repositories.UserRepository;
import com.services.UserService;

import org.springframework.ui.Model;


@RestController 
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	UserService userService;

	
	@GetMapping(path="/getAllUsers")
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
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String registration(Model model) {
      //  model.addAttribute("userForm", new User());

        return "test";
    }

	@RequestMapping(value = "/registrationMessage", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity displayregistrationMessage(
			@RequestBody RegistrationUserDto registrationUserDto,
			HttpServletRequest request) {
		
		System.out.println(registrationUserDto  + " ovo je user dobijen DTO");
		
		
		if(userService.registerUser(registrationUserDto))
			return new ResponseEntity(HttpStatus.OK);
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		
		
	}
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity userLogin(
			@RequestBody LoginUserDto loginUserDTO,
			HttpServletRequest request) {
		
		System.out.println( loginUserDTO  + " ovo je user sa logina");
		
		User logged = userService.findUser(loginUserDTO);
		System.out.println(logged);
		if (logged!=null) {
			userService.setCurrentUser(logged);
			return new ResponseEntity(HttpStatus.OK);
			
		}

		return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		
	}
	
    @RequestMapping(
            value = "/getLoggedInUser", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<?> getLoggedInUser() {

        LoginUserDto userDTO = new LoginUserDto();
        if (userService.getCurrentUser() != null) {
        	User u = userService.getCurrentUser();
            userDTO.setUsername(u.getUsername());
            userDTO.setPassword(u.getPassword());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }
	
	
	
	
}
