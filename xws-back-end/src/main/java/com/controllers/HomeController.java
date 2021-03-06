package com.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.DodatnaUsluga;
import com.model.KategorijaSmestaja;
import com.model.Message;
import com.model.Ocena;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.User;
import com.model.ZauzetostJedinice;
import com.model.dto.AdvancedSearchDto;
import com.model.dto.DodatnaDto;
import com.model.dto.LoginUserDto;
import com.model.dto.SearchDto;
import com.repositories.AvailabilityRepository;
import com.repositories.DodatneuslugeRepository;
import com.repositories.KategorijasmestajaRepository;
import com.repositories.OcenaRepository;
import com.repositories.RezervacijaRepository;
import com.repositories.SmestajnaJedinicaRepository;
import com.repositories.TipsmestajaRepository;
import com.services.CloudService;
import com.services.HomeService;



@RestController 
@CrossOrigin
public class HomeController {
	
	@Autowired
	HomeService homeService;

	@Autowired
	RezervacijaRepository rezRep;
	
	@Autowired
	SmestajnaJedinicaRepository smJedRep;
	
	@Autowired
	AvailabilityRepository avRep;
	
	@Autowired
	KategorijasmestajaRepository katRep;
	
	@Autowired
	TipsmestajaRepository tipRep;
	
	@Autowired
	DodatneuslugeRepository dodUslRep;
	
	@Autowired
	OcenaRepository ocenaRep;
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> search(
			@RequestBody SearchDto searchDto,
			HttpServletRequest request) throws ParseException, DatatypeConfigurationException {
		
			System.out.println(searchDto);
			ArrayList<SmestajnaJedinica> smestajneJedinice = homeService.findSearch(searchDto);
			if (smestajneJedinice!=null) {
				return new ResponseEntity(smestajneJedinice, HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);	
			}
	
			
	}
	
	@RequestMapping(value = "/reserve", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> reserve(
			@RequestParam(value="id") String id,
			@RequestParam(value="idUser") String idUser,
			@RequestParam(value="od") String od,
			@RequestParam(value="do") String dodatum) throws DatatypeConfigurationException, ParseException {
		
		Long hjid = Long.parseLong(id);
		Long hjidUser = Long.parseLong(idUser);
		if (hjidUser==null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED); 
		}
		
	
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(od);

		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);

		XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
		
		Date date2 = format.parse(dodatum);

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
		
		System.out.println(id);
		System.out.println(idUser);
		
		System.out.println(xmlGregCal1);
		System.out.println(xmlGregCal2);
		


		if (homeService.reserve(hjid, hjidUser, xmlGregCal1, xmlGregCal2)) {
			
			return new ResponseEntity(HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST); 
		}
		

		
		
	}
	
	@RequestMapping(value = "/getReservations", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getReservations(
			@RequestParam(value="idUser") String idUser) throws DatatypeConfigurationException{
		
		System.out.println("getReservations");
		Long hjidUser = Long.parseLong(idUser);
		List<Rezervacija> rezervacije = new ArrayList<>();
		List<Rezervacija> sveRezervacije = rezRep.findAll();
		System.out.println(sveRezervacije);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		
		
		for (Rezervacija r: sveRezervacije) {
			System.out.println(r);
			if(r.getUser()!=null) {
				if (r.isRealizovana()==false && r.getUser().getHjid()==hjidUser
						&& r.getOd().compare(xmlGregCal)>0) {
					rezervacije.add(r);
					
				}
			}
		}
		System.out.println(rezervacije);
		return new ResponseEntity(rezervacije, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getPastReservations", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getPastReservations(
			@RequestParam(value="idUser") String idUser){
		
		System.out.println("getPastReservations");
		Long hjidUser = Long.parseLong(idUser);
		List<Rezervacija> rezervacije = new ArrayList<>();
		List<Rezervacija> sveRezervacije = rezRep.findAll();
		System.out.println(sveRezervacije);
		
		for (Rezervacija r: sveRezervacije) {
			System.out.println(r);
			if(r.getUser()!=null) {
				if (r.isRealizovana()==true && r.getUser().getHjid()==hjidUser) {
					rezervacije.add(r);
					
				}
			}
		}
		System.out.println(rezervacije);
		return new ResponseEntity(rezervacije, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getResInfo", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getResInfo(
			@RequestParam(value="idSmJed") String idSmJed){
		
		System.out.println("getResInfo");
		Long hjid = Long.parseLong(idSmJed);
		
		SmestajnaJedinica smjed = smJedRep.findOneByHjid(hjid);
	
		return new ResponseEntity(smjed, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> cancel(
			@RequestParam(value="id") String id, @RequestParam(value="idUser") String idUser ) throws DatatypeConfigurationException{
		
		System.out.println("cancel");
		Long hjid = Long.parseLong(id);
		Long hjidUser = Long.parseLong(idUser);

		Rezervacija re = rezRep.findOneByHjid(hjid);

		List<ZauzetostJedinice> zauzetosti = avRep.findAll();
		ZauzetostJedinice zj = new ZauzetostJedinice();
		System.out.println(zauzetosti);
		for (ZauzetostJedinice z: zauzetosti) {
			System.out.println(z);
			System.out.println(z.getSmestajnaJedinica().getHjid());
			System.out.println(re.getSmestajnaJedinica().getHjid());
			System.out.println(z.getOd());
			System.out.println(z.getDo());
			System.out.println(re.getOd());
			System.out.println(re.getDo());

			if (z.getSmestajnaJedinica()!=null && z.getSmestajnaJedinica().getHjid()==(re.getSmestajnaJedinica().getHjid())&&
					z.getOd().equals(re.getOd()) && z.getDo().equals(re.getDo())) {
				System.out.println("Rezervacija pronadjena!");
				
				z.setSmestajnaJedinica(null);
				avRep.save(z);
				
				avRep.delete(z);
				
			}
		}
		re.setUser(null);
		re.setSmestajnaJedinica(null);
		rezRep.save(re);
		rezRep.delete(re);
		
		
		List<Rezervacija> rezervacije = new ArrayList<>();
		List<Rezervacija> sveRezervacije = rezRep.findAll();
		System.out.println(sveRezervacije);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		XMLGregorianCalendar xmlGregCal =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		
		for (Rezervacija r: sveRezervacije) {
			System.out.println(r);
			if(r.getUser()!=null) {
				if (r.isRealizovana()==false && r.getUser().getHjid()==hjidUser
						&& r.getOd().compare(xmlGregCal)>0) {
					rezervacije.add(r);
					
				}
			}
		}
		System.out.println(rezervacije);
		return new ResponseEntity(rezervacije, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getCategories(){
		
		return new ResponseEntity(katRep.findAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getTypes", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getTypes(){
		
		return new ResponseEntity(tipRep.findAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/searchAdvanced", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> searchAdvanced(
			@RequestBody AdvancedSearchDto asearchDto,
			HttpServletRequest request) throws ParseException, DatatypeConfigurationException {
		
			System.out.println(asearchDto);
		
			
			ArrayList<SmestajnaJedinica> smestajneJedinice = homeService.advancedSearch(asearchDto);
			if (smestajneJedinice!=null) {
				return new ResponseEntity( smestajneJedinice, HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);	
			}
	
			
	}
	
	@RequestMapping(value = "/getReservation", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getReservation(
			@RequestParam(value="id") String id){
		Rezervacija r = rezRep.findOneByHjid(Long.parseLong(id));
		System.out.println(r);
		return new ResponseEntity(r, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getDodatne", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getDodatne(){
		List<DodatnaUsluga> dodatneUsluge = dodUslRep.findAll();
		System.out.println(dodatneUsluge);
		return new ResponseEntity(dodatneUsluge, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/sortCena", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortCena(
			@RequestBody List<SmestajnaJedinica> smestJedinice,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);

			smestJedinice.sort(Comparator.comparing(SmestajnaJedinica::getTrenutnaCena));

			System.out.println("Posle sorta");
			System.out.println(smestJedinice);
			return new ResponseEntity(smestJedinice, HttpStatus.OK);

			
	}
	
	@RequestMapping(value = "/sortKategorija", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortKategorija(
			@RequestBody List<SmestajnaJedinica> smestJedinice,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);
			List<SmestajnaJedinica> sortirano = new ArrayList<>();

			for (KategorijaSmestaja k: katRep.findAll()) {
				for (SmestajnaJedinica sj: smestJedinice) {
					if (k.getHjid()==sj.getKategorijaSmestaja().getHjid()) {
						sortirano.add(sj);
					}
				}
			}

			System.out.println("Posle sorta kategorija");
			System.out.println(sortirano);
			return new ResponseEntity(sortirano, HttpStatus.OK);

			
	}

	@RequestMapping(value = "/sortOcena", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sortOcena(
			@RequestBody List<SmestajnaJedinica> smestJedinice,
			HttpServletRequest request) {
		
			System.out.println(smestJedinice);
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
			}
			
			smestJedinice.sort(Comparator.comparing(SmestajnaJedinica::getTrenutnaOcena).reversed());

			System.out.println("Posle sorta ocene");
			System.out.println(smestJedinice);
			return new ResponseEntity(smestJedinice, HttpStatus.OK);

			
	}
	

	
}
