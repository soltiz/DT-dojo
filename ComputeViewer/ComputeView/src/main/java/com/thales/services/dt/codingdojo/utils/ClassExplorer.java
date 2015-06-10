package com.thales.services.dt.codingdojo.utils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thales.services.dt.codingdojo.computerunner.JarClassLoader;

public class ClassExplorer {

	private JarClassLoader jarClassLoader;

	public ClassExplorer(String jarPath) throws IOException {
		jarClassLoader = new JarClassLoader(new File(jarPath));
	}

	private List<Method> getMethods(String className)
			throws ClassNotFoundException {
		Class<?> clazz = jarClassLoader.findClass(className);

		Method[] methods = clazz.getDeclaredMethods();

		return Arrays.asList(methods);
	}

	public List<String> getMethodNames(String className)
			throws ClassNotFoundException {
		List<Method> methods = getMethods(className);

		List<String> methodNames = new ArrayList<String>();
		for (Method method : methods) {
			methodNames.add(method.getName());
		}

		return methodNames;
	}

	public String printMethods(String className) throws ClassNotFoundException {
		List<Method> methods = getMethods(className);

		Collections.sort(methods, new Comparator<Method>() {

			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		StringBuilder sb = new StringBuilder();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			if (annotations.length > 0) {
				for (Annotation annotation : annotations) {
					if ("Owner".equals(annotation.annotationType()
							.getSimpleName())) {
						try {
							Method declaredMethod = annotation.annotationType()
									.getDeclaredMethod("value");
							String annotationValue = (String) declaredMethod
									.invoke(annotation);
							sb.append(annotationValue);
							sb.append("\n");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			sb.append(method.getReturnType().getSimpleName());
			sb.append(" ");
			sb.append(method.getName());
			sb.append("(");
			Class<?>[] parameterTypes = method.getParameterTypes();
			boolean firstIteration = true;

			for (Class<?> clazz : parameterTypes) {
				if (!firstIteration) {
					sb.append(", ");
				}
				firstIteration = false;
				sb.append(clazz.getSimpleName());
			}

			sb.append(")\n");
		}

		return sb.toString();
	}

}
