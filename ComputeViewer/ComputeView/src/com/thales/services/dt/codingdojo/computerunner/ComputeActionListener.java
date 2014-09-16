package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputeActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	public ComputeActionListener(ComputeTableModel dataModel) {
		this.dataModel=dataModel;
	}
	public void actionPerformed(ActionEvent e) {
		dataModel.compute();
	}

}
