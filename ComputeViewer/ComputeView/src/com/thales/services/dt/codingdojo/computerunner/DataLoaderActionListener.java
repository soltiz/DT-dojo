package com.thales.services.dt.codingdojo.computerunner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DataLoaderActionListener implements ActionListener {
	private ComputeTableModel dataModel;
	public DataLoaderActionListener(ComputeTableModel dataModel) {
		this.dataModel=dataModel;
	}
	public void actionPerformed(ActionEvent e) {
		ComputeSpec spec=new ComputeSpec();
		spec.nbValues=20;
		 File file = new File("spec.xml");
		    JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(ComputeSpec.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				spec=(ComputeSpec)jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
		dataModel.init(spec.nbValues,0,0.4);
	}

}
