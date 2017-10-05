package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;

public class LockTest {
	
	//Show state
	@Test
	public void showState(){
		Lock lock = new Lock("René","barbar","K9");
		
		assertNotNull(lock.getIdLock());
	}	
	
}
