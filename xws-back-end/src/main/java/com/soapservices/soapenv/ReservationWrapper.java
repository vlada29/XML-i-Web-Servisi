package com.soapservices.soapenv;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XMLGregorianCalendarAsDate;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XmlAdapterUtils;

import com.model.SmestajnaJedinica;
import com.model.ZauzetostJedinice;

@XmlRootElement(name = "Reservation_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservationWrapper {
	@XmlElement(name = "hjid")
	private Long hjid;
	
	@XmlElement(name = "Od", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar od;
    @XmlElement(name = "Do", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar _do;
	
	public Long getHjid() {
		return hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

	 @Transient
	    public XMLGregorianCalendar getOd() {
	        return od;
	    }

	    /**
	     * Sets the value of the od property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link XMLGregorianCalendar }
	     *     
	     */
	    public void setOd(XMLGregorianCalendar value) {
	        this.od = value;
	    }

	    /**
	     * Gets the value of the do property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link XMLGregorianCalendar }
	     *     
	     */
	    @Transient
	    public XMLGregorianCalendar getDo() {
	        return _do;
	    }

	    /**
	     * Sets the value of the do property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link XMLGregorianCalendar }
	     *     
	     */
	    public void setDo(XMLGregorianCalendar value) {
	        this._do = value;
	    }

	    public Date getOdItem() {
	        return XmlAdapterUtils.unmarshall(XMLGregorianCalendarAsDate.class, this.getOd());
	    }

	    public void setOdItem(Date target) {
	        setOd(XmlAdapterUtils.marshall(XMLGregorianCalendarAsDate.class, target));
	    }
 
	    public Date getDoItem() {
	        return XmlAdapterUtils.unmarshall(XMLGregorianCalendarAsDate.class, this.getDo());
	    }

	    public void setDoItem(Date target) {
	        setDo(XmlAdapterUtils.marshall(XMLGregorianCalendarAsDate.class, target));
	    }

	
}
