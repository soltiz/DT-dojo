package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement

public class DemoObject {
	public DemoObject(){
		
	}
	public DemoObject(String objectName) {
			this.name=objectName;
					
	}
	@XmlAttribute
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String name;	
	

}
