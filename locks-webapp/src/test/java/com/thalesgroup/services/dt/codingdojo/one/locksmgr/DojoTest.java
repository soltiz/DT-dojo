package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;
import com.thalesgroup.services.dt.codingdojo.one.LockService;
import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;

public class DojoTest {
	private static Logger log = LoggerFactory.getLogger(DojoTest.class);

	@Before
	public void initTest(){
		LockManager.getInstance().reset();
	}
	
	@Test
	public void testInit(){

		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();
		manager.putLock(subject, owner);
		Assert.assertEquals(1, LockManager.getInstance().getCountElement());
		LockManager.getInstance().reset();
		Assert.assertEquals(0, LockManager.getInstance().getCountElement());
		
	}
	
	@Test
	public void sayHello() {
		log.debug("Hello from test #{}", 0);
		assertTrue(true);
	}
	
	
	@Test
	public void testPutVerrou() {

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
	public void testPut2Verrous() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();

		Lock lock1 = manager.putLock(subject, owner);
		Lock lock2 = manager.putLock(subject, owner);
		Assert.assertEquals(lock1.getSubject(), lock2.getSubject());
		Assert.assertEquals(lock1.getOwner(), lock2.getOwner());
	}

	@Test
	public void testPut1subject2owners() {
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
	@Test
	public void getExistingLockDetail() {
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
