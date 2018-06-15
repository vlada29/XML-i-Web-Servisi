package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.model.Agent;
import com.model.Message;
import com.model.User;
import com.model.dto.RegistrationUserDto;
import com.model.dto.LoginUserDto;
import com.model.dto.MessageDto;
import com.repositories.AgentRepository;
import com.repositories.MessageRepository;
import com.repositories.UserRepository;
import com.services.UserService;

import org.springframework.ui.Model;


@RestController 
@CrossOrigin(origins="http://localhost:4213")
public class UserController {
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	AgentRepository agentRep;
	
	@Autowired
	MessageRepository messRep;
	
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
	public ResponseEntity<?> userLogin(
			@RequestBody LoginUserDto loginUserDTO,
			HttpServletRequest request) {
		
		System.out.println( loginUserDTO  + " ovo je user sa logina");
		
		User logged = userService.findUser(loginUserDTO);
		System.out.println(logged);
		if (logged!=null) {
			userService.setCurrentUser(logged);
			return new ResponseEntity(logged, HttpStatus.OK);
			
		}

		return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		
	}
	
	
	
	@RequestMapping(value = "/getLoggedUserById", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getLoggedUserById(
			@RequestParam(value="id") String id) {
		
		Long hjid = Long.parseLong(id);
		
		User u = userService.getLoggedUserById(hjid);
		if (u!=null)
			return new ResponseEntity(u, HttpStatus.OK);
		
		return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		
		
	}
	
// za security - ne radi	
//    @RequestMapping(
//            value = "/getLoggedUser", method = RequestMethod.GET, produces="application/json")
//    public ResponseEntity<?> getLoggedUser() {
//
//        LoginUserDto userDTO = new LoginUserDto();
//        if (userService.getCurrentUser() != null) {
//        	User u = userService.getCurrentUser();
//            userDTO.setUsername(u.getUsername());
//            userDTO.setPassword(u.getPassword());
//            return new ResponseEntity<>(userDTO, HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sendMessage(
			@RequestBody MessageDto messageDto) {
		
		System.out.println( messageDto);
		Message m = new Message();
		User u = userRep.findByUsername(messageDto.getUser());
		m.setUser(u);
		Agent a = agentRep.findByUsername(messageDto.getAgent());
		m.setAgent(a);
		m.setContent(messageDto.getContent());
		m.setSenderType("user");

		try {
			messRep.save(m);
			return new ResponseEntity(HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
			
		}

	}
	
	@RequestMapping(value = "/getSentMessages", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getSentMessages(
			@RequestParam(value="idUser") String idUser) {
		
		Long hjid = Long.parseLong(idUser);
		
		List<Message> poruke = messRep.findAll();
		if (poruke==null) {
			return new ResponseEntity(null, HttpStatus.OK); 
		}
		System.out.println(poruke);
		List<Message> poslatePorukeUsera = new ArrayList<>();
		for (Message m: poruke) {
			if (m.getUser().getHjid()==hjid && m.getSenderType().equals("user")) {
				poslatePorukeUsera.add(m);
			}
		}
		System.out.println(poslatePorukeUsera);
		return new ResponseEntity(poslatePorukeUsera, HttpStatus.OK); 
		
		
	}
	
	@RequestMapping(value = "/getReceivedMessages", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getReceivedMessages(
			@RequestParam(value="idUser") String idUser) {
		
		Long hjid = Long.parseLong(idUser);
		
		List<Message> poruke = messRep.findAll();
		if (poruke==null) {
			return new ResponseEntity(null, HttpStatus.OK); 
		}
		List<Message> primljenePorukeUsera = new ArrayList<>();
		for (Message m: poruke) {
			if (m.getUser().getHjid()==hjid && m.getSenderType().equals("agent")) {
				primljenePorukeUsera.add(m);
			}
		}

		return new ResponseEntity(primljenePorukeUsera, HttpStatus.OK); 
		
		
	}
	
	
	
	
	
	
}
