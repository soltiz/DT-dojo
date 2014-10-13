package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.WebApplicationException;

import junit.framework.Assert;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
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

	@Ignore
	public void testPutLock() {
		fail("Not yet implemented");
	}

	@Ignore
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
	
	@Test
	public void putMultiSujetInTenSecond() throws InterruptedException {
		String subject = "subject";
		long tolerance = 1000;
		int MAX_USER = 2000000;
		
		final LockService ls = new LockServiceImpl();
		
		long tdebut = System.currentTimeMillis();
		
		Counter counter = new Counter();
		
		
		
		for(int i=0;i<MAX_USER; i++){
			final String subject2 = subject + i;
			Thread thread = new Thread(new LockTread(subject2, ls, counter));
			thread.start();
		}
		
		while(counter.count < MAX_USER){
			Thread.sleep(100);
		};
		
		System.out.println(counter.count);
		long tfin = System.currentTimeMillis();
		
		Assert.assertTrue((tfin-tdebut) < tolerance); 
	}
	
	class LockTread implements Runnable {
		private String subject;
		private LockService service;
		private Counter counter;
		
		public LockTread(String subject, LockService service, Counter counter) {
			super();
			this.subject = subject;
			this.service = service;
			this.counter = counter;
		}

		@Override
		public void run() {
			service.putLock(subject, subject);
			synchronized (counter) {
				counter.count++;
			}
		}

	}
	
	class Counter {
		public int count;
		
	}

}
