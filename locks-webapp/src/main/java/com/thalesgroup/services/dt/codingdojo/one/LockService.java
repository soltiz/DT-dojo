package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/Locks")
public interface LockService {

	@PUT
	@Path("/{topicParam}")
	public LockObject putLockOn(@QueryParam("owner") String owner,
			@PathParam("topicParam") String topic,
			@QueryParam("durationSeconds") Long durationSeconds);

	@GET
	@Path("/{topicParam}")
	public LockObject getLockOn(@PathParam("topicParam") String topic);

}
