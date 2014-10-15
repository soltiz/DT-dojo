package com.thales.services.dt.codingdojo.computerunner;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ComputeSpec {
	public int nbValues;
	
	public List<Integer> steps;
}
