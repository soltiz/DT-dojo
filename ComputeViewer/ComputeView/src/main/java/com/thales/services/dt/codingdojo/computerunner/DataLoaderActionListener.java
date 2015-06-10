package com.thales.services.dt.codingdojo.computerunner;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class DataLoaderActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	private Component parent;

	public DataLoaderActionListener(ComputeTableModel dataModel,
			Component parent) {
		this.dataModel = dataModel;
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Compute definition files", "xml");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			ComputeSpec spec = new ComputeSpec();
			spec.nbValues = 20;
			File file = new File(chooser.getSelectedFile().getName());

			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(ComputeSpec.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				spec = (ComputeSpec) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
			dataModel.init(spec.nbValues, spec.series, 0, 0.4);
		}
	}

}
