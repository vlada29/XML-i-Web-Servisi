package com.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.User;
import com.model.ZauzetostJedinice;
import com.model.dto.SearchDto;
import com.repositories.AvailabilityRepository;
import com.repositories.RezervacijaRepository;
import com.repositories.SmestajnaJedinicaRepository;
import com.repositories.UserRepository;
import com.services.HomeService;

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
	
	

	
}
