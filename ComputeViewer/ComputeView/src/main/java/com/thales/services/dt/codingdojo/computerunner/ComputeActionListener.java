package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

public class ComputeActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	private XYPlot plot;
	public ComputeActionListener(ComputeTableModel dataModel, XYPlot plot) {
		this.dataModel=dataModel;
		this.plot=plot;
	}
	public void actionPerformed(ActionEvent e) {
		dataModel.computeSeries();
        plot.setDataset(dataModel.getXYDataset());
		
	}

}
