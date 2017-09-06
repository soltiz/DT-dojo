package com.thalesgroup.services.dt.codingdojo.one;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Lock {
	
	private Long idLock;
	private String owner;
	
	private static Long compteur=0L;
	
	public Lock(){
		
	}
	
	public Lock(String owner){
		this.idLock =  nextId();
		this.owner = owner;
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
	
	

}
