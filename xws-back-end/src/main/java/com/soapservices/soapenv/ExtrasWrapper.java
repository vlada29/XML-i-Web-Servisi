package com.soapservices.soapenv;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.DodatnaUsluga;
import com.model.KategorijaSmestaja;

@XmlRootElement(name="Extras_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtrasWrapper {
	@XmlElementWrapper(name="extras")
	@XmlElement(name="DodatnaUsluga")
	private List<DodatnaUsluga> extras;

	public List<DodatnaUsluga> getExtras() {
		return extras;
	}

	public void setExtras(List<DodatnaUsluga> extras) {
		this.extras = extras;
	}

}
