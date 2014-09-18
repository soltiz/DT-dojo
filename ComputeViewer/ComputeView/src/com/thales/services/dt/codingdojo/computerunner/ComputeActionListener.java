package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

public class ComputeActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	private XYPlot plot;
	private int currentAlgoParam;
	public ComputeActionListener(ComputeTableModel dataModel, XYPlot plot) {
		this.dataModel=dataModel;
		this.plot=plot;
		currentAlgoParam=0;
	}
	public void actionPerformed(ActionEvent e) {
		currentAlgoParam++;
		dataModel.computeSeries(currentAlgoParam);
        plot.setDataset(dataModel.getXYDataset());
		
	}

}
