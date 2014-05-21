package com.thalesgroup.services.dt.codingdojo.one.locksmgr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import org.mortbay.jetty.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalesgroup.services.dt.codingdojo.one.DemoObject;
import com.thalesgroup.services.dt.codingdojo.one.LockService;

public class DemoTests {
	private static Logger log = LoggerFactory.getLogger(DemoTests.class);

	protected LockService serviceProxy;
	private Server server;

//	@Before
//	public void startServer() throws Exception {
//		if (server == null) {
//
//			server = new Server(8080);
//			server.setStopAtShutdown(true);
//
//			WebAppContext webAppContext = new WebAppContext();
//			webAppContext.setContextPath("/test");
//			webAppContext.setResourceBase("src/main/webapp");
//			webAppContext.setClassLoader(getClass().getClassLoader());
//			server.addHandler(webAppContext);
//			server.start();
//			log.info("Server started.");
//			serviceProxy = JAXRSClientFactory.create(
//					"http://localhost:8080/test/rest/v1", LockService.class);
//			WebClient.client(serviceProxy).accept("application/xml");
//
//		}
//	}
//
//	@After
//	public void shutdownServer() throws Exception {
//		server.stop();
//		log.info("Server stopped.");
//	}
//
//	@Test
//	public void signatureLibraryAvailable() {
//		String dataToSign = "AnImportantTopic_!_OwnedByMe_!_20140301181213.340";
//		Long computedSignature = null;
//		for (int i = 0; i < 8; i++) {
//			dataToSign = dataToSign + "x";
//			computedSignature = SignatureHelper.signatureOf(dataToSign);
//			log.info("Signature field= {} result = {}", dataToSign,
//					computedSignature);
//
//		}
//
//		assertTrue(SignatureHelper.isSignatureValid(dataToSign,
//				computedSignature));
//	}

	 //@Test
//	 public void getDemoObject() throws InterruptedException{
//	
//	
//	 DemoObject o=serviceProxy.getOneObject("theOption", "theName");
//	 assertEquals("theName_theOption",o.getName());
//	 }
	
//	 //@Test(expected=NotFoundException.class)
//	 public void refusedGetObject() throws InterruptedException{
//	 serviceProxy.getOneObject("theOption", "doesNotExist");
//	 }

	@Test
	public void testMockito() {
		DemoObject mockedDemoObject = Mockito.mock(DemoObject.class);
		Mockito.when(mockedDemoObject.getName()).thenReturn("toto");
		assertEquals("toto", mockedDemoObject.getName());
	}
}
