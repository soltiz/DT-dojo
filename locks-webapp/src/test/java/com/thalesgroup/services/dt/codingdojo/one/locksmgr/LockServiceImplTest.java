package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;
import com.thalesgroup.services.dt.codingdojo.one.LockService;
import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;

public class LockServiceImplTest {

	@Before
	public void setUp() throws Exception {
		LockManager.getInstance().reset();
	}

	@Test
	public void testPutLock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLock() {
		fail("Not yet implemented");
	}

	@Test
	public void getExistingLockExistingSubject() {
		String subject = "subject";
		String owner1 = "owner1";
		LockService ls = new LockServiceImpl();
		//TODO: refactorer pour simplifier et eviter les copier-coller
		
		ls.putLock(subject, owner1);
		
		Lock lock2 = ls.getLock(subject);
		
		assertEquals(owner1,lock2.getOwner());
		assertEquals(subject,lock2.getSubject());
	}

	@Test
	public void getNonExistingLockDetail() {
		
		String subject = "subject";
		LockService ls = new LockServiceImpl();
		try {
			ls.getLock(subject);
			fail();
			
		} catch (WebApplicationException we) {
			Assert.assertEquals(HttpStatus.NOT_FOUND_404, we.getResponse()
					.getStatus());
		}
		
	}

}
