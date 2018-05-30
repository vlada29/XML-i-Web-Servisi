package com.soapservices;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.Message;
import com.model.Rezervacija;

@XmlRootElement(name = "Mess_Wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessWrapper {
	@XmlElementWrapper(name="messages")
	@XmlElement(name="Message")
	private List<Message> messages;

	@XmlElement(name = "hjid")
	private Long hjid;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Long getHjid() {
		return hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

}
