package com.model.dto;

public class DodatnaDto {
	
	private String nazivUsluge;
	private boolean checked;

	
	
	public DodatnaDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getNazivUsluge() {
		return nazivUsluge;
	}



	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}



	public boolean isChecked() {
		return checked;
	}



	public void setChecked(boolean checked) {
		this.checked = checked;
	}



	@Override
	public String toString() {
		return "DodatnaDto [nazivUsluge=" + nazivUsluge + ", checked=" + checked + "]";
	}
	
	
	
	
}