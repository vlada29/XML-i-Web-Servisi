package com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.model.Agent;

@SpringBootApplication
public class XwsApplication implements CommandLineRunner {

	public static void main(String[] args) throws JAXBException {
		
		SpringApplication.run(XwsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
