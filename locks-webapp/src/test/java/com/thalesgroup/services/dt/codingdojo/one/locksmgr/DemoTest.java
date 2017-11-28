package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertTrue;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.locksmgr.LockService;
import com.thalesgroup.services.dt.codingdojo.one.signature.SignatureHelper;

public class DemoTest {
	private static Logger log = LoggerFactory.getLogger(DemoTest.class);

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
			serviceProxy = JAXRSClientFactory.create("http://localhost:8080/test/rest/v1", LockService.class);
			WebClient.client(serviceProxy).accept("application/xml");

		}
	}

	@Test
	public void signatureLibraryAvailable() {
		String dataToSign = "AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
		Long computedSignature = null;
		for (int i = 0; i < 8; i++) {
			dataToSign = dataToSign + "x";
			computedSignature = SignatureHelper.signatureOf(dataToSign);
			log.info("Signature field= {} result = {}", dataToSign, computedSignature);

		}

		assertTrue(SignatureHelper.isSignatureValid(dataToSign, computedSignature));
	}

	@After
	public void shutdownServer() throws Exception {
		server.stop();
		log.info("Server stopped.");
	}

}
