package com.br.servicetaxi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class FileUtil {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("lojaGeq");

	public static boolean copy (File input, File output) {
		FileReader reader = null;
		FileWriter writer = null;
		boolean success = true;
		try {
			reader = new FileReader(input);
			writer = new FileWriter(output);
			int c;
			while ((c = reader.read()) != -1){
				writer.write(c);
			}
		} catch (IOException e) {
			success = false;
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {}
		}
		return success;
	}
	
	public static void createFile(StringBuilder buffer, String name){
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(name));
	        
	        out.write(buffer.toString());
	        out.close();
	    } catch (IOException e) {
	    	// TODO
	    }
	}

	public static ResourceBundle getResource() {
		return resource;
	}
}
