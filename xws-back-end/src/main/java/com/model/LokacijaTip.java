//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.01 at 05:25:45 PM CEST 
//


package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Lokacija_Tip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Lokacija_Tip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ulica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Broj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Grad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Drzava" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Lokacija_Tip", propOrder = {
    "ulica",
    "broj",
    "grad",
    "drzava"
})
@Entity(name = "LokacijaTip")
@Table(name = "LOKACIJA_TIP")
@Inheritance(strategy = InheritanceType.JOINED)
public class LokacijaTip
    implements Serializable, Equals, HashCode
{

    @XmlElement(name = "Ulica", required = true)
    protected String ulica;
    @XmlElement(name = "Broj", required = true)
    protected String broj;
    @XmlElement(name = "Grad", required = true)
    protected String grad;
    @XmlElement(name = "Drzava", required = true)
    protected String drzava;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;

    /**
     * Gets the value of the ulica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "ULICA", length = 255)
    public String getUlica() {
        return ulica;
    }

    /**
     * Sets the value of the ulica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUlica(String value) {
        this.ulica = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "BROJ", length = 255)
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroj(String value) {
        this.broj = value;
    }

    /**
     * Gets the value of the grad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "GRAD", length = 255)
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrad(String value) {
        this.grad = value;
    }

    /**
     * Gets the value of the drzava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "DRZAVA", length = 255)
    public String getDrzava() {
        return drzava;
    }

    /**
     * Sets the value of the drzava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrzava(String value) {
        this.drzava = value;
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

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof LokacijaTip)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final LokacijaTip that = ((LokacijaTip) object);
        {
            String lhsUlica;
            lhsUlica = this.getUlica();
            String rhsUlica;
            rhsUlica = that.getUlica();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ulica", lhsUlica), LocatorUtils.property(thatLocator, "ulica", rhsUlica), lhsUlica, rhsUlica)) {
                return false;
            }
        }
        {
            String lhsBroj;
            lhsBroj = this.getBroj();
            String rhsBroj;
            rhsBroj = that.getBroj();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "broj", lhsBroj), LocatorUtils.property(thatLocator, "broj", rhsBroj), lhsBroj, rhsBroj)) {
                return false;
            }
        }
        {
            String lhsGrad;
            lhsGrad = this.getGrad();
            String rhsGrad;
            rhsGrad = that.getGrad();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "grad", lhsGrad), LocatorUtils.property(thatLocator, "grad", rhsGrad), lhsGrad, rhsGrad)) {
                return false;
            }
        }
        {
            String lhsDrzava;
            lhsDrzava = this.getDrzava();
            String rhsDrzava;
            rhsDrzava = that.getDrzava();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "drzava", lhsDrzava), LocatorUtils.property(thatLocator, "drzava", rhsDrzava), lhsDrzava, rhsDrzava)) {
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
            String theUlica;
            theUlica = this.getUlica();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ulica", theUlica), currentHashCode, theUlica);
        }
        {
            String theBroj;
            theBroj = this.getBroj();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "broj", theBroj), currentHashCode, theBroj);
        }
        {
            String theGrad;
            theGrad = this.getGrad();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "grad", theGrad), currentHashCode, theGrad);
        }
        {
            String theDrzava;
            theDrzava = this.getDrzava();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "drzava", theDrzava), currentHashCode, theDrzava);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
