package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LockObject {

	private String owner;
	private int id;
	private Date creationDate;
	private Date expirationDate;

	public LockObject() {
	}

	public LockObject(String owner, int id, long duration) {
		this.owner = owner;
		this.id = id;
		this.creationDate = new Date();
		this.expirationDate = new Date(creationDate.getTime() + (duration*1000));
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
