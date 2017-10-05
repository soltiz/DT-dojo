package com.thalesgroup.services.dt.codingdojo.one;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Lock {
	
	private Long idLock;
	private String owner;
	private String spectacleName;
	private String place;
	
	private static Long compteur=0L;
	
	public Lock(){
		
	}
	
	public Lock(String owner, String spectacleName, String place){
		this.idLock =  nextId();
		this.owner = owner;
		this.spectacleName = spectacleName;
		this.place = place;
	}
	
	private synchronized static Long nextId(){
		return compteur++;
	}
	public Long getIdLock() {
		return idLock;
	}
	public String getOwner() {
		return owner;
	}

	public String getSpectacleName() {
		return spectacleName;
	}

	public void setSpectacleName(String spectacleName) {
		this.spectacleName = spectacleName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
