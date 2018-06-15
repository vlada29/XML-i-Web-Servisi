package com.model.dto;

public class MessageDto {
	
	private String content;
	private String agent;
	private String user;

	
	
	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MessageDto(String content, String agent, String user) {
		super();
		this.content = content;
		this.agent = agent;
		this.user = user;
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
		return "MessageDto [content=" + content + ", agent=" + agent + ", user=" + user + "]";
	}
	
	
	
	
	
	
}