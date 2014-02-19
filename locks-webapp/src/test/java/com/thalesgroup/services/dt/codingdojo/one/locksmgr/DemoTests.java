package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.NotFoundException;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.DemoObject;
import com.thalesgroup.services.dt.codingdojo.one.LockService;
import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

public class DemoTests    {
	private static Logger log=LoggerFactory.getLogger(DemoTests.class);
	

    protected LockService serviceProxy;
    private Server server;
	    //@Before
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

	    //@Test
	    public void signatureLibraryAvailable() {
	    	String dataToSign="AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
	    	Long computedSignature=SignatureHelper.signatureOf(dataToSign);
	    	
	    	assertTrue(SignatureHelper.isSignatureValid(dataToSign, computedSignature));
	    }
	    
	//@Test
	public void getDemoObject() throws InterruptedException{
		
		
		DemoObject o=serviceProxy.getOneObject("theOption", "theName");
		assertEquals("theName_theOption",o.getName());
	}
	
	//@Test(expected=NotFoundException.class)
	public void refusedGetObject() throws InterruptedException{
		serviceProxy.getOneObject("theOption", "doesNotExist");
	}
	
	
	
    //@After
    public  void shutdownServer() throws Exception {
        server.stop();
        log.info("Server stopped.");
    }
	
	
}
