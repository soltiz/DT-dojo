package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.time.Instant;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MyInstantAdapter extends XmlAdapter<String,Instant> {

	@Override
	public String marshal(Instant v) throws Exception {
		// TODO Auto-generated method stub
		return v.toString();
	}

	@Override
	public Instant unmarshal(String v) throws Exception {
		throw new RuntimeException("Unmarshalling of date string into Instant not implemented");
	}



}
