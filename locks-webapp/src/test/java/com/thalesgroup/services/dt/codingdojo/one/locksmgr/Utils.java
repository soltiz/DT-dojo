package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import org.junit.Assert;

import com.thalesgroup.services.dt.codingdojo.one.locksmgr.Lock;

public class Utils {

	public static void compareLock(Lock lockExpected, Lock lockExisting){
		Assert.assertEquals(lockExpected.getOwner(), lockExisting.getOwner());
		Assert.assertEquals(lockExpected.getPlace(), lockExisting.getPlace());
		Assert.assertEquals(lockExpected.getSpectacleName(), lockExisting.getSpectacleName());
	}
	
}
