package com.soapservices;
import java.util.*;
import javax.xml.bind.annotation.*;

import com.model.Rezervacija;

@XmlRootElement(name = "Res_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResWrapper {
	@XmlElementWrapper(name="reservations")
	@XmlElement(name="Rezervacija")
	private List<Rezervacija> reservations;
	
	@XmlElement(name = "username")
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<Rezervacija> getReservations() {
		return reservations;
	}

	public void setUnits(List<Rezervacija> reservations) {
		this.reservations = reservations;
	}
}
