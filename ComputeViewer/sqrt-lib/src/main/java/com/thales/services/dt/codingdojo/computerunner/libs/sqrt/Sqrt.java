package com.thales.services.dt.codingdojo.computerunner.libs.sqrt;

public class Sqrt {
	public double computeSqrt(double x) {
		return Math.sqrt(x);
	}
	
	public double approxSqrt(double x, int nbIterations) {
		double approx=1.0;
		for (int k=0; k<nbIterations; k++) {
			approx=approx- (approx*approx-x)/(2*approx);
		}
		return approx;
	}
}
