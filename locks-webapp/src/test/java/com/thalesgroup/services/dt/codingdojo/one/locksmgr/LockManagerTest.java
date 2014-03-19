package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;

public class LockManagerTest {

	@Before
	public void setUp() throws Exception {
		LockManager.getInstance().reset();
	}
	
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();
		manager.putLock(subject, owner);
		Assert.assertEquals(1, LockManager.getInstance().getCountElement());
		LockManager.getInstance().reset();
		Assert.assertEquals(0, LockManager.getInstance().getCountElement());
	}

	@Test
	public void testGetCountElement() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();
		Assert.assertEquals(0, LockManager.getInstance().getCountElement());
		manager.putLock(subject, owner);
		Assert.assertEquals(1, LockManager.getInstance().getCountElement());
	}
	

	@Test
	public void testGetLock() {
		String subject = "subject";
		String owner1 = "owner1";
		LockManager manager = LockManager.getInstance();
		//TODO: refactorer pour simplifier et eviter les copier-coller
		
		manager.putLock(subject, owner1);
		
		Lock rereadLock = manager.getLock(subject);
		assertEquals(subject,rereadLock.getSubject());
		assertEquals(owner1,rereadLock.getOwner());
	
	}
	
	@Test
	public void testPutLock() {

		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();

		Lock lock = manager.putLock(subject, owner);

		Assert.assertNotNull(lock);
		Assert.assertEquals(subject, lock.getSubject());
		Assert.assertEquals(owner, lock.getOwner());

		assertTrue(true);
	}

	@Test
	public void testPutLockIdempotence() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();

		Lock lock1 = manager.putLock(subject, owner);
		Lock lock2 = manager.putLock(subject, owner);
		Assert.assertEquals(lock1.getSubject(), lock2.getSubject());
		Assert.assertEquals(lock1.getOwner(), lock2.getOwner());
	}

	@Test
	public void testPutLock1subject2owners() {
		String subject = "subject";
		String owner1 = "owner1";
		String owner2 = "owner2";
		LockManager manager = LockManager.getInstance();

		Lock lock1 = manager.putLock(subject, owner1);
		Assert.assertNotNull(lock1);
		try {
			manager.putLock(subject, owner2);
			Assert.fail();
		} catch (WebApplicationException we) {
			Assert.assertEquals(HttpStatus.CONFLICT_409, we.getResponse()
					.getStatus());
		}

	}

}
