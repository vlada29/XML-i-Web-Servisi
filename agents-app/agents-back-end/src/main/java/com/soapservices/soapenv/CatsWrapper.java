package com.soapservices.soapenv;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.KategorijaSmestaja;

@XmlRootElement(name="Cats_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class CatsWrapper {
	public List<KategorijaSmestaja> getCategories() {
		return categories;
	}

	public void setCategories(List<KategorijaSmestaja> categories) {
		this.categories = categories;
	}

	@XmlElementWrapper(name="categories")
	@XmlElement(name="KategorijaSmestaja")
	private List<KategorijaSmestaja> categories;
}
