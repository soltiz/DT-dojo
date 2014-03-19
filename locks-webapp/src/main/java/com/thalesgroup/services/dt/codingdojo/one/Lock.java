package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Lock {
	private String subject;
	private String owner;
	private Date expiryDate;

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public Lock() {

	}

	public Lock(String subject, String owner, int timeToLiveInSecond) {
		super();
		this.subject = subject;
		this.owner = owner;
		Calendar dateValidityCalendar = Calendar.getInstance();
		dateValidityCalendar.add(Calendar.SECOND, timeToLiveInSecond);
		this.expiryDate = dateValidityCalendar.getTime();
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
