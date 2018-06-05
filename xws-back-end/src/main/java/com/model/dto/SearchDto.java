package com.model.dto;

public class SearchDto {
	
	private String place;
	private String from;
	private String to;
	private String numberPerson;

	
	
	public SearchDto() {
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



	public SearchDto(String place, String from, String to, String numberPerson) {
		super();
		this.place = place;
		this.from = from;
		this.to = to;
		this.numberPerson = numberPerson;
	}



	@Override
	public String toString() {
		return "SearchDto [place=" + place + ", from=" + from + ", to=" + to + ", numberPerson=" + numberPerson + "]";
	}
	
	
	
	
	
	
}