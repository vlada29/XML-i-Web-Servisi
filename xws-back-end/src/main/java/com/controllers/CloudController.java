package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.model.Admin;
import com.model.Komentar;
import com.model.Message;
import com.model.Ocena;
import com.repositories.AdminRepository;
import com.services.CloudService;

@RestController
@CrossOrigin
public class CloudController {
	
	@RequestMapping(value = "/getOcena", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getOcena(
			@RequestParam(value="id") String id) {
		
		Long hjid = Long.parseLong(id);
		
		int brojac = 0;
		int suma = 0;
		int ocena = 0;
		CloudService cloudService = new CloudService();
		
		for (Ocena o: cloudService.getOceneArrayForSmestaj(hjid)) {
			System.out.println(o.getOcena()+", "+hjid);
				suma = suma + o.getOcena();
				brojac++;
			
		}
		if (brojac!=0) {
			ocena = suma/brojac;
		}
		
		return new ResponseEntity(ocena, HttpStatus.OK); 
		
	
		
		
	}
	
	@RequestMapping(value = "/getCom", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getKomentari(
			@RequestParam(value="id") String id) {
		
		Long hjid = Long.parseLong(id);
		CloudService cs = new CloudService();
		System.out.println("getCom"+id);
		
		List<Komentar> komentari = cs.getKomentariArrayForSmestaj(hjid);
		System.out.println(komentari);

		return new ResponseEntity(komentari, HttpStatus.OK); 
		
		
	}
	
	
	
	

}
