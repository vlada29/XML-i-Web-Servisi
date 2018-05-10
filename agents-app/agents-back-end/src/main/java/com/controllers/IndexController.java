package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@GetMapping("/agentindex")
	public String sayHello(){
		System.out.println("Agent Rest controller working.");
		return "Welcome Agent";
	}
}
