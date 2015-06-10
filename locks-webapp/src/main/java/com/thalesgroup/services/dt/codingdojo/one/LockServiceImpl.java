package com.thalesgroup.services.dt.codingdojo.one;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class LockServiceImpl implements LockService {

	private static int compteur = 0;
	private static final Long DEFAULT_DURATION = 10l;
	private static Map<String, LockObject> locks = new HashMap<String, LockObject>();

	@Override
	public LockObject putLockOn(String owner, String topic, Long duration) {
		LockObject obj = locks.get(topic);
		if(duration == null){
			duration = DEFAULT_DURATION;
		}
		if (obj != null) {// verrou existant sur le topic
			if (obj.getOwner().equals(owner)) {// verrou déjà affecté au proprio
				return obj;
			} else {
				throw new WebApplicationException(HttpStatus.CONFLICT_409);
			}
		}
		compteur = compteur + 1;
		obj = new LockObject(owner, compteur, duration);
		locks.put(topic, obj);
		return obj;
	}

	@Override
	public LockObject getLockOn(String topic) {
		LockObject obj = locks.get(topic);
		if(obj == null) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		} else {
			return obj;
		}
	};

}
