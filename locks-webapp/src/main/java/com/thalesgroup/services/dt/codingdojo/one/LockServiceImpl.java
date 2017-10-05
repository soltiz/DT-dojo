package com.thalesgroup.services.dt.codingdojo.one;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;


public class LockServiceImpl implements LockService {
	
	//Map<"spectacleName",Map<"placeName",Lock>>
	private static Map<String,Spectacle> store = new HashMap<>();
	
/*
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
	}
*/
	@Override
	public Lock placerVerrou(String userName, String spectacleName, String placeName) {
		Lock lock;
		
		if(!store.containsKey(spectacleName)){
			lock = new Lock(userName,spectacleName,placeName);
			Spectacle spectacle = new Spectacle();
			spectacle.setLockForPlace(placeName, lock);
			store.put(spectacleName, spectacle);
		}
		else{
			if(store.get(spectacleName).getLockForPlace(placeName) == null){
				lock = new Lock(userName,spectacleName,placeName);
				store.get(spectacleName).setLockForPlace(placeName, lock);
				store.put(spectacleName, store.get(spectacleName));
			}
			else{
				lock = store.get(spectacleName).getLockForPlace(placeName);
			}
		}
		return lock;
	};
	
	public static void clearStore(){
		store.clear();
	}

	@Override
	public Lock consulterVerrou(String spectacleName, String placeName) {
		
		Spectacle spectacle = store.get(spectacleName);
		
		if (spectacle == null || spectacle.getLockForPlace(placeName) == null) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		
		return spectacle.getLockForPlace(placeName);
	}
	
	
	public Collection<Lock> getVerrous(String nomSpectacle) {
		
		Spectacle spectacle = store.get(nomSpectacle);
		
		if (spectacle == null ) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		return spectacle.getAllLocks();
	}
	

}
