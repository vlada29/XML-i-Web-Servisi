//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.01 at 05:25:45 PM CEST 
//


package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;   
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.jvnet.hyperjaxb3.item.ItemUtils;
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
 *         &lt;element ref="{model}Agent"/>
 *         &lt;element name="Lokacija" type="{model}Lokacija_Tip"/>
 *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Broj_Osoba" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{model}Kategorija_Smestaja"/>
 *         &lt;element ref="{model}Tip_Smestaja"/>
 *         &lt;sequence>
 *           &lt;element name="Picture" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;element name="Usluge" type="{model}Usluga_Jedinice" maxOccurs="unbounded"/>
 *         &lt;element name="Cene" type="{model}Plan_Cena" maxOccurs="unbounded"/>
 *         &lt;element name="ListaZauzetosti" type="{model}Zauzetost_Jedinice" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="slike" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Dostupna" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agent",
    "lokacija",
    "naziv",
    "opis",
    "brojOsoba",
    "kategorijaSmestaja",
    "tipSmestaja",
    "picture",
    "usluge",
    "cene",
    "listaZauzetosti",
    "slike"
})
@XmlRootElement(name = "Smestajna_Jedinica")
@Entity(name = "SmestajnaJedinica")
@Table(name = "SMESTAJNA_JEDINICA")
@Inheritance(strategy = InheritanceType.JOINED)
public class SmestajnaJedinica
    implements Serializable, Equals, HashCode
{

    @XmlElement(name = "Agent", required = true)
    protected Agent agent;
    @XmlElement(name = "Lokacija", required = true)
    protected LokacijaTip lokacija;
    @XmlElement(name = "Naziv", required = true)
    protected String naziv;
    @XmlElement(name = "Opis", required = true)
    protected String opis;
    @XmlElement(name = "Broj_Osoba")
    protected int brojOsoba;
    @XmlElement(name = "Kategorija_Smestaja", required = true)
    protected KategorijaSmestaja kategorijaSmestaja;
    @XmlElement(name = "Tip_Smestaja", required = true)
    protected TipSmestaja tipSmestaja;
    @XmlElement(name = "Picture", required = true)
    protected List<byte[]> picture;
    @XmlElement(name = "Usluge", required = true)
    protected List<UslugaJedinice> usluge;
    @XmlElement(name = "Cene", required = true)
    protected List<PlanCena> cene;
    @XmlElement(name = "ListaZauzetosti")
    protected List<ZauzetostJedinice> listaZauzetosti;
    @XmlElement(required = true)
    protected List<String> slike;
    @XmlAttribute(name = "Dostupna")
    protected Boolean dostupna;
    @XmlAttribute(name = "Hjid")
    protected Long hjid;
    protected transient List<SmestajnaJedinicaPictureItem> pictureItems;
    protected transient List<SmestajnaJedinicaSlikeItem> slikeItems;
    private transient double trenutnaCena;
    private transient double trenutnaOcena;
 

    public double getTrenutnaCena() {
		return trenutnaCena;
	}

	public void setTrenutnaCena(double trenutnaCena) {
		this.trenutnaCena = trenutnaCena;
	}
	
	public double getTrenutnaOcena() {
		return trenutnaOcena;
	}

	public void setTrenutnaOcena(double trenutnaOcena) {
		this.trenutnaOcena = trenutnaOcena;
	}

	/**
     * Gets the value of the agent property.
     * 
     * @return
     *     possible object is
     *     {@link Agent }
     *     
     */
    @ManyToOne(targetEntity = Agent.class, cascade = {
        CascadeType.MERGE
    })
    @JoinColumn(name = "AGENT_SMESTAJNA_JEDINICA_HJID")
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Agent }
     *     
     */
    public void setAgent(Agent value) {
        this.agent = value;
    }

    /**
     * Gets the value of the lokacija property.
     * 
     * @return
     *     possible object is
     *     {@link LokacijaTip }
     *     
     */
    @ManyToOne(targetEntity = LokacijaTip.class, cascade = {
        CascadeType.ALL
    })
    @JoinColumn(name = "LOKACIJA_SMESTAJNA_JEDINICA__0")
    public LokacijaTip getLokacija() {
        return lokacija;
    }

    /**
     * Sets the value of the lokacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link LokacijaTip }
     *     
     */
    public void setLokacija(LokacijaTip value) {
        this.lokacija = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "NAZIV", length = 255)
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Basic
    @Column(name = "OPIS", length = 255)
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the brojOsoba property.
     * 
     */
    @Basic
    @Column(name = "BROJ_OSOBA", precision = 10, scale = 0)
    public int getBrojOsoba() {
        return brojOsoba;
    }

    /**
     * Sets the value of the brojOsoba property.
     * 
     */
    public void setBrojOsoba(int value) {
        this.brojOsoba = value;
    }

    /**
     * Gets the value of the kategorijaSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link KategorijaSmestaja }
     *     
     */
    @ManyToOne(targetEntity = KategorijaSmestaja.class, cascade = {
        CascadeType.MERGE
    })
    @JoinColumn(name = "KATEGORIJA_SMESTAJA_SMESTAJN_0")
    public KategorijaSmestaja getKategorijaSmestaja() {
        return kategorijaSmestaja;
    }

    /**
     * Sets the value of the kategorijaSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link KategorijaSmestaja }
     *     
     */
    public void setKategorijaSmestaja(KategorijaSmestaja value) {
        this.kategorijaSmestaja = value;
    }

    /**
     * Gets the value of the tipSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link TipSmestaja }
     *     
     */
    @ManyToOne(targetEntity = TipSmestaja.class, cascade = {
        CascadeType.MERGE
    })
    @JoinColumn(name = "TIP_SMESTAJA_SMESTAJNA_JEDIN_0")
    public TipSmestaja getTipSmestaja() {
        return tipSmestaja;
    }

    /**
     * Sets the value of the tipSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipSmestaja }
     *     
     */
    public void setTipSmestaja(TipSmestaja value) {
        this.tipSmestaja = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the picture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPicture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    @Transient
    public List<byte[]> getPicture() {
        if (picture == null) {
            picture = new ArrayList<byte[]>();
        }
        return this.picture;
    }

    /**
     * 
     * 
     */
    public void setPicture(List<byte[]> picture) {
        this.picture = picture;
    }

    /**
     * Gets the value of the usluge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usluge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsluge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UslugaJedinice }
     * 
     * 
     */
    @OneToMany(targetEntity = UslugaJedinice.class, cascade = {
       CascadeType.ALL 
	}
	)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "USLUGE_SMESTAJNA_JEDINICA_HJ_0")//, updatable = false, insertable = false)
    public List<UslugaJedinice> getUsluge() {
        if (usluge == null) {
            usluge = new ArrayList<UslugaJedinice>();
        }
        return this.usluge;
    }

    /**
     * 
     * 
     */
    public void setUsluge(List<UslugaJedinice> usluge) {
        this.usluge = usluge;
    }

    /**
     * Gets the value of the cene property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cene property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCene().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlanCena }
     * 
     * 
     */
    @OneToMany(targetEntity = PlanCena.class, cascade = {
        CascadeType.ALL
    })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "CENE_SMESTAJNA_JEDINICA_HJID")
    public List<PlanCena> getCene() {
        if (cene == null) {
            cene = new ArrayList<PlanCena>();
        }
        return this.cene;
    }

    /**
     * 
     * 
     */
    public void setCene(List<PlanCena> cene) {
        this.cene = cene;
    }

    /**
     * Gets the value of the listaZauzetosti property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaZauzetosti property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaZauzetosti().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZauzetostJedinice }
     * 
     * 
     */
//    @OneToMany(targetEntity = ZauzetostJedinice.class, cascade = {
//        CascadeType.ALL
//    })
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JoinColumn(name = "LISTA_ZAUZETOSTI_SMESTAJNA_J_0")
//    public List<ZauzetostJedinice> getListaZauzetosti() {
//        if (listaZauzetosti == null) {
//            listaZauzetosti = new ArrayList<ZauzetostJedinice>();
//        }
//        return this.listaZauzetosti;
//    }

    @OneToMany(targetEntity = ZauzetostJedinice.class, cascade = {
            CascadeType.ALL
        }, orphanRemoval=true)
        @LazyCollection(LazyCollectionOption.FALSE)
        @JoinColumn(name = "LISTA_ZAUZETOSTI_SMESTAJNA_J_0")
        public List<ZauzetostJedinice> getListaZauzetosti() {
            if (listaZauzetosti == null) {
                listaZauzetosti = new ArrayList<ZauzetostJedinice>();
            }
            return this.listaZauzetosti;
        }
    
    /**
     * 
     * 
     */
    public void setListaZauzetosti(List<ZauzetostJedinice> listaZauzetosti) {
        this.listaZauzetosti = listaZauzetosti;
    }

    /**
     * Gets the value of the slike property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slike property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlike().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    @Transient
    public List<String> getSlike() {
        if (slike == null) {
            slike = new ArrayList<String>();
        }
        return this.slike;
    }

    /**
     * 
     * 
     */
    public void setSlike(List<String> slike) {
        this.slike = slike;
    }

    /**
     * Gets the value of the dostupna property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Basic
    @Column(name = "DOSTUPNA")
    public Boolean isDostupna() {
        return dostupna;
    }

    /**
     * Sets the value of the dostupna property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDostupna(Boolean value) {
        this.dostupna = value;
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

    @OneToMany(targetEntity = SmestajnaJedinicaPictureItem.class, cascade = {
        CascadeType.ALL
    })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "PICTURE_ITEMS_SMESTAJNA_JEDI_0")
    public List<SmestajnaJedinicaPictureItem> getPictureItems() {
        if (this.pictureItems == null) {
            this.pictureItems = new ArrayList<SmestajnaJedinicaPictureItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.picture)) {
            this.picture = ItemUtils.wrap(this.picture, this.pictureItems, SmestajnaJedinicaPictureItem.class);
        }
        return this.pictureItems;
    }

    public void setPictureItems(List<SmestajnaJedinicaPictureItem> value) {
        this.picture = null;
        this.pictureItems = null;
        this.pictureItems = value;
        if (this.pictureItems == null) {
            this.pictureItems = new ArrayList<SmestajnaJedinicaPictureItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.picture)) {
            this.picture = ItemUtils.wrap(this.picture, this.pictureItems, SmestajnaJedinicaPictureItem.class);
        }
    }

    @OneToMany(targetEntity = SmestajnaJedinicaSlikeItem.class, cascade = {
        CascadeType.ALL
    })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "SLIKE_ITEMS_SMESTAJNA_JEDINI_0")
    public List<SmestajnaJedinicaSlikeItem> getSlikeItems() {
        if (this.slikeItems == null) {
            this.slikeItems = new ArrayList<SmestajnaJedinicaSlikeItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.slike)) {
            this.slike = ItemUtils.wrap(this.slike, this.slikeItems, SmestajnaJedinicaSlikeItem.class);
        }
        return this.slikeItems;
    }

    public void setSlikeItems(List<SmestajnaJedinicaSlikeItem> value) {
        this.slike = null;
        this.slikeItems = null;
        this.slikeItems = value;
        if (this.slikeItems == null) {
            this.slikeItems = new ArrayList<SmestajnaJedinicaSlikeItem>();
        }
        if (ItemUtils.shouldBeWrapped(this.slike)) {
            this.slike = ItemUtils.wrap(this.slike, this.slikeItems, SmestajnaJedinicaSlikeItem.class);
        }
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SmestajnaJedinica)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SmestajnaJedinica that = ((SmestajnaJedinica) object);
        {
            Agent lhsAgent;
            lhsAgent = this.getAgent();
            Agent rhsAgent;
            rhsAgent = that.getAgent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "agent", lhsAgent), LocatorUtils.property(thatLocator, "agent", rhsAgent), lhsAgent, rhsAgent)) {
                return false;
            }
        }
        {
            LokacijaTip lhsLokacija;
            lhsLokacija = this.getLokacija();
            LokacijaTip rhsLokacija;
            rhsLokacija = that.getLokacija();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lokacija", lhsLokacija), LocatorUtils.property(thatLocator, "lokacija", rhsLokacija), lhsLokacija, rhsLokacija)) {
                return false;
            }
        }
        {
            String lhsNaziv;
            lhsNaziv = this.getNaziv();
            String rhsNaziv;
            rhsNaziv = that.getNaziv();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "naziv", lhsNaziv), LocatorUtils.property(thatLocator, "naziv", rhsNaziv), lhsNaziv, rhsNaziv)) {
                return false;
            }
        }
        {
            String lhsOpis;
            lhsOpis = this.getOpis();
            String rhsOpis;
            rhsOpis = that.getOpis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "opis", lhsOpis), LocatorUtils.property(thatLocator, "opis", rhsOpis), lhsOpis, rhsOpis)) {
                return false;
            }
        }
        {
            int lhsBrojOsoba;
            lhsBrojOsoba = this.getBrojOsoba();
            int rhsBrojOsoba;
            rhsBrojOsoba = that.getBrojOsoba();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "brojOsoba", lhsBrojOsoba), LocatorUtils.property(thatLocator, "brojOsoba", rhsBrojOsoba), lhsBrojOsoba, rhsBrojOsoba)) {
                return false;
            }
        }
        {
            KategorijaSmestaja lhsKategorijaSmestaja;
            lhsKategorijaSmestaja = this.getKategorijaSmestaja();
            KategorijaSmestaja rhsKategorijaSmestaja;
            rhsKategorijaSmestaja = that.getKategorijaSmestaja();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "kategorijaSmestaja", lhsKategorijaSmestaja), LocatorUtils.property(thatLocator, "kategorijaSmestaja", rhsKategorijaSmestaja), lhsKategorijaSmestaja, rhsKategorijaSmestaja)) {
                return false;
            }
        }
        {
            TipSmestaja lhsTipSmestaja;
            lhsTipSmestaja = this.getTipSmestaja();
            TipSmestaja rhsTipSmestaja;
            rhsTipSmestaja = that.getTipSmestaja();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tipSmestaja", lhsTipSmestaja), LocatorUtils.property(thatLocator, "tipSmestaja", rhsTipSmestaja), lhsTipSmestaja, rhsTipSmestaja)) {
                return false;
            }
        }
        {
            List<byte[]> lhsPicture;
            lhsPicture = (((this.picture!= null)&&(!this.picture.isEmpty()))?this.getPicture():null);
            List<byte[]> rhsPicture;
            rhsPicture = (((that.picture!= null)&&(!that.picture.isEmpty()))?that.getPicture():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "picture", lhsPicture), LocatorUtils.property(thatLocator, "picture", rhsPicture), lhsPicture, rhsPicture)) {
                return false;
            }
        }
        {
            List<UslugaJedinice> lhsUsluge;
            lhsUsluge = (((this.usluge!= null)&&(!this.usluge.isEmpty()))?this.getUsluge():null);
            List<UslugaJedinice> rhsUsluge;
            rhsUsluge = (((that.usluge!= null)&&(!that.usluge.isEmpty()))?that.getUsluge():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usluge", lhsUsluge), LocatorUtils.property(thatLocator, "usluge", rhsUsluge), lhsUsluge, rhsUsluge)) {
                return false;
            }
        }
        {
            List<PlanCena> lhsCene;
            lhsCene = (((this.cene!= null)&&(!this.cene.isEmpty()))?this.getCene():null);
            List<PlanCena> rhsCene;
            rhsCene = (((that.cene!= null)&&(!that.cene.isEmpty()))?that.getCene():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cene", lhsCene), LocatorUtils.property(thatLocator, "cene", rhsCene), lhsCene, rhsCene)) {
                return false;
            }
        }
        {
            List<ZauzetostJedinice> lhsListaZauzetosti;
            lhsListaZauzetosti = (((this.listaZauzetosti!= null)&&(!this.listaZauzetosti.isEmpty()))?this.getListaZauzetosti():null);
            List<ZauzetostJedinice> rhsListaZauzetosti;
            rhsListaZauzetosti = (((that.listaZauzetosti!= null)&&(!that.listaZauzetosti.isEmpty()))?that.getListaZauzetosti():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "listaZauzetosti", lhsListaZauzetosti), LocatorUtils.property(thatLocator, "listaZauzetosti", rhsListaZauzetosti), lhsListaZauzetosti, rhsListaZauzetosti)) {
                return false;
            }
        }
        {
            List<String> lhsSlike;
            lhsSlike = (((this.slike!= null)&&(!this.slike.isEmpty()))?this.getSlike():null);
            List<String> rhsSlike;
            rhsSlike = (((that.slike!= null)&&(!that.slike.isEmpty()))?that.getSlike():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "slike", lhsSlike), LocatorUtils.property(thatLocator, "slike", rhsSlike), lhsSlike, rhsSlike)) {
                return false;
            }
        }
        {
            Boolean lhsDostupna;
            lhsDostupna = this.isDostupna();
            Boolean rhsDostupna;
            rhsDostupna = that.isDostupna();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dostupna", lhsDostupna), LocatorUtils.property(thatLocator, "dostupna", rhsDostupna), lhsDostupna, rhsDostupna)) {
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
            Agent theAgent;
            theAgent = this.getAgent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "agent", theAgent), currentHashCode, theAgent);
        }
        {
            LokacijaTip theLokacija;
            theLokacija = this.getLokacija();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lokacija", theLokacija), currentHashCode, theLokacija);
        }
        {
            String theNaziv;
            theNaziv = this.getNaziv();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "naziv", theNaziv), currentHashCode, theNaziv);
        }
        {
            String theOpis;
            theOpis = this.getOpis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "opis", theOpis), currentHashCode, theOpis);
        }
        {
            int theBrojOsoba;
            theBrojOsoba = this.getBrojOsoba();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "brojOsoba", theBrojOsoba), currentHashCode, theBrojOsoba);
        }
        {
            KategorijaSmestaja theKategorijaSmestaja;
            theKategorijaSmestaja = this.getKategorijaSmestaja();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "kategorijaSmestaja", theKategorijaSmestaja), currentHashCode, theKategorijaSmestaja);
        }
        {
            TipSmestaja theTipSmestaja;
            theTipSmestaja = this.getTipSmestaja();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tipSmestaja", theTipSmestaja), currentHashCode, theTipSmestaja);
        }
        {
            List<byte[]> thePicture;
            thePicture = (((this.picture!= null)&&(!this.picture.isEmpty()))?this.getPicture():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "picture", thePicture), currentHashCode, thePicture);
        }
        {
            List<UslugaJedinice> theUsluge;
            theUsluge = (((this.usluge!= null)&&(!this.usluge.isEmpty()))?this.getUsluge():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usluge", theUsluge), currentHashCode, theUsluge);
        }
        {
            List<PlanCena> theCene;
            theCene = (((this.cene!= null)&&(!this.cene.isEmpty()))?this.getCene():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cene", theCene), currentHashCode, theCene);
        }
        {
            List<ZauzetostJedinice> theListaZauzetosti;
            theListaZauzetosti = (((this.listaZauzetosti!= null)&&(!this.listaZauzetosti.isEmpty()))?this.getListaZauzetosti():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "listaZauzetosti", theListaZauzetosti), currentHashCode, theListaZauzetosti);
        }
        {
            List<String> theSlike;
            theSlike = (((this.slike!= null)&&(!this.slike.isEmpty()))?this.getSlike():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "slike", theSlike), currentHashCode, theSlike);
        }
        {
            Boolean theDostupna;
            theDostupna = this.isDostupna();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dostupna", theDostupna), currentHashCode, theDostupna);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

	@Override
	public String toString() {
		return "SmestajnaJedinica [agent=" + agent + ", lokacija=" + lokacija + ", naziv=" + naziv + ", opis=" + opis
				+ ", brojOsoba=" + brojOsoba + ", kategorijaSmestaja=" + kategorijaSmestaja + ", tipSmestaja="
				+ tipSmestaja + ", picture=" + picture + ", usluge=" + usluge + ", cene=" + cene + ", listaZauzetosti="
				+ listaZauzetosti + ", slike=" + slike + ", dostupna=" + dostupna + ", hjid=" + hjid + ", trenutnaCena="
				+ trenutnaCena + ", trenutnaOcena=" + trenutnaOcena + "]";
	}

    


    

}
