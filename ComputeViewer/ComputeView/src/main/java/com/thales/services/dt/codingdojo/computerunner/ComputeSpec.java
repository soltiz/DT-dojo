package com.thales.services.dt.codingdojo.computerunner;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ComputeSpec {
	public int nbValues;
	
	@XmlElementWrapper(name="series")
	@XmlElement(name="serie")
	public List<Serie> series;
	
	static public class Serie {
		public int iterations;
		public String lib;		
	}
	
}
