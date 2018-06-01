package com.soapservices.soapenv;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.KategorijaSmestaja;
import com.model.TipSmestaja;

@XmlRootElement(name="Types_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypesWrapper {
	@XmlElementWrapper(name="types")
	@XmlElement(name="TipSmestaja")
	private List<TipSmestaja> types;

	public List<TipSmestaja> getTypes() {
		return types;
	}

	public void setTypes(List<TipSmestaja> types) {
		this.types = types;
	}

}
