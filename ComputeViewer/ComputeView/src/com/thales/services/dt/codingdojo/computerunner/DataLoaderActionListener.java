package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataLoaderActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	public DataLoaderActionListener(ComputeTableModel dataModel) {
		this.dataModel=dataModel;
	}
	public void actionPerformed(ActionEvent e) {
		dataModel.init(20,0,0.4);
	}

}
