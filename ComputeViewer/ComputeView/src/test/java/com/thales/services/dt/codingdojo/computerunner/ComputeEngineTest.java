package com.thales.services.dt.codingdojo.computerunner;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ComputeEngineTest extends TestCase {
	final static String JAR1="./libs/sqrt-lib-0.0.1.jar";
	
	public void testComputeOneSerie_withJar1() {
		int algoParam = 1;
		ComputeEngine computeEngine = new ComputeEngine(JAR1);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		Double[] expectedResult = { 0.5, 0.7, 0.9, 1.1 };
		Double[] result = computeEngine.compute(algoParam, input);

		assertEquals(expectedResult.length, result.length);
		
		for (int i=0; i < input.length; i++) {
			assertEquals(expectedResult[i], result[i]);
		}
	}
	
	public void testComputeOneSerie_withJar2() {
		String JAR2="./libs/sqrt-lib-0.0.1.jar";
		int algoParam = 1;
		ComputeEngine computeEngine = new ComputeEngine(JAR2);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		Double[] expectedResult = { 0.5, 0.7, 0.9, 1.1 };
		Double[] result = computeEngine.compute(algoParam, input);

		assertEquals(expectedResult.length, result.length);
		
		for (int i=0; i < input.length; i++) {
			assertEquals(expectedResult[i], result[i]);
		}
	}
	public void testComputeSeries() {
		List<Integer> algoParams = new ArrayList<Integer>();
		algoParams.add(1);
		algoParams.add(3);
		
		ComputeEngine computeEngine = new ComputeEngine(JAR1);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		List<Double[]> expectedResults = new ArrayList<Double[]>();
		expectedResults.add(new Double[]{ 0.5, 0.7, 0.9, 1.1 });
		expectedResults.add(new Double[]{ 0.125, 0.6324638844301766, 0.8944271911663216, 1.0954451150509243 });
		
		List<Double[]> results = computeEngine.compute(algoParams, input);

		assertEquals(expectedResults.size(), results.size());
		
		
		for (int i = 0; i < expectedResults.size(); i++) {
			assertEquals(expectedResults.get(i).length, results.get(i).length);
			for (int j = 0; j < expectedResults.get(i).length; j++) {
				assertEquals(expectedResults.get(i)[j], results.get(i)[j]);
			}
		}
	}

}