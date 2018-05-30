package com.soapservices;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import com.model.Agent;
import com.model.LoginAgentRequest;
import com.model.LoginAgentResponse;
import com.model.SmestajnaJedinica;

@Transactional
@Endpoint
public class AgentEndpoint {
	
	
	@PayloadRoot(namespace="model", localPart = "Smestajna_Jedinica")
	@ResponsePayload
	public SmestajnaJedinica login(@RequestPayload SmestajnaJedinica request) throws JAXBException {
//		LoginAgentResponse response = new LoginAgentResponse();
//		System.out.println("req username: " + request.getUsername());
//		System.out.println("found: " + agentService.findByUsername(request.getUsername()).getUsername());
//		response.setAgent(agentService.findByUsername(request.getUsername()));
//		
//		return agentService.findByUsername(request.getUsername());
		System.out.println("Odgovor primljen nazad u agent backend");
		System.out.println(request);
//		JAXBContext jaxbContext = JAXBContext.newInstance(SmestajnaJedinica.class);
//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//		StringWriter sw = new StringWriter();
//		jaxbMarshaller.marshal(request, sw);
//		String xmlString = sw.toString();
//		//System.out.println(xmlString);
//		
//		System.out.println("XML.toString(JSONObject)-> " + xmlString);
		
		return null;
	}
}
