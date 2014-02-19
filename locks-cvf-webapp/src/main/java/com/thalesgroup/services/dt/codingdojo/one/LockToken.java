package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class LockToken {
	public LockToken(){
		
	}
	public LockToken(String owner, String topic) {
			this.owner=owner;
			this.topic=topic;
			this.issuingTime=new Date();
			this.refreshTime=(Date)issuingTime.clone();
					
	}
	@XmlAttribute
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getIssuingTime() {
		return issuingTime;
	}
	public void setIssuingTime(Date issuingTime) {
		this.issuingTime = issuingTime;
	}
	public Date getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Date refreshTime) {
		this.refreshTime = refreshTime;
	}
	public Date getTerminationTime() {
		return terminationTime;
	}
	public void setTerminationTime(Date terminationTime) {
		this.terminationTime = terminationTime;
	}
	private String owner;
	private String topic;
	private String id;
	private Date issuingTime;
	private Date refreshTime;
	private Date terminationTime;	
	

}
