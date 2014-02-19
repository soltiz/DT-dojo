package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;

public class LockMgrApp extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(LockServiceImpl.class);

		return classes;

	}

}
