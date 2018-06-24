package com;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.model.Agent;
import com.repositories.CategoryRepository;
import com.repositories.ExtrasRepository;
import com.repositories.TypesRepository;
import com.services.AgentService;
import com.services.MessageService;
import com.services.RezService;
import com.services.StorageService;
import com.services.UnitService;
import com.soapservices.CloudSynchronisationService;
//@EnableWebMvc
@SpringBootApplication
public class AgentsBackEndApplication implements CommandLineRunner{
	
	@Autowired
	CloudSynchronisationService syncService;
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	AgentService aService;
	
	@Autowired
	RezService rezService;
	
	@Resource
	StorageService storageService;
	
	@Autowired
	MessageService messService;
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	ExtrasRepository extrasRepo;
	
	@Autowired
	TypesRepository typesRepo;
	
	public static void main(String[] args) throws JAXBException {
		
		SpringApplication.run(AgentsBackEndApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
		
	}
}
