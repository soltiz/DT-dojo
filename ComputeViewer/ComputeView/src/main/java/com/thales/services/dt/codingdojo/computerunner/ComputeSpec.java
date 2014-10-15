package com.thales.services.dt.codingdojo.computerunner;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ComputeSpec {
	public int nbValues;
	
	@XmlElementWrapper(name="steps")
	@XmlElement(name="step")
	public List<Integer> steps;
}
