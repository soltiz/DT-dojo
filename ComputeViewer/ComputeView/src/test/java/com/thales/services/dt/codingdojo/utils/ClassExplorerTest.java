package com.thales.services.dt.codingdojo.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.junit.Test;

public class ClassExplorerTest {

	private String jarPath = "sqrt-lib-0.0.3.jar";
	private String jarPathWithAnnote = "sqrt-lib-0.0.4.jar";
	private String className = "com.thales.services.dt.codingdojo.computerunner.libs.sqrt.Sqrt";

	@Test
	public void listFunctionIsNotNullTest() throws IOException,
			ClassNotFoundException, URISyntaxException {
		URI jarUri = getClass().getClassLoader().getResource(jarPath).toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();
		ClassExplorer tested = new ClassExplorer(path);
		assertNotNull(tested.getMethodNames(className));
	}

	@Test
	public void listFunctionTest() throws IOException, ClassNotFoundException,
			URISyntaxException {
		URI jarUri = getClass().getClassLoader().getResource(jarPath).toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();
		ClassExplorer tested = new ClassExplorer(path);
		String result = tested.printMethods(className);
		String expected = "double approxSqrt(double, double)\ndouble computeSqrt(double)\n";

		assertEquals(expected, result);
	}

	@Test
	public void listFunctionWithJarAnnoteTest() throws IOException,
			ClassNotFoundException, URISyntaxException {
		URI jarUri = getClass().getClassLoader().getResource(jarPathWithAnnote)
				.toURI();
		String path = Paths.get(jarUri).toFile().getAbsolutePath();
		ClassExplorer tested = new ClassExplorer(path);
		String result = tested.printMethods(className);
		String expected = "thales\ndouble approxSqrt(double, double)\ncoding dojo\ndouble computeSqrt(double)\n";

		assertEquals(expected, result);
	}

}
