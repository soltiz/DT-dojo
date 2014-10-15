package com.thales.services.dt.codingdojo.computerunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class DataExporter {

	public void export(Writer out, double[] xValues, List<List<Double>> ySeries) throws IOException {
		
		verifySeries(xValues, ySeries);
		
		out.append("X");
		for (int i = 0; i < ySeries.size(); i++) {
			out.append(";Y");
		}
		out.append("\n");
		
		for (int i = 0; i < xValues.length; i++) {
			out.append(Double.toString(xValues[i]));
			
			for (int j = 0; j < ySeries.size(); j++) {
				out.append(";").append(Double.toString(ySeries.get(j).get(i)));
			}
			out.append("\n");
		}
	}

	private void verifySeries(double[] xValues, List<List<Double>> ySeries) {
		if (xValues == null || ySeries == null) {
			throw new IllegalArgumentException("xValues and YSeries cannot be null");
		}
		if (ySeries.contains(null)){
			throw new IllegalArgumentException("an Yserie");
		}
	}

	public void exportToFile(double[] xValues, List<List<Double>> ySeries) {
		File exportFile = new File("export.csv");
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(exportFile);
		
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			export(writer, xValues, ySeries);
			
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	
}
