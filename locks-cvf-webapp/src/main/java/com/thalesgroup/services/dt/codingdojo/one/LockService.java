package com.thalesgroup.services.dt.codingdojo.one;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

@Path("/Locks")	
public interface LockService {

@PUT
@Path("/{topic}")
	public LockToken setOrRefreshLock(@QueryParam ("ownerName") String ownerName, @PathParam ("topic") String topic);
	
@GET
	public List<LockToken> getOwnerLocks(@QueryParam ("ownerName") String ownerName);
}
		