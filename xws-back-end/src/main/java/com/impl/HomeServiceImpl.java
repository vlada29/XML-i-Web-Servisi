package com.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.KategorijaSmestaja;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.TipSmestaja;
import com.model.User;
import com.model.ZauzetostJedinice;
import com.model.dto.AdvancedSearchDto;
import com.model.dto.SearchDto;
import com.repositories.AvailabilityRepository;
import com.repositories.KategorijasmestajaRepository;
import com.repositories.RezervacijaRepository;
import com.repositories.SmestajnaJedinicaRepository;
import com.repositories.TipsmestajaRepository;
import com.repositories.UserRepository;
import com.services.HomeService;
import com.model.UslugaJedinice;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	SmestajnaJedinicaRepository smJedRep;
	
	@Autowired
	AvailabilityRepository avRep;
	
	@Autowired
	RezervacijaRepository rezRep;
	
	@Autowired
	UserRepository userRep;
	
	@Autowired
	KategorijasmestajaRepository katRep;
	
	@Autowired
	TipsmestajaRepository tipRep;

	@Override
	public ArrayList<SmestajnaJedinica> findSearch(SearchDto searchDto) throws ParseException, DatatypeConfigurationException {
		// TODO Auto-generated method stub
		ArrayList<SmestajnaJedinica> savSmestaj = smJedRep.findAll();
		ArrayList<SmestajnaJedinica> filtSmestaj = new ArrayList<>();
		for (SmestajnaJedinica smjed: savSmestaj) {
			if (smjed.getBrojOsoba()==(Integer.parseInt(searchDto.getNumberPerson()))
					&& (smjed.getLokacija().getDrzava().equals(searchDto.getPlace())
							|| (smjed.getLokacija().getGrad().equals(searchDto.getPlace())))
					) {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(searchDto.getFrom());

			GregorianCalendar cal1 = new GregorianCalendar();
			cal1.setTime(date1);

			XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
			
			Date date2 = format.parse(searchDto.getTo());

			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime(date2);

			XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
			int brojac = 0;
			for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
				System.out.println("od: "+zauzJed.getOd());
				System.out.println("do: "+zauzJed.getDo());
				System.out.println("unetod: "+xmlGregCal1);
				System.out.println("unetdo: "+xmlGregCal2);
				
				
				if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid()) 
						&& ((zauzJed.getOd().compare(xmlGregCal1)<0 && zauzJed.getDo().compare(xmlGregCal1)>0)||
						(zauzJed.getOd().compare(xmlGregCal2)<0 && zauzJed.getDo().compare(xmlGregCal2)>0))) {
					System.out.println("Ne valja");
				}else {
					brojac++;
					System.out.println(brojac);
				}

				
				
			}
			int ukupanbr = 0;
			for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
				if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())){
					ukupanbr++;
				}
			}
			
			if (brojac==ukupanbr) {
				System.out.println(smjed);
				filtSmestaj.add(smjed);
			}
				
			}
		}
		
		System.out.println(filtSmestaj);
		return filtSmestaj;
	}

	@Override
	public boolean reserve(Long id, Long idUser, XMLGregorianCalendar d1, XMLGregorianCalendar d2) {
		SmestajnaJedinica smjed = smJedRep.findOneByHjid(id);
		User user = userRep.findOneByHjid(idUser);
		Rezervacija r = new Rezervacija();
		r.setDo(d2);
		r.setOd(d1);
		r.setRealizovana(false);
		r.setUser(user);
		r.setSmestajnaJedinica(smjed);
		r.setUkupnaCena(1200.00); // to do: cena
		
		
		ZauzetostJedinice zj = new ZauzetostJedinice();
		
		zj.setSmestajnaJedinica(smjed);
		zj.setDo(d2);
		zj.setOd(d1);

		try{
			rezRep.save(r);
			try {
				avRep.save(zj);
				return true;
			}catch(Exception e1) {
				e1.printStackTrace();
				return false;
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		
		
	}
	
	@Override
	public ArrayList<SmestajnaJedinica> advancedSearch(AdvancedSearchDto asearchDto) throws ParseException, DatatypeConfigurationException {
		// TODO Auto-generated method stub
		ArrayList<SmestajnaJedinica> savSmestaj = smJedRep.findAll();
		ArrayList<SmestajnaJedinica> filtSmestaj = new ArrayList<>();
		for (SmestajnaJedinica smjed: savSmestaj) {
			if (smjed.getBrojOsoba()==(Integer.parseInt(asearchDto.getNumberPerson()))
					&& (smjed.getLokacija().getDrzava().equals(asearchDto.getPlace())
							|| (smjed.getLokacija().getGrad().equals(asearchDto.getPlace())))
					) {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(asearchDto.getFrom());

			GregorianCalendar cal1 = new GregorianCalendar();
			cal1.setTime(date1);

			XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
			
			Date date2 = format.parse(asearchDto.getTo());

			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime(date2);

			XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
			int brojac = 0;
			for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
				System.out.println("od: "+zauzJed.getOd());
				System.out.println("do: "+zauzJed.getDo());
				System.out.println("unetod: "+xmlGregCal1);
				System.out.println("unetdo: "+xmlGregCal2);
				
				
				if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid()) 
						&& ((zauzJed.getOd().compare(xmlGregCal1)<0 && zauzJed.getDo().compare(xmlGregCal1)>0)||
						(zauzJed.getOd().compare(xmlGregCal2)<0 && zauzJed.getDo().compare(xmlGregCal2)>0))) {
					System.out.println("Ne valja");
				}else {
					brojac++;
					System.out.println(brojac);
				}

				
				
			}
			int ukupanbr = 0;
			for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
				if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())){
					ukupanbr++;
				}
			}
			
			if (brojac==ukupanbr) {
				System.out.println(smjed);
				filtSmestaj.add(smjed);
			}
				
			}
		}
		
		System.out.println(filtSmestaj);
		
		ArrayList<SmestajnaJedinica> filtSmestajAdv = new ArrayList<>();
		

		KategorijaSmestaja kat = katRep.findOneByHjid(Long.parseLong(asearchDto.getKategorija()));
		TipSmestaja tip = tipRep.findOneByHjid(Long.parseLong(asearchDto.getTip()));

		List<String> listaUsluga = new ArrayList<>();
		List<String> listaUslugaDodatnih = new ArrayList<>();
		
		if (asearchDto.isDorucak()) {
			listaUsluga.add("Dorucak");
		}
		if (asearchDto.isWifi()) {
			listaUsluga.add("WiFi");
		}
		if (asearchDto.isParking()) {
			listaUsluga.add("Parking");
		}
		if (asearchDto.isPansion()) {
			listaUsluga.add("Pansion");
		}
		if (asearchDto.isPolupansion()) {
			listaUsluga.add("Polupansion");
		}
		if (asearchDto.isTv()) {
			listaUsluga.add("Tv");
		}
		if (asearchDto.isKuhinja()) {
			listaUsluga.add("Kuhinja");
		}
		if (asearchDto.isKupatilo()) {
			listaUsluga.add("Kupatilo");
		}
		java.util.Collections.sort(listaUsluga);
		
		System.out.println(listaUsluga);
		
		
		for (SmestajnaJedinica sm: filtSmestaj) {
			System.out.println(sm.getNaziv());
			if (sm.getTipSmestaja().getHjid()==tip.getHjid() && sm.getKategorijaSmestaja().getHjid()==kat.getHjid()) {
				System.out.println("Isti tip i kat: "+sm);
				if (listaUsluga.isEmpty()) {
					filtSmestajAdv.add(sm);
				}else {
					for (UslugaJedinice uj: sm.getUsluge()) {
						listaUslugaDodatnih.add(uj.getUsluga().getNazivUsluge());
					}
					java.util.Collections.sort(listaUslugaDodatnih);
					
					System.out.println(listaUslugaDodatnih);
					if (listaUslugaDodatnih.containsAll(listaUsluga)) {
						System.out.println("Lista sadrzi drugu - dodaj ovu smestajnu jedinicu");
						filtSmestajAdv.add(sm);
					}
				}
			}
			
		}
		
		return filtSmestajAdv;
	}
	
	

	
}
