package com.soapservices;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Agent;
import com.model.Message;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;
import com.repositories.AgentRepository;
import com.services.RezService;
import com.services.UnitService;
import com.soapservices.soapenv.ConfirmArrival;
import com.soapservices.soapenv.MessWrapper;
import com.soapservices.soapenv.ReservationWrapper;
import com.soaputils.SOAPUtils;
@Transactional
@Service
public class SoapActionsService {
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	RezService rezService;
	
	@Autowired
	AgentRepository agentRepo;
	
	public void confirmArrival(String username, Long hjid) throws JAXBException, SOAPException{
		System.out.println("Confirming arrival on Azure...");
		String se = "http://a15c4eb3.ngrok.io/soapWS";
		String sa = "http://a15c4eb3.ngrok.io/Confirm_Arrival";

		ConfirmArrival ca = new ConfirmArrival();
		ca.setUsername(username);
		ca.setHjid(hjid);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ConfirmArrival.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(ca, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
	}
	
	public void createNewUnit(SmestajnaJedinica unit) throws JAXBException, SOAPException{
		System.out.println("Sending create request...");
		String se = "http://a15c4eb3.ngrok.io/soapWS";
		String sa = "http://a15c4eb3.ngrok.io/Smestajna_Jedinica";
		
		
		
		System.out.println("*******************************************");
		System.out.println("***[BEFORE SYNC] Units in Local database:***");
		for(SmestajnaJedinica sj : unitService.findAll()){
			System.out.println(sj.getOpis());
		}
		System.out.println("*******************************************");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(SmestajnaJedinica.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(unit, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
		

		Unmarshaller unmarshaller = JAXBContext.newInstance(SmestajnaJedinica.class).createUnmarshaller();
		SmestajnaJedinica unit_with_hjid = (SmestajnaJedinica)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());


		unitService.save(unit_with_hjid);
		
		System.out.println("*******************************************");
		System.out.println("***[AFTER SYNC] Units in Local database:***");
		for(SmestajnaJedinica r : unitService.findAll()){
			System.out.println(r);
		}
		System.out.println("*******************************************");
		
		
	}
	
	public Rezervacija reserve(Long hjid, Rezervacija rw) throws JAXBException, SOAPException{
		System.out.println("Reserving on Azure...");
		String se = "http://a15c4eb3.ngrok.io/soapWS";
		String sa = "http://a15c4eb3.ngrok.io/Rezervacija";
		
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Rezervacija.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(rw, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		 
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		Unmarshaller unmarshaller = JAXBContext.newInstance(Rezervacija.class).createUnmarshaller();
		Rezervacija rw2 = (Rezervacija)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
		 
		 
		if(rw2.getSmestajnaJedinica().getHjid().equals(Long.valueOf(-1))) {//uspesno rezervisano
			return null;
		} else {
			return rw2;
		}
		
	}
	
	public void sendMessage(Message message) throws JAXBException, SOAPException{
		
		System.out.println("Reserving on Azure...");
		String se = "http://a15c4eb3.ngrok.io/soapWS";
		String sa = "http://a15c4eb3.ngrok.io/Message";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(message, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPUtils.callSoapWebService(se, sa, xmlString);	
	}
	
	public void login(Agent agent) throws JAXBException, SOAPException{
		
		System.out.println("Reserving on Azure...");
		String se = "http://a15c4eb3.ngrok.io/soapWS";
		String sa = "http://a15c4eb3.ngrok.io/Agent";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Agent.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(agent, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPMessage message = SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		Unmarshaller unmarshaller = JAXBContext.newInstance(Agent.class).createUnmarshaller();
		Agent rw2 = (Agent)unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());	
	
		agentRepo.deleteAll();
		if(!rw2.getHjid().equals(Long.valueOf(-1))){
			agentRepo.save(rw2);
		}
	}
	
}
