package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import com.thalesgroup.services.dt.codingdojo.one.CalendarProvider;
import com.thalesgroup.services.dt.codingdojo.one.Lock;
import com.thalesgroup.services.dt.codingdojo.one.LockManager;
import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

public class LockTest {

	@After
	public void reset(){
		Lock.setCalendarProvider(new CalendarProvider());
	}
	
	@Test
	public void singleLockExpiration() {
		Calendar mockedCalendar = Calendar.getInstance();
		
		CalendarProvider provider = Mockito.mock(CalendarProvider.class);
		Mockito.when(provider.getCalendarInstance()).thenReturn(mockedCalendar);
		
		Lock.setCalendarProvider(provider);
		Lock lock=new Lock("bonjour","owner", 1 );
		
		mockedCalendar.add(Calendar.MINUTE, 1);
		
		Assert.assertTrue(lock.hasExpired());
	}
	
	@Test
	public void testSignatureValue()
	{
		String subject = "subject";
		String owner = "owner1";
		Lock lock1 = new Lock(subject,owner, LockManager.DEFAULT_TTL);
		
		assertEquals(subject,lock1.getSubject());
		assertEquals(owner,lock1.getOwner());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss.S");
		
		String lockExpiryDateRepresentation =  format.format(lock1.getExpiryDate());
		String dataToSign = subject+ "_!_" + owner + "_!_" + lockExpiryDateRepresentation;

		Long expectedSignature =  SignatureHelper.signatureOf(dataToSign);
		
		assertEquals(expectedSignature, lock1.getSignature());
		
	}

}
