package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.*;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.ClientException;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.LockObject;
import com.thalesgroup.services.dt.codingdojo.one.LockService;
import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

public class LockServiceTest {
	private static Logger log = LoggerFactory.getLogger(LockServiceTest.class);

	protected LockService serviceProxy;
	private Server server;

	@Before
	public void startServer() throws Exception {
		if (server == null) {

			server = new Server(8080);
			server.setStopAtShutdown(true);

			WebAppContext webAppContext = new WebAppContext();
			webAppContext.setContextPath("/test");
			webAppContext.setResourceBase("src/main/webapp");
			webAppContext.setClassLoader(getClass().getClassLoader());
			server.addHandler(webAppContext);
			server.start();
			log.info("Server started.");
			serviceProxy = JAXRSClientFactory.create(
					"http://localhost:8080/test/rest/v1", LockService.class);
			WebClient.client(serviceProxy).accept("application/xml");

		}
	}

	// @Test
	public void signatureLibraryAvailable() {
		String dataToSign = "AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
		Long computedSignature = null;
		for (int i = 0; i < 8; i++) {
			dataToSign = dataToSign + "x";
			computedSignature = SignatureHelper.signatureOf(dataToSign);
			log.info("Signature field= {} result = {}", dataToSign,
					computedSignature);

		}

		assertTrue(SignatureHelper.isSignatureValid(dataToSign,
				computedSignature));
	}

	@Test
	public void putLock() throws InterruptedException {

		int id = 0;
		LockObject o = serviceProxy.putLockOn("me", "topic", null);
		id = o.getId();
		LockObject o1 = serviceProxy.putLockOn("me", "topic", null);
		assertEquals(id, o1.getId());

		LockObject o2 = serviceProxy.putLockOn("you", "otopics", null);
		assertTrue(id != o2.getId());
	}

	@Test
	public void conflictLock() throws InterruptedException {

		int id = 0;
		LockObject o = serviceProxy.putLockOn("me", "topic", null);
		id = o.getId();
		LockObject o1 = serviceProxy.putLockOn("me", "topic", null);
		assertEquals(id, o1.getId());

		try {
			LockObject o2 = serviceProxy.putLockOn("you", "topic", null);
			fail();
		} catch (ClientErrorException e) {
			assertTrue(e.getResponse().getStatus() == 409);
		}

	}

	@Test
	public void getLock() throws InterruptedException {
		try {
			serviceProxy.getLockOn("topique");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof NotFoundException);
		}

		LockObject o1 = serviceProxy.putLockOn("me", "topique", null);

		LockObject o2 = serviceProxy.getLockOn("topique");
		assertEquals(o1.getId(), o2.getId());
	}

	// Le verrou renvoyé par le service à la création contient une date de
	// création et une date d'expiration.
	@Test
	public void putLockCheckDate() throws InterruptedException {
		long duration = 10;
		LockObject o1 = serviceProxy.putLockOn("me", "topique", duration);
		assertNotNull(o1.getCreationDate());
		assertTrue(o1.getExpirationDate().getTime() == (o1.getCreationDate().getTime() + (duration*1000)));		
	}
	

	@After
	public void shutdownServer() throws Exception {
		server.stop();
		log.info("Server stopped.");
	}

}
