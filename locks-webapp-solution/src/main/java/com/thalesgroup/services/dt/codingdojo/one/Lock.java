package com.thalesgroup.services.dt.codingdojo.one;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

@XmlRootElement
public class Lock {
	private String subject;
	private String owner;
	private Date expiryDate;
	private Long signature;
	private static CalendarProvider calendarProvider = new CalendarProvider();
	
	private final static String pattern = "yyyyMMddHHmmss.S";

	public static void setCalendarProvider(CalendarProvider calendarProvider) {
		Lock.calendarProvider = calendarProvider;
	}

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
		Calendar dateValidityCalendar = calendarProvider.getCalendarInstance();
		dateValidityCalendar.add(Calendar.SECOND, timeToLiveInSecond);
		this.expiryDate = dateValidityCalendar.getTime();

		computeSignature();
	}

	private void computeSignature(){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String lockExpiryDateRepresentation =  format.format(this.getExpiryDate());
		
		String dataToSign = subject+ "_!_" + owner + "_!_" + lockExpiryDateRepresentation;

		this.signature =  SignatureHelper.signatureOf(dataToSign);		
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

	public boolean hasExpired() {
		Date lockDate = this.getExpiryDate();
		Date now = calendarProvider.getCalendarInstance().getTime();
		
		
		if(lockDate == null || now.after(lockDate)){
			return true;
		}
		return false;
	}

	public Long getSignature() {
		return signature;
	}

	public void setSignature(Long signature) {
		this.signature = signature;
	}

}
