package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;

public class LockManagerTest {

	private static final long TOLERENCE_IN_MS = 500;

	@Before
	public void setUp() throws Exception {
		LockManager.getInstance().reset();
	}
	
	@Ignore
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();
		manager.putLock(subject, owner, LockManager.DEFAULT_TTL);
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
		manager.putLock(subject, owner, LockManager.DEFAULT_TTL);
		Assert.assertEquals(1, LockManager.getInstance().getCountElement());
	}
	

	@Test
	public void testGetLock() {
		String subject = "subject";
		String owner1 = "owner1";
		LockManager manager = LockManager.getInstance();
		//TODO: refactorer pour simplifier et eviter les copier-coller
		
		manager.putLock(subject, owner1, LockManager.DEFAULT_TTL);
		
		Lock rereadLock = manager.getLock(subject);
		assertEquals(subject,rereadLock.getSubject());
		assertEquals(owner1,rereadLock.getOwner());
	
	}
	
	@Test
	public void testPutLock() {

		String subject = "subject";
		String owner = "owner";
		int timeToLiveInSecond = 3600;
		Calendar dateValidityCalendar = Calendar.getInstance();
		dateValidityCalendar.add(Calendar.SECOND, timeToLiveInSecond);
		Date dateValidity = dateValidityCalendar.getTime();
		
		LockManager manager = LockManager.getInstance();

		Lock lock = manager.putLock(subject, owner, timeToLiveInSecond);

		Assert.assertNotNull(lock);
		Assert.assertEquals(subject, lock.getSubject());
		Assert.assertEquals(owner, lock.getOwner());
		
		// Assert.assertEquals(dateValidity.getTime(), lock.getExpiryDate().getTime());
		Assert.assertTrue(equalsDate(dateValidity, lock.getExpiryDate()));
	}

	private boolean equalsDate(Date dateValidity, Date expiryDate) 
	{
		long date1 = dateValidity.getTime();
		long date2 = expiryDate.getTime();
		return Math.abs(date2 - date1) < TOLERENCE_IN_MS;
	}

	@Test
	public void testPutLockIdempotence() {
		String subject = "subject";
		String owner = "owner";
		LockManager manager = LockManager.getInstance();

		Lock lock1 = manager.putLock(subject, owner, LockManager.DEFAULT_TTL);
		Lock lock2 = manager.putLock(subject, owner, LockManager.DEFAULT_TTL);
		Assert.assertEquals(lock1.getSubject(), lock2.getSubject());
		Assert.assertEquals(lock1.getOwner(), lock2.getOwner());
	}

	@Test
	public void testPutLock1subject2owners() {
		String subject = "subject";
		String owner1 = "owner1";
		String owner2 = "owner2";
		LockManager manager = LockManager.getInstance();

		Lock lock1 = manager.putLock(subject, owner1, LockManager.DEFAULT_TTL);
		Assert.assertNotNull(lock1);
		try {
			manager.putLock(subject, owner2, LockManager.DEFAULT_TTL);
			Assert.fail();
		} catch (WebApplicationException we) {
			Assert.assertEquals(HttpStatus.CONFLICT_409, we.getResponse()
					.getStatus());
		}

	}

}
