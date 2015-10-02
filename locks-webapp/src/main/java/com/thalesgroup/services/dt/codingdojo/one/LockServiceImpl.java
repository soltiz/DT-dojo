package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;

public class LockServiceImpl implements LockService {

	private static int compteur = 0;
	private static final Long DEFAULT_DURATION = 10l;
	private static final Semaphore available = new Semaphore(8, true);
	private static Map<String, LockObject> locks = new HashMap<String, LockObject>();
	
	private static LockObject getLockIfValid(String owner)
	{
		LockObject obj = locks.get(owner);
		
		if (obj != null) {// verrou existant sur le topic
			if (obj.getExpirationDate().after(new Date())) 
			{
				// Valide
				return obj;
			}
			else
			{
				// Expiré
				locks.remove(owner);
				return null;
			}
		} else {
			// Y'a pas
			return null;
		}
	}
	
	@Override
	public LockObject putLockOn(String owner, String topic, Long duration) {
		LockObject obj = getLockIfValid(topic);
		if(duration == null){
			duration = DEFAULT_DURATION;
		}
		if (obj != null) {// verrou existant sur le topic
			if (obj.getOwner().equals(owner)) {// verrou déjà affecté au proprio
				return obj;
			} else   {
				throw new WebApplicationException(HttpStatus.CONFLICT_409);
			}
		}
		if (available.tryAcquire()) {
		compteur = compteur + 1;
		obj = new LockObject(owner, compteur, duration, topic);
		locks.put(topic, obj);
		available.release();
		return obj;
		} else {
			throw new WebApplicationException(503);
		}
	}

	@Override
	public LockObject getLockOn(String topic) {
		LockObject obj = getLockIfValid(topic);

		if (obj != null) {// verrou existant sur le topic
			return obj;

		} else {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
	};

}
