package com.soapservices.soapenv;
import java.util.*;
import javax.xml.bind.annotation.*;

import com.model.SmestajnaJedinica;

@XmlRootElement(name = "Units_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnitsWrapper {
	@XmlElementWrapper(name="units")
	@XmlElement(name="Smestajna_Jedinica")
	private List<SmestajnaJedinica> units;

	@XmlElement(name = "username")
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<SmestajnaJedinica> getUnits() {
		return units;
	}

	public void setUnits(List<SmestajnaJedinica> units) {
		this.units = units;
	}
}
