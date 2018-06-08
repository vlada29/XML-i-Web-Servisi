//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.01 at 05:25:45 PM CEST 
//


package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XMLGregorianCalendarAsDate;
import org.jvnet.hyperjaxb3.xml.bind.annotation.adapters.XmlAdapterUtils;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Zauzetost_Jedinice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Zauzetost_Jedinice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{model}Smestajna_Jedinica"/>
 *         &lt;element name="Od" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Do" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zauzetost_Jedinice", propOrder = {
    "smestajnaJedinica",
    "od",
    "_do"
})
@Entity(name = "ZauzetostJedinice")
@Table(name = "ZAUZETOST_JEDINICE")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement(name = "Zauzetost_Jedinice")
public class ZauzetostJedinice
    implements Serializable, Equals, HashCode
{

    @XmlElement(name = "Smestajna_Jedinica", required = true)
    protected SmestajnaJedinica smestajnaJedinica;
    @XmlElement(name = "Od", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar od;
    @XmlElement(name = "Do", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar _do;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the smestajnaJedinica property.
     * 
     * @return
     *     possible object is
     *     {@link SmestajnaJedinica }
     *     
     */
    @ManyToOne(targetEntity = SmestajnaJedinica.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "SMESTAJNA_JEDINICA_ZAUZETOST_0")
    public SmestajnaJedinica getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    /**
     * Sets the value of the smestajnaJedinica property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmestajnaJedinica }
     *     
     */
    public void setSmestajnaJedinica(SmestajnaJedinica value) {
        this.smestajnaJedinica = value;
    }

    /**
     * Gets the value of the od property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
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

    /**
     * Gets the value of the hjid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    @Id
    @Column(name = "HJID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getHjid() {
        return hjid;
    }

    /**
     * Sets the value of the hjid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHjid(Long value) {
        this.hjid = value;
    }

    @Basic
    @Column(name = "OD_ITEM")
    @Temporal(TemporalType.DATE)
    public Date getOdItem() {
        return XmlAdapterUtils.unmarshall(XMLGregorianCalendarAsDate.class, this.getOd());
    }

    public void setOdItem(Date target) {
        setOd(XmlAdapterUtils.marshall(XMLGregorianCalendarAsDate.class, target));
    }

    @Basic
    @Column(name = "DO_ITEM")
    @Temporal(TemporalType.DATE)
    public Date getDoItem() {
        return XmlAdapterUtils.unmarshall(XMLGregorianCalendarAsDate.class, this.getDo());
    }

    public void setDoItem(Date target) {
        setDo(XmlAdapterUtils.marshall(XMLGregorianCalendarAsDate.class, target));
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ZauzetostJedinice)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ZauzetostJedinice that = ((ZauzetostJedinice) object);
        {
            SmestajnaJedinica lhsSmestajnaJedinica;
            lhsSmestajnaJedinica = this.getSmestajnaJedinica();
            SmestajnaJedinica rhsSmestajnaJedinica;
            rhsSmestajnaJedinica = that.getSmestajnaJedinica();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "smestajnaJedinica", lhsSmestajnaJedinica), LocatorUtils.property(thatLocator, "smestajnaJedinica", rhsSmestajnaJedinica), lhsSmestajnaJedinica, rhsSmestajnaJedinica)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsOd;
            lhsOd = this.getOd();
            XMLGregorianCalendar rhsOd;
            rhsOd = that.getOd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "od", lhsOd), LocatorUtils.property(thatLocator, "od", rhsOd), lhsOd, rhsOd)) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsDo;
            lhsDo = this.getDo();
            XMLGregorianCalendar rhsDo;
            rhsDo = that.getDo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "_do", lhsDo), LocatorUtils.property(thatLocator, "_do", rhsDo), lhsDo, rhsDo)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SmestajnaJedinica theSmestajnaJedinica;
            theSmestajnaJedinica = this.getSmestajnaJedinica();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "smestajnaJedinica", theSmestajnaJedinica), currentHashCode, theSmestajnaJedinica);
        }
        {
            XMLGregorianCalendar theOd;
            theOd = this.getOd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "od", theOd), currentHashCode, theOd);
        }
        {
            XMLGregorianCalendar theDo;
            theDo = this.getDo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "_do", theDo), currentHashCode, theDo);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

	@Override
	public String toString() {
		return "ZauzetostJedinice [smestajnaJedinica=" + smestajnaJedinica + ", od=" + od + ", _do=" + _do + ", hjid="
				+ hjid + "]";
	}
    
    

}
