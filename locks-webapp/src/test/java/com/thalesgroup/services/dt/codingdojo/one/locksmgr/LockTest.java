package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import com.thalesgroup.services.dt.codingdojo.one.CalendarProvider;
import com.thalesgroup.services.dt.codingdojo.one.Lock;

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

}
