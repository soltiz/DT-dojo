package com.thalesgroup.services.dt.codingdojo.one;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class LockServiceImpl implements LockService {

	@Override
	public DemoObject getOneObject(String option, String objectName) {

		String newName=objectName;
		if (option != null) {
			newName=newName+"_withOption_"+option;
	}

		if (objectName.contentEquals("doesNotExist")) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		return new DemoObject(newName);
	};

}
