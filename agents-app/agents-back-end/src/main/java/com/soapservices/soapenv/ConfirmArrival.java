package com.soapservices.soapenv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Confirm_Arrival")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmArrival {
	@XmlElement(name = "username")
	private String username;
	
	@XmlElement(name = "hjid")
	private Long hjid;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getHjid() {
		return hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}
	
}
