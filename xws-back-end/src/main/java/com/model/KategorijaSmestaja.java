//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.10 at 08:04:01 PM CEST 
//


package com.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="Id_Kategorije" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Naziv_Kategorije" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Opis_Kategorije" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idKategorije",
    "nazivKategorije",
    "opisKategorije"
})
@XmlRootElement(name = "Kategorija_Smestaja")
public class KategorijaSmestaja {

    @XmlElement(name = "Id_Kategorije")
    protected int idKategorije;
    @XmlElement(name = "Naziv_Kategorije", required = true)
    protected String nazivKategorije;
    @XmlElement(name = "Opis_Kategorije", required = true)
    protected String opisKategorije;

    /**
     * Gets the value of the idKategorije property.
     * 
     */
    public int getIdKategorije() {
        return idKategorije;
    }

    /**
     * Sets the value of the idKategorije property.
     * 
     */
    public void setIdKategorije(int value) {
        this.idKategorije = value;
    }

    /**
     * Gets the value of the nazivKategorije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivKategorije() {
        return nazivKategorije;
    }

    /**
     * Sets the value of the nazivKategorije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivKategorije(String value) {
        this.nazivKategorije = value;
    }

    /**
     * Gets the value of the opisKategorije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpisKategorije() {
        return opisKategorije;
    }

    /**
     * Sets the value of the opisKategorije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpisKategorije(String value) {
        this.opisKategorije = value;
    }

}