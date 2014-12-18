package com.thales.services.dt.codingdojo.utils;

import java.io.IOException;

public class MainUtils {

	public static void main(String[] args) {
		
		ClassExplorer explorer;
		try {
			explorer = new ClassExplorer(args[0]);
			System.out.println(explorer.printMethods(args[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
