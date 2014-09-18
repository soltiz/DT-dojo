package com.thales.services.dt.codingdojo.computerunner.libs.sqrt;

public class Sqrt {
	public double computeSqrt(double x) {
		return Math.sqrt(x);
	}
	
	public double approxSqrt(double x, double requestedPrecisionRatio) {
		double approx=1.0;
		Double obtainedPrecision=null;
		do {
			double newApprox=approx-(approx*approx-x)/(2*approx);
			obtainedPrecision=Math.abs((newApprox-approx)/newApprox);
		} while (obtainedPrecision>requestedPrecisionRatio);
		return approx;
	}
}
