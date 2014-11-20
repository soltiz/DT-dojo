package com.thales.services.dt.codingdojo.computerunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ComputeEngine {

	Method approxSqrt;
	private Object computeEngine;

	ComputeEngine(String jAR1) throws IOException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException {
		// TODO Here check we can load the compute engine jar
		JarClassLoader jarClassLoader = new JarClassLoader(new File(jAR1));
		Class<?> computeEngineClass = jarClassLoader
				.findClass("com.thales.services.dt.codingdojo.computerunner.libs.sqrt.Sqrt");
		computeEngine = computeEngineClass.newInstance();
		approxSqrt = computeEngineClass.getMethod("approxSqrt", double.class,
				int.class);
	}

	public Double[] compute(int algoParam, double[] input) {
		Double result[] = new Double[input.length];
		for (int i = 0; i < input.length; i++) {
			// TODO Here we must change implementation of compute library
			// depending on parameter read in the compute xml.
			try {
				result[i] = (Double) approxSqrt.invoke(computeEngine, input[i],
						algoParam);
			} catch (Exception e) {
				result[i] = 0.0;
				System.err.println("Erreur au cours de approxSqrt");
				e.printStackTrace();
			}
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
