package com.thales.services.dt.codingdojo.computerunner;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.thales.services.dt.codingdojo.computerunner.libs.sqrt.Sqrt;

public class ComputeTableModel extends AbstractTableModel {

	
    public int getColumnCount() { if (yValues==null) { return 1;} else { return 2;} }
    public int getRowCount() { return xValues.size();}
    private List<Double> xValues=new ArrayList<Double>();
    private List<Double> yValues;
    private Sqrt computeEngine=new Sqrt();
    public void init() {
   	 for (int l=0;l<10;l++) {
   		 xValues.add(l/10.0);
   		 fireTableDataChanged();
   	 }
    }
    public Object getValueAt(int row, int col) { 
   	 if (col==0) {
   		 return xValues.get(row);
   	 } else if (col==1) {
   		 return yValues.get(row);
   	 } else { throw new IndexOutOfBoundsException("Column "+col+" does not exist in data model.");
   	 
   	 }
    }
    
    public void compute() {
    	yValues=new ArrayList<Double>();
    	for (Double xValue : xValues) {
    		yValues.add(computeEngine.approxSqrt(xValue,4));
    	}
    	fireTableStructureChanged();
  		 fireTableDataChanged();
    }

}
