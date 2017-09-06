package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;

public class LockServiceImplTest {

	@Test
	public void placerLock(){
		
		LockServiceImpl lockService = new LockServiceImpl();
		Lock lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		Long id = lock.getIdLock();

		lock = lockService.placerVerrou("Bernard", "OSS 117", "g5");
		
		assertEquals(id, lock.getIdLock());
		
	}

}
