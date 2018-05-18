//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.18 at 05:09:50 PM CEST 
//


package com.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Id_Komentara" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Smestajna_Jedinica" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}User"/>
 *         &lt;element name="Sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Odobren" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idKomentara",
    "smestajnaJedinica",
    "user",
    "sadrzaj"
})
@XmlRootElement(name = "Komentar")
public class Komentar {

    @XmlElement(name = "Id_Komentara")
    protected int idKomentara;
    @XmlElement(name = "Smestajna_Jedinica")
    protected int smestajnaJedinica;
    @XmlElement(name = "User", required = true)
    protected User user;
    @XmlElement(name = "Sadrzaj", required = true)
    protected String sadrzaj;
    @XmlAttribute(name = "Odobren")
    protected Boolean odobren;

    /**
     * Gets the value of the idKomentara property.
     * 
     */
    public int getIdKomentara() {
        return idKomentara;
    }

    /**
     * Sets the value of the idKomentara property.
     * 
     */
    public void setIdKomentara(int value) {
        this.idKomentara = value;
    }

    /**
     * Gets the value of the smestajnaJedinica property.
     * 
     */
    public int getSmestajnaJedinica() {
        return smestajnaJedinica;
    }

    /**
     * Sets the value of the smestajnaJedinica property.
     * 
     */
    public void setSmestajnaJedinica(int value) {
        this.smestajnaJedinica = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
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
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSadrzaj(String value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the odobren property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOdobren() {
        return odobren;
    }

    /**
     * Sets the value of the odobren property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOdobren(Boolean value) {
        this.odobren = value;
    }

}
