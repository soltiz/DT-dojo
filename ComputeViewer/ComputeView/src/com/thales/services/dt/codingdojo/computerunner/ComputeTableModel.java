package com.thales.services.dt.codingdojo.computerunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import com.thales.services.dt.codingdojo.computerunner.libs.sqrt.Sqrt;

public class ComputeTableModel extends AbstractTableModel {
	
	private int nbXValues;
    private double[] xValues;
    // ySeries are boxed Doubles in order to support null values
    private List<List<Double>> ySeries;
    
    private int getSeriesCount() { return ySeries.size();}  
  
    private Sqrt computeEngine=new Sqrt();
    
	
    public int getColumnCount() { return getSeriesCount()+1; }
    public int getRowCount() { return nbXValues;}
    public ComputeTableModel() {
    	super();
    	init(1,0,0);
    }
   public void init(int nbXValues, double minXValue, double xStep) {
	   this.nbXValues=nbXValues;
    	xValues=new double[nbXValues];
    	ySeries=new ArrayList<List<Double>>();
    	double xValue=minXValue;
    	for (int l=0;l<nbXValues;l++) {
    		xValues[l]=xValue;
    		xValue=xValue+xStep;
   	 	}
   	 	fireTableDataChanged();
    }
    
    
    public Object getValueAt(int row, int col) {
    	return getDoubleValueAt(row,col);
    }
    
    
    public Object getDoubleValueAt(int row, int col) {   	
    	if (col==0) {
    		return xValues[row];
    	} else {
    		return ySeries.get(col-1).get(row);
    	}
    }
    	
 
    public void computeSeries(int algoParam) {
    	List<Double> yValues=new ArrayList<Double>();
    	for (Double xValue : xValues) {
    		yValues.add(computeEngine.approxSqrt(xValue,algoParam));
    	}
    	ySeries.add(yValues);
    	fireTableStructureChanged();
  		 fireTableDataChanged();
    }
    
    
    private double[] getSerieYData(int serieIndex) {
    	double[] serieData=new double[nbXValues];
    	List<Double> serie=ySeries.get(serieIndex);
    	for (int row=0;row<nbXValues;row++) {
    		serieData[row]=serie.get(row);
    	}
    	return serieData;
    }
    
    private double[][] getSerieXYData(int serieIndex) {
    	double[][] serieData=new double[2][nbXValues];
    	serieData[0]=xValues;
    	serieData[1]=getSerieYData(serieIndex);
    	return serieData;
    }
    
	public XYDataset getXYDataset() {
		DefaultXYDataset ds = new DefaultXYDataset();
		
		for (int serieIndex=0;serieIndex<getSeriesCount();serieIndex++) {
			double[][] serieData=getSerieXYData(serieIndex);
	        ds.addSeries("series "+String.valueOf(serieIndex+1), serieData);
		}
		return ds;
	}

}
