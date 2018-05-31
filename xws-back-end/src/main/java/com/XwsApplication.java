package com;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
=======
import com.impl.ImageWebServiceImpl;
import com.model.Agent;
import com.soapservices.ImageWebService;
>>>>>>> 714b30f6d1154549d75477d3b92f83db05d8071f

@SpringBootApplication
public class XwsApplication implements CommandLineRunner {

	public static void main(String[] args) throws JAXBException {
		
		SpringApplication.run(XwsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
