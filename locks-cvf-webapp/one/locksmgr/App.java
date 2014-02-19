package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.LockServer;
import com.thalesgroup.services.dt.codingdojo.one.LockServiceImpl;


/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger log = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	
    	 
    	JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
    	sf.setResourceClasses(LockServiceImpl.class);
    	sf.setAddress("http://localhost:9000/");
    	sf.create();
//    	
//    		 JAXRSServerFactoryBean jaxrsServerFactory = RuntimeDelegate.getInstance().createEndpoint(new LockMgrApp(), JAXRSServerFactoryBean.class);
//    		 jaxrsServerFactory.setAddress("http://localhost:9000");
//    		 org.apache.cxf.endpoint.Server webServer = jaxrsServerFactory.create();
//    		 webServer.start();
//    		  
    		 log.info("Web server started...");
    		
    	
    	
    	
    	
    	
    	LockServer server=new LockServer();
        try {
			server.start();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			Thread.sleep(1200000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        server.stop();
//        webServer.stop();
    }
}
