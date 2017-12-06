package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;


public class AssertJLockServiceImplTest {
	
	private LockServiceImpl lockService = new LockServiceImpl();
	
	@Test
	public void placerLock(){
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		Long id = lock.getIdLock();

		lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		assertEquals(id, lock.getIdLock());
		
		Instant creationdate = lock.getCreationDate();
		
		//«SPECTACLE_!_PLACE_!_OWNER_!_AAAAMMJJHHmmss.mse »
		ZonedDateTime creationDt = ZonedDateTime.ofInstant(creationdate,ZoneOffset.UTC);

		String dataToSign = "OSS 117_!_g5_!_Bernard_!_" + DateTimeFormatter.ofPattern("YYYYMMddHHmmss").format(creationDt);
		
		long computedSignature = SignatureHelper.signatureOf(dataToSign);
		
		assertThat(computedSignature).isEqualTo(lock.getSignature());
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
		
		assertThat(listVerrouExistant)
			.hasSize(listeVerrousAttendus.size())
			.usingElementComparatorOnFields("owner", "place", "spectacleName")
			.containsAll(listeVerrousAttendus);	
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
		
		int nbMillisecondes = Duration.between(start, end).getNano()/1_000_000;
		
		System.out.println(nbMillisecondes);		
		assertThat(nbMillisecondes).isLessThan(1500);
	}	
}
