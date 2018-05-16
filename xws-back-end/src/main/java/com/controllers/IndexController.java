package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repositories.AdminRepository;


@RestController
public class IndexController implements ErrorController {
	
	@Autowired
	AdminRepository adminRepository;
	
	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
	@GetMapping("/index")
	public String sayHello(){
		System.out.println("Rest controller working.");
		System.out.println(adminRepository.findOneById((long) 1));
		return "Welcome";
	}
}
