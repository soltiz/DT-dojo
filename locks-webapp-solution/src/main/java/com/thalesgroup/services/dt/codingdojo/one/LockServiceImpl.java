package com.thalesgroup.services.dt.codingdojo.one;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.eclipse.jetty.http.HttpStatus;

public class LockServiceImpl implements LockService {

	@Override
	public Lock putLock(String subject, String owner) {
		return LockManager.getInstance().putLock(subject, owner, LockManager.DEFAULT_TTL);
	}

	@Override
	public Lock getLock(String subject) throws WebApplicationException{
		Lock lock = LockManager.getInstance().getLock(subject);
		
		if(lock == null) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}

		return lock;
	}
	
//	@Override
//	public DemoObject getOneObject(String option, String objectName) {
//
//		String newName=objectName;
//		if (option != null) {
//			newName=newName+"_withOption_"+option;
//		}
//		if (objectName.contentEquals("doesNotExist")) {
//			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
//		}
//		return new DemoObject(newName);
//	};
//	@Override
//	public List<DemoObject> getObjects() {
//		List<DemoObject>list=new ArrayList<DemoObject>();
//		list.add(new DemoObject("un"));
//		list.add(new DemoObject("deux"));
//		return list;
//	}
}
