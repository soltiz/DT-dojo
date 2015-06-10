package com.thalesgroup.services.dt.codingdojo.one;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

public class LockManager {

	private static LockManager lockManager = null;
	
	public static final int MAX_TTL = 3600;
	public static final int DEFAULT_TTL = MAX_TTL;

	public static LockManager getInstance() {
		if (LockManager.lockManager == null) {
			LockManager.lockManager = new LockManager();
		}
		return LockManager.lockManager;
	}

	private LockManager() {
	}

	Map<String, Lock> locksElements = new HashMap<>();

	public Lock putLock(String subject, String owner, int timeToLiveInSecond)
			throws WebApplicationException {
		Lock lock;

		if (locksElements.containsKey(subject)) {
			lock = locksElements.get(subject);
			if (lock.getOwner().equals(owner)) {
				return lock;
			} else {

				throw new WebApplicationException(409);
			}
		} else {

			lock = new Lock(subject, owner, timeToLiveInSecond);
			locksElements.put(subject, lock);
		}
		return lock;
	}

	public void reset() {
		locksElements.clear();
	}

	public int getCountElement() {
		return locksElements.size();
	}

	public Lock getLock(String subject) {
		// FIXME:renvoyer un clone car objet mutable
		
		Lock lock = locksElements.get(subject);
		
		if(lock != null) {
			
			if(lock.hasExpired()) {
				locksElements.remove(subject);
				lock = null;
			}
		}
		
		return lock;
	}

}
