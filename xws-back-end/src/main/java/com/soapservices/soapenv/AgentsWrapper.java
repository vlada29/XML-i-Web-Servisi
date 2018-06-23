package com.soapservices.soapenv;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.Agent;

@XmlRootElement(name="Agents_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentsWrapper {
	@XmlElementWrapper(name="agents")
	@XmlElement(name="Agent")
	private List<Agent> agents;

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

}
