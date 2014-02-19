package com.thalesgroup.services.dt.codingdojo.one;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ClientErrorException;

public class LockServiceImpl implements LockService {

	private List<LockToken> tokens=new ArrayList<LockToken> ();
	
	public LockToken setOrRefreshLock(String ownerName, String topic) {
		if (topic.contains("lockedTopic")) {	
			throw new ClientErrorException(409);
		}
		
		LockToken token = new LockToken(ownerName, topic);
		tokens.add(token);
		return token;
	}




}
