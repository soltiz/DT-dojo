package com.thales.services.dt.codingdojo.computerunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ComputeEngineTest extends TestCase {

	final static String JAR1 = "sqrt-lib-0.0.1.jar";

	final static String JAR2 = "sqrt-lib-0.0.2.jar";

	public void testComputeOneSerie_withJar1() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IOException,
			URISyntaxException {
		int algoParam = 1;

		URI jarUri = getClass().getClassLoader().getResource(JAR1).toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();

		ComputeEngine computeEngine = new ComputeEngine(path);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		Double[] expectedResult = { 0.5, 0.7, 0.9, 1.1 };
		Double[] result = computeEngine.compute(algoParam, input);

		assertEquals(expectedResult.length, result.length);

		for (int i = 0; i < input.length; i++) {
			assertEquals(expectedResult[i], result[i]);
		}
	}

	public void testComputeOneSerie_withJar2() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IOException,
			URISyntaxException {
		int algoParam = 3;
		URI jarUri = getClass().getClassLoader().getResource(JAR2).toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();

		ComputeEngine computeEngine = new ComputeEngine(path);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		Double[] expectedResult = { 0.3, 0.7416666666666667, 1.0,
				1.2000000000000002 };
		Double[] result = computeEngine.compute(algoParam, input);

		assertEquals(expectedResult.length, result.length);

		for (int i = 0; i < input.length; i++) {
			assertEquals(expectedResult[i], result[i]);
		}
	}

	public void testComputeSeries() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IOException,
			URISyntaxException {
		List<Integer> algoParams = new ArrayList<Integer>();
		algoParams.add(1);
		algoParams.add(3);

		URI jarUri = getClass().getClassLoader().getResource(JAR1).toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();

		ComputeEngine computeEngine = new ComputeEngine(path);
		double[] input = { 0.0, 0.4, 0.8, 1.2000000000000002 };
		List<Double[]> expectedResults = new ArrayList<Double[]>();
		expectedResults.add(new Double[] { 0.5, 0.7, 0.9, 1.1 });
		expectedResults.add(new Double[] { 0.125, 0.6324638844301766,
				0.8944271911663216, 1.0954451150509243 });

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