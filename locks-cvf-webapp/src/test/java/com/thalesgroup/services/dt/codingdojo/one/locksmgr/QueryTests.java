package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.thalesgroup.services.dt.codingdojo.one.LockService;
import com.thalesgroup.services.dt.codingdojo.one.LockToken;
import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class QueryTests    {
	private static Logger log=LoggerFactory.getLogger(QueryTests.class);
	
//    @Qualifier("lockclient")
//    @Autowired
    protected LockService serviceProxy;
    private Server server;
	    @Before
	    public  void startServer() throws Exception {
	    	if (server==null) {
	    		

	    	server = new Server(8080);
			server.setStopAtShutdown(true);
	
			WebAppContext webAppContext = new WebAppContext();
			webAppContext.setContextPath("/test");	
			webAppContext.setResourceBase("src/main/webapp");        
			webAppContext.setClassLoader(getClass().getClassLoader());
			server.addHandler(webAppContext);
			server.start( );
			log.info("Server started.");
			serviceProxy = JAXRSClientFactory.create("http://localhost:8080/test/rest/v1", LockService.class);
			WebClient.client(serviceProxy).accept("application/xml");
			
	    	}
	    }

	    @Test
	    public void signatureLibraryAvailable() {
	    	String dataToSign="AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
	    	Long computedSignature=SignatureHelper.signatureOf(dataToSign);
	    	
	    	assertTrue(SignatureHelper.isSignatureValid(dataToSign, computedSignature));
	    }
	    
	@Test
	public void getLock() throws InterruptedException{
		
		
		LockToken token=serviceProxy.setOrRefreshLock("ownerMe", "topicOne");
		log.info("Got token with owner {} and topic {}.",token.getOwner(),token.getTopic());
		assertEquals("ownerMe",token.getOwner());
		assertEquals("topicOne",token.getTopic());
		Thread.sleep(1000);
	}
	
	@Test(expected=ClientErrorException.class)
	public void refusedLock() throws InterruptedException{
		
		
		LockToken token=serviceProxy.setOrRefreshLock("ownerMe", "lockedTopic");
		log.info("Got token with owner {} and topic {}.",token.getOwner(),token.getTopic());
		assertEquals("ownerMe",token.getOwner());
		assertEquals("topicOne",token.getTopic());
		Thread.sleep(1000);
	}
	
	
	
    @After
    public  void shutdownServer() throws Exception {
        server.stop();
        log.info("Server stopped.");
    }
	
	
}
