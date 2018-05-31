package com.model.dto;

public class RegistrationUserDto {
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String passwordConfirmation;
	
	
	public RegistrationUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	



	public String getFirstname() {
		return firstname;
	}





	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}





	public String getLastname() {
		return lastname;
	}





	public void setLastname(String lastname) {
		this.lastname = lastname;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}





	public void setPasswordconfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}





	@Override
	public String toString() {
		return "RegistrationUserDto [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", passwordConfirmation=" + passwordConfirmation + "]";
	}
	
	

	
	

}
