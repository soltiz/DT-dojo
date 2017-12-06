package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

public class LockServiceImplTest {
	
	private LockServiceImpl lockService = new LockServiceImpl();
	
	@Test
	public void placerLock(){
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		Instant creationdate = lock.getCreationDate();
		
		//«SPECTACLE_!_PLACE_!_OWNER_!_AAAAMMJJHHmmss.mse »
		ZonedDateTime creationDt = ZonedDateTime.ofInstant(creationdate,ZoneOffset.UTC);

		String dataToSign = "OSS 117_!_g5_!_Bernard_!_" + DateTimeFormatter.ofPattern("YYYYMMddHHmmss").format(creationDt);
		
		Long computedSignature = SignatureHelper.signatureOf(dataToSign);
		
		assertEquals(computedSignature, lock.getSignature());
	}
	
	@Test
	public void placerLockIndempotence(){
		Lock lock = lockService.placerVerrou("Johnny", "OSS 117", "g5");
		
		Long id = lock.getIdLock();
		
		String haliday = "John";

		lock = lockService.placerVerrou(haliday+"ny", "OSS 117", "g5");
		
		assertEquals(id, lock.getIdLock());
	}
	
	@Test
	public void spectacleNoneExistent(){
		
		try {
			lockService.consulterVerrou("LeSpectacleNestPasUnSpectacleSVP", "g6");
			fail();
		}
		catch(WebApplicationException wae) {
			assertEquals(HttpStatus.NOT_FOUND_404, wae.getResponse().getStatus());
		}
	}
	
	@Test
	public void consulterVerrou(){
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");		
		Lock lock2 = lockService.consulterVerrou("OSS 117", "g5");
		
		assertEquals(lock2, lock);
	}
	
	@Test
	public void consulterVerrouSiInexistant() {	
			
		try {
			lockService.consulterVerrou("OSS 117", "g6");
			fail();
		}
		catch(WebApplicationException wae) {
			assertEquals(HttpStatus.NOT_FOUND_404, wae.getResponse().getStatus());
		}
	}
	
	@Test
	public void consulterListeVerrou(){
		
		// liste attendu de verrou
		List<Lock> listeVerrousAttendus = new ArrayList<>();
		listeVerrousAttendus.add(new Lock("Marcel", "mon super spectacle", "K10"));
		listeVerrousAttendus.add(new Lock("Etienne", "mon super spectacle", "K11"));
		listeVerrousAttendus.add(new Lock("Paul", "mon super spectacle", "K09"));
		
		for (Lock lock : listeVerrousAttendus) {
			lockService.placerVerrou(lock.getOwner(), lock.getSpectacleName(), lock.getPlace());
		}
		lockService.placerVerrou("toto", "le spectacle de toto", "Z0");
		
		// je récupere les verrous du spectacle
		List<Lock> listVerrouExistant = new ArrayList<>(lockService.getVerrous("mon super spectacle"));		
		
//		Comparator<? super Lock> lockComparator2 = new Comparator<Lock>() {
//
//			@Override
//			public int compare(Lock a, Lock b) {
//				a.getIdLock().compareTo(b.getIdLock());
//			}
//		};
		
		Comparator<? super Lock> lockComparator = (a,b)->(a.getIdLock().compareTo(b.getIdLock()));
		listeVerrousAttendus.sort(lockComparator);
		listVerrouExistant.sort(lockComparator);
		
		Assert.assertEquals(listeVerrousAttendus.size(), listVerrouExistant.size());
		
		Iterator<Lock> monIterateur = listVerrouExistant.iterator();
		for (Lock monVerrouAttendu : listeVerrousAttendus) {
			
			Utils.compareLock(monVerrouAttendu, monIterateur.next());

		}
	
		//will fail
		//Assert.assertEquals(listeVerrousAttendus, listVerrouExistant);	
	}
	

	@Test
	public void placerVerrouConflictuel(){
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");		
				
		try {
			Lock lock2 = lockService.placerVerrou("Patrick", "OSS 117", "g5");
			fail();
		}
		catch(WebApplicationException wae) {
			assertEquals(HttpStatus.CONFLICT_409, wae.getResponse().getStatus());
		}
	}
	
	@After
	public void nettoyerStore(){
		// Nettoyage de la map
		LockServiceImpl.clearStore();
	}

	@Test
	public void verifierExpiration() {
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");

		assertEquals(lock.getExpirationDate(), lock.getCreationDate().plusSeconds(1800));
	}
	
	@Test
	public void comparaisonVerrouExpire(){
		
		lockService.placerVerrou("Bernard", "OSS 117", "g5");
		//setter la date dexpiration
		
		Lock lock = lockService.consulterVerrou("OSS 117", "g5");
		lock.setDateCreation(lock.getCreationDate().minusSeconds(1900));
		lock.setDateExpiration(lock.getExpirationDate().minusSeconds(1900));
		
		try {
			lockService.consulterVerrou("OSS 117", "g5");
			fail();
		}
		catch(WebApplicationException wae) {
			assertEquals(HttpStatus.NOT_FOUND_404, wae.getResponse().getStatus());
		}
	}
	
	@Test
	public void verifierPerformances() {
		
		int i = 0;
		
		Instant start = Instant.now();
		// Lancement des 8 requêtes PUT
		while(i <= 7) {
			Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "G"+i);
			i++;
		}
		
		Instant end = Instant.now();
		
	    long nbMillisecondes = ChronoUnit.MILLIS.between(start,end);

		//int nbMillisecondes = Duration.between(start, end). ) .getNano()/1_000_000;
		
		System.out.println(nbMillisecondes);
		Assert.assertTrue(nbMillisecondes < 1500);
	}	
	
	@Test()
	public void placerLockSimulanee() throws InterruptedException {

		for (int k = 0; k < 100; k++) {
			AtomicBoolean lundesdeusthreadnapasreussi = new AtomicBoolean(false);
			List<Thread> threadList = new ArrayList<>();

			for (int j = 0; j < 2; j++) {

				class myRunnable implements Runnable {

					int id;
					int place;

					public myRunnable(int id, int place) {
						this.id = id;
						this.place = place;
					}

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							lockService.placerVerrou("Johnny" + id, "OSS 117", "g5" + place);
						} catch (WebApplicationException wae) {
							lundesdeusthreadnapasreussi.set(true);
						}
					}
				}
				;

				Runnable runnable = new myRunnable(j, k);
				Thread monThread = new Thread(runnable);
				monThread.start();
				threadList.add(monThread);
			}

			// On attend la fin des threads
			for (Thread thread : threadList) {
				thread.join();
			}
			Assert.assertTrue(lundesdeusthreadnapasreussi.get());
		}
	}
	
	@Test()
	public void placerLockPerformances() throws InterruptedException {

			List<Thread> threadList = new ArrayList<>();
			
			Instant start = Instant.now();
			AtomicInteger errorCounter = new AtomicInteger(0);
			AtomicBoolean signatureDeMerde = new AtomicBoolean(false);
			
			for (int j = 0; j < 16; j++) {

				class myRunnable implements Runnable {

					int place;

					public myRunnable(int place) {
						this.place = place;
					}

					@Override
					public void run() {
						try{
							// TODO Auto-generated method stub
							Instant startplacerVerrou = Instant.now();
							lockService.placerVerrou("Johnny" + place, "OSS 117", "g5" + place);							
							Instant endplacerVerrou = Instant.now();
							
							long nbMillisecondesplacerVerrou = ChronoUnit.MILLIS.between(startplacerVerrou,endplacerVerrou);
							
							System.out.println("Le thread " + place + " a mis " + nbMillisecondesplacerVerrou + "ms à s'éxécuter.");
							
							if(nbMillisecondesplacerVerrou > 1000) {
								// On remonte l'erreur 
								System.out.println("Le thread " + place + " a mis trop de temps.");
								errorCounter.incrementAndGet();
							}
						}
						catch(Exception ex){
							signatureDeMerde.set(true);
						}
					}
				}
				;
				
				Runnable runnable = new myRunnable(j);
				Thread monThread = new Thread(runnable);
				monThread.start();
				threadList.add(monThread);
			}

			// On attend la fin des threads
			for (Thread thread : threadList) {
				thread.join();
			}
			Instant end = Instant.now();
			
			long nbMillisecondes = ChronoUnit.MILLIS.between(start,end);
			
			Assert.assertTrue(nbMillisecondes < 1000);
			Assert.assertEquals(0, errorCounter.get());
			Assert.assertFalse(signatureDeMerde.get());
			
		}
	
}
