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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{model}User"/>
 *         &lt;element ref="{model}Smestajna_Jedinica"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Od" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Do" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Ukupna_Cena" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="Realizovana" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user",
    "smestajnaJedinica"
})
@XmlRootElement(name = "Rezervacija")
@Entity(name = "Rezervacija")
@Table(name = "REZERVACIJA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Rezervacija
    implements Serializable, Equals, HashCode
{

    @XmlElement(name = "User", required = true)
    protected User user;
    @XmlElement(name = "Smestajna_Jedinica", required = true)
    protected SmestajnaJedinica smestajnaJedinica;
    @XmlAttribute(name = "Od")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar od;
    @XmlAttribute(name = "Do")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar _do;
    @XmlAttribute(name = "Ukupna_Cena")
    protected Double ukupnaCena;
    @XmlAttribute(name = "Realizovana")
    protected Boolean realizovana;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    @ManyToOne(targetEntity = User.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "USER__REZERVACIJA_HJID")
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the smestajnaJedinica property.
     * 
     * @return
     *     possible object is
     *     {@link SmestajnaJedinica }
     *     
     */
    @ManyToOne(targetEntity = SmestajnaJedinica.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "SMESTAJNA_JEDINICA_REZERVACI_0")
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
     * Gets the value of the ukupnaCena property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    @Basic
    @Column(name = "UKUPNA_CENA", precision = 20, scale = 10)
    public Double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Sets the value of the ukupnaCena property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUkupnaCena(Double value) {
        this.ukupnaCena = value;
    }

    /**
     * Gets the value of the realizovana property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Basic
    @Column(name = "REALIZOVANA")
    public Boolean isRealizovana() {
        return realizovana;
    }

    /**
     * Sets the value of the realizovana property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRealizovana(Boolean value) {
        this.realizovana = value;
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
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Rezervacija that = ((Rezervacija) object);
        {
            User lhsUser;
            lhsUser = this.getUser();
            User rhsUser;
            rhsUser = that.getUser();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "user", lhsUser), LocatorUtils.property(thatLocator, "user", rhsUser), lhsUser, rhsUser)) {
                return false;
            }
        }
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
        {
            Double lhsUkupnaCena;
            lhsUkupnaCena = this.getUkupnaCena();
            Double rhsUkupnaCena;
            rhsUkupnaCena = that.getUkupnaCena();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ukupnaCena", lhsUkupnaCena), LocatorUtils.property(thatLocator, "ukupnaCena", rhsUkupnaCena), lhsUkupnaCena, rhsUkupnaCena)) {
                return false;
            }
        }
        {
            Boolean lhsRealizovana;
            lhsRealizovana = this.isRealizovana();
            Boolean rhsRealizovana;
            rhsRealizovana = that.isRealizovana();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "realizovana", lhsRealizovana), LocatorUtils.property(thatLocator, "realizovana", rhsRealizovana), lhsRealizovana, rhsRealizovana)) {
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
            User theUser;
            theUser = this.getUser();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "user", theUser), currentHashCode, theUser);
        }
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
        {
            Double theUkupnaCena;
            theUkupnaCena = this.getUkupnaCena();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ukupnaCena", theUkupnaCena), currentHashCode, theUkupnaCena);
        }
        {
            Boolean theRealizovana;
            theRealizovana = this.isRealizovana();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "realizovana", theRealizovana), currentHashCode, theRealizovana);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
