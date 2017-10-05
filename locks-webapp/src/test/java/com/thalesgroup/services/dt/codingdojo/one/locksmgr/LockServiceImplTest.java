package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.AssertThrows;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;

public class LockServiceImplTest {
	
	private LockServiceImpl lockService = new LockServiceImpl();
	
	@Test
	public void placerLock(){
		
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		Long id = lock.getIdLock();

		lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
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
		
		
		lockService.placerVerrou("Bernard", "OSS 117", "g5");		
		
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
		
	
		//will fail
		Assert.assertEquals(listeVerrousAttendus, listVerrouExistant);	
	}
	
	@After
	public void nettoyerStore(){
		// Nettoyage de la map
		LockServiceImpl.clearStore();
	}

}
