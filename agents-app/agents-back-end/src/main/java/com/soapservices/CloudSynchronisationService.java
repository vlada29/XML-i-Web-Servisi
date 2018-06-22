package com.soapservices;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.model.DodatnaUsluga;
import com.model.KategorijaSmestaja;
import com.model.Message;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.TipSmestaja;
import com.repositories.CategoryRepository;
import com.repositories.ExtrasRepository;
import com.repositories.TypesRepository;
import com.services.MessageService;
import com.services.RezService;
import com.services.UnitService;
import com.soapservices.soapenv.CatsWrapper;
import com.soapservices.soapenv.ExtrasWrapper;
import com.soapservices.soapenv.MessWrapper;
import com.soapservices.soapenv.ResWrapper;
import com.soapservices.soapenv.TypesWrapper;
import com.soapservices.soapenv.UnitsWrapper;
import com.soaputils.SOAPUtils;
@Transactional
@Service
public class CloudSynchronisationService {
	@Autowired
	UnitService unitService;
	
	@Autowired
	RezService rezService;
	
	@Autowired
	MessageService messService;
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	ExtrasRepository extrasRepo;
	
	@Autowired
	TypesRepository typesRepo;
	
	public void syncroniseWithCloudWS(String username) throws JAXBException, SOAPException{
		System.out.println("Synchronising with Azure database...");
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Units_Wrapper";
		
		
		List<SmestajnaJedinica> units = unitService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[BEFORE SYNC] Units in Local database:***");
		for(SmestajnaJedinica u : units){
			System.out.println(u);
		}
		System.out.println("*******************************************");
		UnitsWrapper uw = new UnitsWrapper();
		uw.setUnits(units);
		uw.setUsername(username);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(UnitsWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(uw, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		//odgovor u message
		Unmarshaller unmarshaller = JAXBContext.newInstance(UnitsWrapper.class).createUnmarshaller();
		UnitsWrapper uw2 = (UnitsWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		
		List<SmestajnaJedinica> unitsAfter = uw2.getUnits();
		
		unitService.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Units in Local database:***");
		for(SmestajnaJedinica u : unitService.findAll()){
			System.out.println(u);
		}
		System.out.println("*******************************************");
		
		
		
		for(SmestajnaJedinica u : unitsAfter){
			unitService.save(u);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Units in Local database:***");
		for(SmestajnaJedinica u : unitService.findAll()){
			System.out.println(u);
		}
		System.out.println("*******************************************");
				
	}
	
	public void syncroniseWithCloudRes(String username) throws JAXBException, SOAPException{
		System.out.println("Synchronising with Azure database...");
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Res_Wrapper";
		
		
		List<Rezervacija> reservations = rezService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[BEFORE SYNC] Reservations in Local database:***");
		for(Rezervacija r : reservations){
			System.out.println(r);
		}
		System.out.println("*******************************************");
		ResWrapper rw = new ResWrapper();
		rw.setReservations(reservations);
		rw.setUsername(username);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ResWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(ResWrapper.class).createUnmarshaller();
		ResWrapper rw2 = (ResWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		System.out.println(rw2.getReservations());
		List<Rezervacija> reservationsAfter = rw2.getReservations();
		
		rezService.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Reservations in Local database:***");
		for(Rezervacija r : rezService.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");

		for(Rezervacija r : reservationsAfter){
			rezService.save(r);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Reservations in Local database:***");
		for(Rezervacija r : rezService.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");	
	}
	
	public void syncroniseWithCloudMess(Long hjid) throws JAXBException, SOAPException{
		System.out.println("Synchronising with Azure database...");
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Mess_Wrapper";
		
		
		List<Message> messages = messService.findAll();
		System.out.println("*******************************************");
		System.out.println("***[BEFORE SYNC] Messages in Local database:***");
		for(Message r : messages){
			 
			System.out.println(r);
		}
		System.out.println("*******************************************");
		MessWrapper rw = new MessWrapper();
		rw.setMessages(new ArrayList<Message>()); //messages
		rw.setHjid(hjid);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(MessWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(MessWrapper.class).createUnmarshaller();
		MessWrapper rw2 = (MessWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		System.out.println(rw2.getMessages());
		List<Message> messagesAfter = rw2.getMessages();
		
		messService.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Messages in Local database:***");
		for(Message r : messService.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");

		for(Message r : messagesAfter){
			if(r.getDatum()!=null)
				r.setDat(r.getDatum().getTime());
			messService.save(r);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Messages in Local database:***");
		for(Message r : messService.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");
		
		
	}
	
	public void syncroniseWithCloudCategories() throws JAXBException, SOAPException{
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Cats_Wrapper";

		CatsWrapper rw = new CatsWrapper();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(CatsWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();
		
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(CatsWrapper.class).createUnmarshaller();
		CatsWrapper cw = (CatsWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		
		catRepo.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Categories in Local database:***");
		for(KategorijaSmestaja r : catRepo.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");

		for(KategorijaSmestaja r : cw.getCategories()){
			catRepo.save(r);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Categories in Local database:***");
		for(KategorijaSmestaja r : catRepo.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");	
	}
	
	public void syncroniseWithCloudTypes() throws JAXBException, SOAPException{
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Types_Wrapper";
			
		TypesWrapper rw = new TypesWrapper();

		JAXBContext jaxbContext = JAXBContext.newInstance(TypesWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();
		
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(TypesWrapper.class).createUnmarshaller();
		TypesWrapper tw = (TypesWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		
		typesRepo.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Types in Local database:***");
		for(TipSmestaja r : typesRepo.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");

		for(TipSmestaja r : tw.getTypes()){
			typesRepo.save(r);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Types in Local database:***");
		for(TipSmestaja r : typesRepo.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");	
	}
	
	public void syncroniseWithCloudExtras() throws JAXBException, SOAPException{
		String se = "http://192.168.1.2:8080/soapWS";
		String sa = "http://192.168.1.2:8080/Extras_Wrapper";

		ExtrasWrapper rw = new ExtrasWrapper();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ExtrasWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();

		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(ExtrasWrapper.class).createUnmarshaller();
		ExtrasWrapper ew = (ExtrasWrapper)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		
		extrasRepo.deleteAll();
		System.out.println("*******************************************");
		System.out.println("***[REMOVED] Extras in Local database:***");
		for(DodatnaUsluga r : extrasRepo.findAll()){
			System.out.println(r.getNazivUsluge());
		}
		System.out.println("*******************************************");

		for(DodatnaUsluga r : ew.getExtras()){
			extrasRepo.save(r);
		}
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Extras in Local database:***");
		for(DodatnaUsluga r : extrasRepo.findAll()){
			System.out.println(r.getNazivUsluge());
		}
		System.out.println("*******************************************");	
	}
}
