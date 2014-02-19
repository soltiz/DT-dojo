package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/Locks")
public interface LockService {

	@GET
	@Path("/{oneparam}")
	public DemoObject getOneObject(@QueryParam("paramtwo") String option,
			@PathParam("oneparam") String objectName);
	
	@PUT
	@Path("/{subject}")
	public Lock getLock(@PathParam("subject") String subject,
			@QueryParam("owner") String owner);
}
