package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;


public class LockServiceImpl implements LockService {
	
	//Map<"spectacleName",Map<"placeName",Lock>>
	private static Map<String,Spectacle> store = new HashMap<>();
	private static AtomicInteger signEnCours = new AtomicInteger(0);
	
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
			
		synchronized(this){
			if(!store.containsKey(spectacleName)){
				//Le spectacle n'existe pas 
				
				lock = new Lock(userName,spectacleName,placeName);
				Spectacle spectacle = new Spectacle();
				spectacle.setLockForPlace(placeName, lock);
				store.put(spectacleName, spectacle);
			}
			else{
				lock = store.get(spectacleName).getLockForPlace(placeName);
				if(lock == null){
					//Le spectacle existe mais la place est libre 
					lock = new Lock(userName,spectacleName,placeName);
					store.get(spectacleName).setLockForPlace(placeName, lock);
					store.put(spectacleName, store.get(spectacleName));
				}
				else{
					//Le spectacle existe et la place est occupée
					if(!lock.getOwner().equals(userName)){
						throw new WebApplicationException(HttpStatus.CONFLICT_409);
					}
				}
			}
		}
		
		if(lock.getSignature() == null ){
			boolean jepeuxsigner = false;
			while (!jepeuxsigner){
				synchronized (signEnCours) {
					if (signEnCours.get() < 2){
						signEnCours.incrementAndGet();
						jepeuxsigner = true;
					}
				}
				if (!jepeuxsigner){
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
				}
			}
			
			lock.sign();
			signEnCours.decrementAndGet();
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
			//spectacle n'existe pas ou verrou inexistant
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		
		Lock lock = spectacle.getLockForPlace(placeName);
		
		if(lock.getExpirationDate().isBefore(Instant.now())){
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		
		return lock;
	}
	
	
	public Collection<Lock> getVerrous(String nomSpectacle) {
		
		Spectacle spectacle = store.get(nomSpectacle);
		
		if (spectacle == null ) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		return spectacle.getAllLocks();
	}
	

}
