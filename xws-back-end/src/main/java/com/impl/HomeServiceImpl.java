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
import com.model.PlanCena;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.TipSmestaja;
import com.model.User;
import com.model.ZauzetostJedinice;
import com.model.dto.AdvancedSearchDto;
import com.model.dto.DodatnaDto;
import com.model.dto.SearchDto;
import com.repositories.AvailabilityRepository;
import com.repositories.KategorijasmestajaRepository;
import com.repositories.PlanCenaRepository;
import com.repositories.RezervacijaRepository;
import com.repositories.SmestajnaJedinicaRepository;
import com.repositories.TipsmestajaRepository;
import com.repositories.UserRepository;
import com.services.HomeService;
import com.model.UslugaJedinice;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	PlanCenaRepository pcRep;

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
					if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())) {
						System.out.println("ZAUZET");
						System.out.println("od: "+zauzJed.getOd());
						System.out.println("do: "+zauzJed.getDo());
						System.out.println("unetod: "+xmlGregCal1);
						System.out.println("unetdo: "+xmlGregCal2);
						
				
						if (((zauzJed.getOd().compare(xmlGregCal1)>0 && zauzJed.getOd().compare(xmlGregCal2)<0)||
								(zauzJed.getOd().compare(xmlGregCal1)<0 && zauzJed.getDo().compare(xmlGregCal1)>0))) {
							System.out.println("Ne valja");
						}else {
							brojac++;
							System.out.println(brojac);
						}
					}
	
					
					
				}
				int ukupanbr = 0;
				for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
					if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())){
						ukupanbr++;
					}
				}
				System.out.println("ukupan"+ukupanbr);
				System.out.println("brojac"+brojac);
				if (brojac==ukupanbr) {
					System.out.println("DODAJEMO OVU:"+smjed.getCene());
		//
					for (PlanCena c: smjed.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							smjed.setTrenutnaCena(c.getCena());
							smJedRep.save(smjed);
						}
						
					}
				
					for (PlanCena c: smjed.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							smjed.setTrenutnaCena(c.getCena());
							smJedRep.save(smjed);
						}
						
					}
					filtSmestaj.add(smjed);
		
				}
					
				}
			}
		
		System.out.println(filtSmestaj);
		return filtSmestaj;
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRES_NEW)
	public boolean reserve( Long id, Long idUser, XMLGregorianCalendar d1, XMLGregorianCalendar d2) {
		SmestajnaJedinica smjed = smJedRep.findOneByHjid(id);
		User user = userRep.findOneByHjid(idUser);
		Rezervacija r = new Rezervacija();
		r.setOd(d1);
		r.setDo(d2);
		r.setRealizovana(false);
		r.setUser(user);
		r.setSmestajnaJedinica(smjed);
		r.setUkupnaCena(1200.00); // to do: cena
		
		
		ZauzetostJedinice zj = new ZauzetostJedinice();
		
		zj.setSmestajnaJedinica(smjed);
		zj.setDo(d2);
		zj.setOd(d1);
		
		int brojac = 0;
		for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
			if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())) {
				System.out.println("ZAUZET");
				System.out.println("od: "+zauzJed.getOd());
				System.out.println("do: "+zauzJed.getDo());
				System.out.println("unetod: "+d1);
				System.out.println("unetdo: "+d2);
				
				System.out.println(smjed.getHjid());
				if (((zauzJed.getOd().compare(d1)>0 && zauzJed.getOd().compare(d2)<0)||
						(zauzJed.getOd().compare(d1)<0 && zauzJed.getDo().compare(d1)>0))) {

					System.out.println("Ne valja");
				}else {
					brojac++;
					System.out.println(brojac);
				}
			}

			
			
		}
		int ukupanbr = 0;
		for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
			if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())){
				ukupanbr++;
			}
		}
		System.out.println("ukupan"+ukupanbr);
		System.out.println("brojac"+brojac);
		if (brojac!=ukupanbr) {
			return false;
		}
		
		
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
		System.out.println(asearchDto);
		ArrayList<SmestajnaJedinica> savSmestaj = smJedRep.findAll();
		ArrayList<SmestajnaJedinica> filtSmestaj = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(asearchDto.getFrom());

		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);

		XMLGregorianCalendar xmlGregCal1 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal1);
		
		Date date2 = format.parse(asearchDto.getTo());

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		XMLGregorianCalendar xmlGregCal2 =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
		
		for (SmestajnaJedinica smjed: savSmestaj) {
			if (smjed.getBrojOsoba()==(Integer.parseInt(asearchDto.getNumberPerson()))
					&& (smjed.getLokacija().getDrzava().equals(asearchDto.getPlace())
							|| (smjed.getLokacija().getGrad().equals(asearchDto.getPlace())))
					) {


				
			int brojac = 0;
			for (ZauzetostJedinice zauzJed: avRep.findAll()  ) {
				if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid())) {
					System.out.println("od: "+zauzJed.getOd());
					System.out.println("do: "+zauzJed.getDo());
					System.out.println("unetod: "+xmlGregCal1);
					System.out.println("unetdo: "+xmlGregCal2);
					
					
					if (zauzJed.getSmestajnaJedinica().getHjid().equals(smjed.getHjid()) 
							&& (zauzJed.getOd().compare(xmlGregCal1)>0 && zauzJed.getOd().compare(xmlGregCal2)<0)||
									(zauzJed.getOd().compare(xmlGregCal1)<0 && zauzJed.getDo().compare(xmlGregCal1)>0)) {
						System.out.println("Ne valja");
					}else {
						brojac++;
						System.out.println(brojac);
					}
	
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

					for (PlanCena c: smjed.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							smjed.setTrenutnaCena(c.getCena());
							smJedRep.save(smjed);
						}
						
					}
					filtSmestaj.add(smjed);
					System.out.println(smjed.getCene());
				}
					
				}
			}
			
		System.out.println(filtSmestaj);
		
		ArrayList<SmestajnaJedinica> filtSmestajAdv = new ArrayList<>();
		KategorijaSmestaja kat;
		if (!asearchDto.getKategorija().equals("-1")) {
			kat = katRep.findOneByHjid(Long.parseLong(asearchDto.getKategorija()));
			
		}else {
			kat = new KategorijaSmestaja();
			kat.setHjid((long)-1);
		}
		TipSmestaja tip;
		if (!asearchDto.getTip().equals("-1")) {
			tip = tipRep.findOneByHjid(Long.parseLong(asearchDto.getTip()));
		}else {
			tip = new TipSmestaja();
			tip.setHjid((long)-1);
		}

		List<String> listaUsluga = new ArrayList<>();
		
		List<DodatnaDto> listaUslugaIzSearcha = asearchDto.getDodatne();
		
		for (DodatnaDto d: listaUslugaIzSearcha) {
			if (d.isChecked()) {
				listaUsluga.add(d.getNazivUsluge());
			}
		}
		java.util.Collections.sort(listaUsluga);
		
		System.out.println(listaUsluga);
		
		System.out.println(filtSmestaj);
		for (SmestajnaJedinica sm: filtSmestaj) {
			List<String> listaUslugaDodatnih = new ArrayList<>();
			
			System.out.println("smestajnajedinica:   "+sm.getHjid());

			if (sm.getTipSmestaja().getHjid().equals(tip.getHjid()) && sm.getKategorijaSmestaja().getHjid().equals(kat.getHjid())) {
				System.out.println("Isti tip i kat: "+sm);
				if (listaUsluga.isEmpty()) {
					for (PlanCena c: sm.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							sm.setTrenutnaCena(c.getCena());
							smJedRep.save(sm);
						}
						
					}

					filtSmestajAdv.add(sm);
				}else {
					System.out.println(sm.getHjid());
					for (UslugaJedinice uj: sm.getUsluge()) {
						System.out.println(uj);
						listaUslugaDodatnih.add(uj.getUsluga().getNazivUsluge());
					}
					java.util.Collections.sort(listaUslugaDodatnih);
					
					System.out.println("lud"+listaUslugaDodatnih);
					System.out.println("lu"+listaUsluga);
					if (listaUslugaDodatnih.containsAll(listaUsluga)) {
						System.out.println("Lista sadrzi drugu - dodaj ovu smestajnu jedinicu");
						for (PlanCena c: sm.getCene()) {
							
							if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
								System.out.println("cena"+c);
								sm.setTrenutnaCena(c.getCena());
								smJedRep.save(sm);
							}
							
						}

						filtSmestajAdv.add(sm);
					}
				}
			}else if (sm.getTipSmestaja().getHjid().equals(tip.getHjid()) && kat.getHjid()==(long)(-1) ) {
				System.out.println("Isti tip, kat nije uneta: "+sm);
				if (listaUsluga.isEmpty()) {
					for (PlanCena c: sm.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							sm.setTrenutnaCena(c.getCena());
							smJedRep.save(sm);
						}
						
					}
					filtSmestajAdv.add(sm);
				}else {
					for (UslugaJedinice uj: sm.getUsluge()) {
						listaUslugaDodatnih.add(uj.getUsluga().getNazivUsluge());
					}
					java.util.Collections.sort(listaUslugaDodatnih);
					
					System.out.println("lud"+listaUslugaDodatnih);
					System.out.println("lu"+listaUsluga);
					if (listaUslugaDodatnih.containsAll(listaUsluga)) {
						System.out.println("Lista sadrzi drugu - dodaj ovu smestajnu jedinicu");
						for (PlanCena c: sm.getCene()) {
							
							if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
								System.out.println("cena"+c);
								sm.setTrenutnaCena(c.getCena());
								smJedRep.save(sm);
							}
							
						}
						filtSmestajAdv.add(sm);
					}
				}
				
			}else if (sm.getKategorijaSmestaja().getHjid().equals(kat.getHjid()) && tip.getHjid()==(long)(-1)) {
				System.out.println("Ista kat, tip nije unesen: "+sm);
				if (listaUsluga.isEmpty()) {
					for (PlanCena c: sm.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							sm.setTrenutnaCena(c.getCena());
							smJedRep.save(sm);
						}
						
					}

					filtSmestajAdv.add(sm);
				}else {
					for (UslugaJedinice uj: sm.getUsluge()) {
						listaUslugaDodatnih.add(uj.getUsluga().getNazivUsluge());
					}
					java.util.Collections.sort(listaUslugaDodatnih);
					
					System.out.println("lud"+listaUslugaDodatnih);
					System.out.println("lu"+listaUsluga);
					if (listaUslugaDodatnih.containsAll(listaUsluga)) {
						System.out.println("Lista sadrzi drugu - dodaj ovu smestajnu jedinicu");
						for (PlanCena c: sm.getCene()) {
							
							if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
								System.out.println("cena"+c);
								sm.setTrenutnaCena(c.getCena());
								smJedRep.save(sm);
							}
							
						}

						filtSmestajAdv.add(sm);
					}
				}
				
			}else if (tip.getHjid()==(long)(-1) && kat.getHjid()==(long)(-1)){
				System.out.println("Nisu uneti tip i kat: "+sm);
				if (listaUsluga.isEmpty()) {
					for (PlanCena c: sm.getCene()) {
						
						if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
							System.out.println("cena"+c);
							sm.setTrenutnaCena(c.getCena());
							smJedRep.save(sm);
						}
						
					}

					filtSmestajAdv.add(sm);
				}else {
					System.out.println(sm.getHjid());
					for (UslugaJedinice uj: sm.getUsluge()) {
						System.out.println(uj);
						listaUslugaDodatnih.add(uj.getUsluga().getNazivUsluge());
					}
					java.util.Collections.sort(listaUslugaDodatnih);
					
					System.out.println("lud"+listaUslugaDodatnih);
					System.out.println("lu"+listaUsluga);
					if (listaUslugaDodatnih.containsAll(listaUsluga)) {
						System.out.println("Lista sadrzi drugu - dodaj ovu smestajnu jedinicu");
						for (PlanCena c: sm.getCene()) {
							
							if (c.getKrajVazenja().compare(xmlGregCal2)>0) {
								System.out.println("cena"+c);
								sm.setTrenutnaCena(c.getCena());
								smJedRep.save(sm);
							}
							
						}
						filtSmestajAdv.add(sm);
					}
				}
				
			}

			
		}
		
		return filtSmestajAdv;
	}
	
	
	

	
}
