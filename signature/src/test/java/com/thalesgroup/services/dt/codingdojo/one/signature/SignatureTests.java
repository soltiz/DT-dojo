package com.thalesgroup.services.dt.codingdojo.one.signature;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SignatureTests    {
	private static Logger log=LoggerFactory.getLogger(SignatureTests.class);
	

	    @Test
	    public void signatureDuration() {
	    	long start_time = System.nanoTime();
	    	String dataToSign="AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
	    	Long computedSignature=SignatureHelper.signatureOf(dataToSign);
	    	long end_time = System.nanoTime();
	    	double difference = (end_time - start_time)/1e6;
	    	log.info("Signature '{}' has taken {} seconds.",computedSignature,difference/1000);
	    	assertTrue((difference/1000)>=1);
	    	assertTrue(SignatureHelper.isSignatureValid(dataToSign, computedSignature));
	    }
	    @Test
	    public void parralelDuration() throws InterruptedException {
	    	 class signatureThread extends Thread {
	    		
	    		  public void run() {
	    				long start_time = System.nanoTime();
				    	String dataToSign="AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
				    	Long computedSignature=SignatureHelper.signatureOf(dataToSign);
				    	long end_time = System.nanoTime();
				    	double difference = (end_time - start_time)/1e6;
				    	log.info("Signature '{}' has taken {} seconds.",getName(),difference/1000);
	    		  }
	    		}

	    	
	    	
	    	List<Thread> threads=new ArrayList<Thread> ();
	    	
	    	
	    	for (int i=0;i<1;i++) {
	    		Thread t=new signatureThread();
	    		t.setName("ST#"+String.valueOf(i+1));
	    		threads.add(t);
	  	}
	    	for (Thread t:threads) {
	    		log.info("Starting thread '{}'...",t.getName());
	    		t.start();
	    	}
	    	for (Thread t:threads) {
	    		t.join();
	    	}
	    
	    }

	
	
}
