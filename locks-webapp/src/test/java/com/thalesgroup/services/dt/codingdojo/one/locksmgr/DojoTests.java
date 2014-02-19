package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;

public class DojoTests {
	private static Logger log = LoggerFactory.getLogger(DojoTests.class);

	@Before
	public void initTest(){
		LockManager.getInstance().reset();
	}
	
	@Test
	public void testInit(){

		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();
		Lock lock = manager.putLock(subject, owner);
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
			Lock lock2 = manager.putLock(subject, owner2);
			Assert.fail();
		} catch (WebApplicationException we) {
			Assert.assertEquals(HttpStatus.CONFLICT_409, we.getResponse()
					.getStatus());
		}

	}

}
