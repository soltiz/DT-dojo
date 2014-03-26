package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/Locks")
public interface LockService {

	// TODO : Exposition TTL dans le service REST
	@PUT
	@Path("/{subject}")
	public Lock putLock(@PathParam("subject") String subject,
			@QueryParam("owner") String owner);

	@GET
	@Path("/{subject}")
	public Lock getLock(@PathParam("subject") String subject);

}
