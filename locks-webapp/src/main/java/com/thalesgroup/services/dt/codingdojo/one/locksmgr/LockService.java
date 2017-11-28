package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/shows")
public interface LockService {

	
	//(.../shows/WestSideStory-Paris-20170906-2030/seatlocks/K9)
	@GET
	@Path("/{spectacle}/seatlocks/{place}")
	public Lock consulterVerrou(@PathParam("spectacle") String spectacleName,
			@PathParam("place") String placeName);
	
	//(.../shows/WestSideStory-Paris-20170906-2030/seatlocks/K9?owner=me)
	@PUT
	@Path("/{spectacle}/seatlocks/{place}")
	public Lock placerVerrou(@QueryParam("owner") String userName,
			@PathParam("spectacle") String spectacleName,
			@PathParam("place") String placeName);
	
	@GET
	@Path("/{spectacle}/seatlocks")
	Collection<Lock> getVerrous(@PathParam("spectacle")  String nomSpectacle) ;
}
