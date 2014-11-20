package com.thales.services.dt.codingdojo.computerunner;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

public class JarClassLoaderTest {

	private static final File RT_JAR_PATH = new File(System.getProperty("java.home") + "/lib/rt.jar");

	@Test(expected = RuntimeException.class)
	public void testNullJarName() throws IOException {
		JarClassLoader cl = null;
		try {
			cl = new JarClassLoader(null);
		} finally {
			if (cl != null) {
				cl.close();
			}
		}
	}

	@Test(expected = IOException.class)
	public void testJarNotFound() throws IOException {
		JarClassLoader cl = null;
		try {
			cl = new JarClassLoader(new File("notFound.jar"));
		} finally {
			if (cl != null) {
				cl.close();
			}
		}
	}

	@Test(expected = RuntimeException.class)
	public void testNullClassName() throws IOException, ClassNotFoundException {
		JarClassLoader cl = null;
		try {
			cl = new JarClassLoader(RT_JAR_PATH);
			cl.loadClass(null);
		} finally {
			if (cl != null) {
				cl.close();
			}
		}
	}

	@Test(expected = ClassNotFoundException.class)
	public void testClassNotFound() throws IOException, ClassNotFoundException {
		JarClassLoader cl = null;
		try {
			cl = new JarClassLoader(RT_JAR_PATH);
			cl.loadClass("foo.Bar");
		} finally {
			if (cl != null) {
				cl.close();
			}
		}
	}

	@Test
	public void testLoadClass() throws IOException, ClassNotFoundException {
		JarClassLoader cl = null;
		try {
			cl = new JarClassLoader(RT_JAR_PATH);
			Class<?> c = cl.loadClass("java.nio.ByteBuffer");
			Assert.assertEquals(c, ByteBuffer.class);
		} finally {
			if (cl != null) {
				cl.close();
			}
		}
	}
}
