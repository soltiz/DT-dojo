package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class LockServiceImpl implements LockService {

	@Override
	public Lock putLock(String subject, String owner) {
		return LockManager.getInstance().putLock(subject, owner);
	}

	@Override
	public Lock getLock(String subject) throws WebApplicationException{
		Lock lock = LockManager.getInstance().getLock(subject);
		
		if(lock == null) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}

		return lock;
	}
}
