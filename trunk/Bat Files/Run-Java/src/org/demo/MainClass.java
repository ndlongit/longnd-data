package org.demo;

import java.io.IOException;

public class MainClass {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Value of key1: " + AppConfig.getValue("key1"));
	}
}
