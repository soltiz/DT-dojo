package com.thales.services.dt.codingdojo.computerunner;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DataExporterTest extends TestCase {

	public void testExport() throws IOException {
		DataExporter exporter = new DataExporter();
		Writer out = new StringWriter();

		double[] xValues = new double[] { 0, 1 };
		List<List<Double>> ySeries = new ArrayList<List<Double>>();
		ySeries.add(Arrays.asList(0.0, 1.14444444));
		ySeries.add(Arrays.asList(2.14, 3.85));

		exporter.export(out, xValues, ySeries);

		String result = out.toString();
		Assert.assertEquals("X;Y;Y\n0.0;0.0;2.14\n1.0;1.14444444;3.85\n",
				result);
	}

	public void testExport_YSerieNull() throws IOException {
		DataExporter exporter = new DataExporter();
		Writer out = new StringWriter();

		double[] xValues = new double[] { 0, 1 };
		List<List<Double>> ySeries = null;

		boolean exceptionOccured = false;
		try {
			exporter.export(out, xValues, ySeries);
		} catch (IllegalArgumentException e) {
			exceptionOccured = true;
		}
		Assert.assertTrue(exceptionOccured);
	}

	public void testExport_UneSerieNull() throws IOException {
		DataExporter exporter = new DataExporter();
		Writer out = new StringWriter();

		double[] xValues = new double[] { 0, 1 };
		List<List<Double>> ySeries = null;
		boolean exceptionOccured = false;

		try {
			exporter.export(out, xValues, ySeries);
		} catch (IllegalArgumentException e) {
			exceptionOccured = true;
		}
		Assert.assertTrue(exceptionOccured);
	}

	public void testExport_XSerieNull() throws IOException {
		DataExporter exporter = new DataExporter();
		Writer out = new StringWriter();

		double[] xValues = null;
		List<List<Double>> ySeries = new ArrayList<List<Double>>();
		ySeries.add(Arrays.asList(0.0, 1.14444444));
		ySeries.add(Arrays.asList(2.14, 3.85));

		boolean exceptionOccured = false;
		try {
			exporter.export(out, xValues, ySeries);
		} catch (IllegalArgumentException e) {
			exceptionOccured = true;
		}
		Assert.assertTrue(exceptionOccured);
	}
}
