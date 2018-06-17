package com.model.dto;

public class MessageDto {
	
	private String content;
	private String agent;
	private String user;
	private String datum;
	private String naslov;

	
	
	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	


	public MessageDto(String content, String agent, String user, String datum, String naslov) {
		super();
		this.content = content;
		this.agent = agent;
		this.user = user;
		this.datum = datum;
		this.naslov = naslov;
	}






	public String getDatum() {
		return datum;
	}






	public void setDatum(String datum) {
		this.datum = datum;
	}






	public String getNaslov() {
		return naslov;
	}






	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}






	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAgent() {
		return agent;
	}



	public void setAgent(String agent) {
		this.agent = agent;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "MessageDto [content=" + content + ", agent=" + agent + ", user=" + user + ", datum=" + datum
				+ ", naslov=" + naslov + "]";
	}
	
	
	
	
	
	
}