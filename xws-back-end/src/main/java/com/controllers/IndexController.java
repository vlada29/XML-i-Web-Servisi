package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@GetMapping("/index")
	public String sayHello(){
		System.out.println("Rest controller working.");
		return "Welcome";
	}
}
