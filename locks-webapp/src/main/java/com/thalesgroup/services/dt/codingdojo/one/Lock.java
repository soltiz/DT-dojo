package com.thalesgroup.services.dt.codingdojo.one;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lock {
	private String subject;
	private String owner;

	public Lock() {

	}

	public Lock(String subject, String owner) {
		super();
		this.subject = subject;
		this.owner = owner;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSubject() {
		return subject;
	}

	public String getOwner() {
		return owner;
	}

}
