package com.soapservices;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.services.AgentService;
import com.model.Agent;
import ns.LoginAgentRequest;
import ns.LoginAgentResponse;

@XmlSeeAlso({Agent.class})
@Endpoint
public class AgentEndpoint {
	@Autowired
	private AgentService agentService;
	
	@PayloadRoot(namespace="ns", localPart = "loginAgentRequest")
	@ResponsePayload
	public LoginAgentResponse login(@RequestPayload LoginAgentRequest request) {
		LoginAgentResponse response = new LoginAgentResponse();
		System.out.println("req username: " + request.getUsername());
		System.out.println("found: " + agentService.findByUsername(request.getUsername()).getUsername());
		response.setAgent(agentService.findByUsername(request.getUsername()));
		return response;
	}
}
