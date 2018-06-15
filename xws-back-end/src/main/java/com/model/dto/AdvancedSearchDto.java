package com.model.dto;

import java.util.ArrayList;

public class AdvancedSearchDto {
	
	private String place;
	private String from;
	private String to;
	private String numberPerson;
	private ArrayList<DodatnaDto> dodatne;
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



	public ArrayList<DodatnaDto> getDodatne() {
		return dodatne;
	}



	public void setDodatne(ArrayList<DodatnaDto> dodatne) {
		this.dodatne = dodatne;
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



	@Override
	public String toString() {
		return "AdvancedSearchDto [place=" + place + ", from=" + from + ", to=" + to + ", numberPerson=" + numberPerson
				+ ", dodatne=" + dodatne + ", kategorija=" + kategorija + ", tip=" + tip + "]";
	}
	
	


}