package com.thalesgroup.services.dt.codingdojo.one;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

@XmlRootElement
public class LockObject {
	
	private static final String SEPARATOR = "_!_";

	private static final Semaphore available = new Semaphore(2, true);

	private String owner;
	private int id;
	private Date creationDate;
	private Date expirationDate;
    private long signature;

	public LockObject() {
	}

	
	public long getSignature() {
		return signature;
	}


	public void setSignature(long signature) {
		this.signature = signature;
	}


	public LockObject(String owner, int id, long duration, String topic) {
		this.owner = owner;
		this.id = id;
		this.creationDate = new Date();
		this.expirationDate = new Date(creationDate.getTime() + (duration*1000));
	
		try {
			available.acquire();
			String expDate = new SimpleDateFormat("yyyyMMddHHmmss.S").format(expirationDate);
			String dataToSign = topic + SEPARATOR + owner + SEPARATOR + expDate;
			this.signature = SignatureHelper.signatureOf(dataToSign);
			available.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	@XmlAttribute
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
