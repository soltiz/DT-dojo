package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportActionListener implements ActionListener {

	private ComputeTableModel dataModel;
	
	public ExportActionListener(ComputeTableModel dataModel) {
		if(dataModel == null) {
			throw new IllegalArgumentException();
		}
		this.dataModel=dataModel;
	}

	public void actionPerformed(ActionEvent e) {
		
		DataExporter export = new DataExporter();
		export.exportToFile(dataModel.getxValues(), dataModel.getySeries());
	}



}
