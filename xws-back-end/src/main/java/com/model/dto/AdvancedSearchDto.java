package com.model.dto;

public class AdvancedSearchDto {
	
	private String place;
	private String from;
	private String to;
	private String numberPerson;
	private boolean parking;
	private boolean wifi;
	private boolean dorucak;
	private boolean polupansion;
	private boolean pansion;
	private boolean tv;
	private boolean kuhinja;
	private boolean kupatilo;
	private String kategorija;
	private String tip;
	

	
	
	public AdvancedSearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getPlace() {
		return place;
	}



	public void setPlace(String place) {
		this.place = place;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getNumberPerson() {
		return numberPerson;
	}



	public void setNumberPerson(String numberPerson) {
		this.numberPerson = numberPerson;
	}



	public boolean isParking() {
		return parking;
	}



	public void setParking(boolean parking) {
		this.parking = parking;
	}



	public boolean isWifi() {
		return wifi;
	}



	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}



	public boolean isDorucak() {
		return dorucak;
	}



	public void setDorucak(boolean dorucak) {
		this.dorucak = dorucak;
	}



	public boolean isPolupansion() {
		return polupansion;
	}



	public void setPolupansion(boolean polupansion) {
		this.polupansion = polupansion;
	}



	public boolean isPansion() {
		return pansion;
	}



	public void setPansion(boolean pansion) {
		this.pansion = pansion;
	}



	public boolean isTv() {
		return tv;
	}



	public void setTv(boolean tv) {
		this.tv = tv;
	}



	public boolean isKuhinja() {
		return kuhinja;
	}



	public void setKuhinja(boolean kuhinja) {
		this.kuhinja = kuhinja;
	}



	public boolean isKupatilo() {
		return kupatilo;
	}



	public void setKupatilo(boolean kupatilo) {
		this.kupatilo = kupatilo;
	}
	
	



	public String getKategorija() {
		return kategorija;
	}



	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}



	public String getTip() {
		return tip;
	}



	public void setTip(String tip) {
		this.tip = tip;
	}



	public AdvancedSearchDto(String place, String from, String to, String numberPerson, boolean parking, boolean wifi,
			boolean dorucak, boolean polupansion, boolean pansion, boolean tv, boolean kuhinja, boolean kupatilo,
			String kategorija, String tip) {
		super();
		this.place = place;
		this.from = from;
		this.to = to;
		this.numberPerson = numberPerson;
		this.parking = parking;
		this.wifi = wifi;
		this.dorucak = dorucak;
		this.polupansion = polupansion;
		this.pansion = pansion;
		this.tv = tv;
		this.kuhinja = kuhinja;
		this.kupatilo = kupatilo;
		this.kategorija = kategorija;
		this.tip = tip;
	}



	@Override
	public String toString() {
		return "AdvancedSearchDto [place=" + place + ", from=" + from + ", to=" + to + ", numberPerson=" + numberPerson
				+ ", parking=" + parking + ", wifi=" + wifi + ", dorucak=" + dorucak + ", polupansion=" + polupansion
				+ ", pansion=" + pansion + ", tv=" + tv + ", kuhinja=" + kuhinja + ", kupatilo=" + kupatilo
				+ ", kategorija=" + kategorija + ", tip=" + tip + "]";
	}



	


	
	
	
	
	
}