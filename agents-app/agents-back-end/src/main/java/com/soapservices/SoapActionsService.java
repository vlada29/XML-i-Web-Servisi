package com.soapservices;

import java.io.StringWriter;
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

import com.model.Message;
import com.model.Rezervacija;
import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;
import com.services.RezService;
import com.services.UnitService;
import com.soapservices.soapenv.ConfirmArrival;
import com.soaputils.SOAPUtils;
@Transactional
@Service
public class SoapActionsService {
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	RezService rezService;
	
	public void confirmArrival(String username, Long hjid) throws JAXBException, SOAPException{
		System.out.println("Confirming arrival on Azure...");
		String se = "http://localhost:8080/soapWS";
		String sa = "http://localhost:8080/Confirm_Arrival";

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
		String se = "http://localhost:8080/soapWS";
		String sa = "http://localhost:8080/Smestajna_Jedinica";
		
		
		
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
	
	public void reserve(ZauzetostJedinice zj) throws JAXBException, SOAPException{
		System.out.println("Reserving on Azure...");
		String se = "http://localhost:8080/soapWS";
		String sa = "http://localhost:8080/Zauzetost_Jedinice";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ZauzetostJedinice.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(zj, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPUtils.callSoapWebService(se, sa, xmlString);
		
		
	}
	
	public void sendMessage(Message message) throws JAXBException, SOAPException{
		System.out.println("Reserving on Azure...");
		String se = "http://localhost:8080/soapWS";
		String sa = "http://localhost:8080/Message";
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Message.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(message, sw);
		String xmlString = sw.toString();
		
		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		SOAPUtils.callSoapWebService(se, sa, xmlString);	
	}
	
}
