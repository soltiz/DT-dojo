package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Spectacle {
	
	private Map<String,Lock> lockByPlaceNameMap = new HashMap<>();
	
	public void setLockForPlace(String placeName, Lock lock){
		lockByPlaceNameMap.put(placeName, lock);
	}
	
	public Lock getLockForPlace(String placeName){
		return lockByPlaceNameMap.get(placeName);
	}
	
	public Collection<Lock> getAllLocks(){
		return lockByPlaceNameMap.values();
	}
	
}
