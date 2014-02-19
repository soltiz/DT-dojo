package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.NotFoundException;



public class LockServiceImpl implements LockService {

	@Override
	public DemoObject getOneObject(String option, String objectName) {

		if (objectName.contentEquals("doesNotExist")) {
			NotFoundException e = new NotFoundException();
			throw e;
		}
		DemoObject o = new DemoObject(objectName + "_" + option);
		return o;
	}

	@Override
	public Lock getLock(String subject, String owner) {
		return LockManager.getInstance().putLock(subject, owner);
	}
}
