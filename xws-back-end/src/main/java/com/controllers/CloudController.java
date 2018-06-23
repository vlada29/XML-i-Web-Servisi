package com.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;

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
import com.model.SmestajnaJedinica;
import com.model.dto.SearchDto;
import com.repositories.AdminRepository;
import com.repositories.SmestajnaJedinicaRepository;
import com.services.CloudService;

@RestController
@CrossOrigin
public class CloudController {
	
	@Autowired
	SmestajnaJedinicaRepository smJedRep;
	
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
	
	@RequestMapping(value = "/greater", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortOcenaGreater(
			@RequestBody List<SmestajnaJedinica> smestJedinice, @RequestParam(value="broj") String broj,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);
			List<SmestajnaJedinica> smestJediniceAfter = new ArrayList<>();
			for (SmestajnaJedinica sj: smestJedinice) {
				int brojac = 0;
				int suma = 0;
				int ocena = 0;
				CloudService cloudService = new CloudService();
				
				for (Ocena o: cloudService.getOceneArrayForSmestaj(sj.getHjid())) {
					System.out.println(o.getOcena());
						suma = suma + o.getOcena();
						brojac++;
					
				}
				if (brojac!=0) {
					ocena = suma/brojac;
					sj.setTrenutnaOcena(ocena);
					smJedRep.save(sj);
				}
				
				if (sj.getTrenutnaOcena()>Double.parseDouble(broj)) {
					smestJediniceAfter.add(sj);
				}
				
			}
			
		

			System.out.println(smestJediniceAfter);
			return new ResponseEntity(smestJediniceAfter, HttpStatus.OK);

			
	}
	
	@RequestMapping(value = "/less", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortOcenaSmaller(
			@RequestBody List<SmestajnaJedinica> smestJedinice, @RequestParam(value="broj") String broj,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);
			List<SmestajnaJedinica> smestJediniceAfter = new ArrayList<>();
			for (SmestajnaJedinica sj: smestJedinice) {
				int brojac = 0;
				int suma = 0;
				int ocena = 0;
				CloudService cloudService = new CloudService();
				
				for (Ocena o: cloudService.getOceneArrayForSmestaj(sj.getHjid())) {
					System.out.println(o.getOcena());
						suma = suma + o.getOcena();
						brojac++;
					
				}
				if (brojac!=0) {
					ocena = suma/brojac;
					sj.setTrenutnaOcena(ocena);
					smJedRep.save(sj);
				}
				
				if (sj.getTrenutnaOcena()<Double.parseDouble(broj)) {
					smestJediniceAfter.add(sj);
				}
				
			}
			
		

			System.out.println(smestJediniceAfter);
			return new ResponseEntity(smestJediniceAfter, HttpStatus.OK);

			
	}
	
	@RequestMapping(value = "/equal", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortOcenaEqual(
			@RequestBody List<SmestajnaJedinica> smestJedinice, @RequestParam(value="broj") String broj,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);
			List<SmestajnaJedinica> smestJediniceAfter = new ArrayList<>();
			for (SmestajnaJedinica sj: smestJedinice) {
				int brojac = 0;
				int suma = 0;
				int ocena = 0;
				CloudService cloudService = new CloudService();
				
				for (Ocena o: cloudService.getOceneArrayForSmestaj(sj.getHjid())) {
					System.out.println(o.getOcena());
						suma = suma + o.getOcena();
						brojac++;
					
				}
				if (brojac!=0) {
					ocena = suma/brojac;
					sj.setTrenutnaOcena(ocena);
					smJedRep.save(sj);
				}
				
				if (sj.getTrenutnaOcena()==Double.parseDouble(broj)) {
					smestJediniceAfter.add(sj);
				}
				
			}
			
		

			System.out.println(smestJediniceAfter);
			return new ResponseEntity(smestJediniceAfter, HttpStatus.OK);

			
	}
	
	
	
	

}
