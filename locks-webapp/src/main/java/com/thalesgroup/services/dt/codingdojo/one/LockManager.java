package com.thalesgroup.services.dt.codingdojo.one;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

public class LockManager {

	private static LockManager lockManager = null;

	public static LockManager getInstance() {
		if (LockManager.lockManager == null) {
			LockManager.lockManager = new LockManager();
		}
		return LockManager.lockManager;
	}

	private LockManager() {
	}

	Map<String, Lock> locksElements = new HashMap<>();

	public Lock putLock(String subject, String owner)
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

			lock = new Lock(subject, owner);
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
		return locksElements.get(subject);
	}

}
