package com.thalesgroup.services.dt.codingdojo.one;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/shows")
public interface LockService {

	@GET
	@Path("/{oneparam}")
	public DemoObject getOneObject(@QueryParam("paramtwo") String option,
			@PathParam("oneparam") String objectName);
	
	//(.../shows/WestSideStory-Paris-20170906-2030/seatlocks/K9?owner=me)
	@PUT
	@Path("/{spectacle}/seatlocks/{place}")
	public Lock placerVerrou(@QueryParam("owner") String userName,
			@PathParam("spectacle") String spectacleName,
			@PathParam("place") String placeName);
	
}
