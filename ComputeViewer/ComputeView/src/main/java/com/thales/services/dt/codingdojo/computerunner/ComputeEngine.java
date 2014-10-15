package com.thales.services.dt.codingdojo.computerunner;

import java.util.ArrayList;
import java.util.List;

import com.thales.services.dt.codingdojo.computerunner.libs.sqrt.Sqrt;

public class ComputeEngine {

	
	
	private Sqrt computeEngine=new Sqrt();
	 
 ComputeEngine(String jAR1) {
	 //TODO Here check we can load the compute engine jar
	}

	public Double[] compute(int algoParam, double[] input) {
		Double result[] = new Double[input.length];
		for (int i = 0; i < input.length; i++) {
			//TODO Here we must change implementation of compute library
			// depending on parameter read in the compute xml.
    		result[i] = computeEngine.approxSqrt(input[i], algoParam);
    	}
		return result;
	}

	public List<Double[]> compute(List<Integer> algoParams, double[] input) {
		
		List<Double[]> results = new ArrayList<Double[]>();
		for (int i = 0; i < algoParams.size(); i++) {
			results.add(compute(algoParams.get(i), input));
		}
		return results;
	}

	
	
}
