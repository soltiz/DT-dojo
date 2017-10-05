package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.Spectacle;

public class SpectacleTest {

	
	//LOCK >> SPECTACLE
	//SPECTACLE get(placeName) >> LOCK ?
	//
	@Test
	public void testPutAndGetLockForSeat() {
		Spectacle spectacle = new Spectacle();
		Lock lock = new Lock("Bill", "Barbar", "k9");
		spectacle.setLockForPlace("K9", lock);

		assertSame(lock,spectacle.getLockForPlace("K9"));
		assertNotSame(lock, spectacle.getLockForPlace("K8"));
		
	}
	
	@Test
	public void testGetLockOnEmptySeat() {
		Spectacle spectacle = new Spectacle();
		
		//test lorsqu'il n'y a pas de lock sur la place K8
		assertNull(spectacle.getLockForPlace("K8"));
		
	}
	

}
