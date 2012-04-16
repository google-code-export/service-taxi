package com.br.servicetaxi.util;

import java.util.Comparator;
import java.util.Map;

public class MapComparator implements Comparator {

	private String key;
	
	
	public MapComparator(String key) {
		setKey(key); 
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public int compare(Object obj1, Object obj2) {
		String s1 = (String)((Map) obj1).get(key);
        String s2 = (String)((Map) obj2).get(key);
        
		return s1.compareTo(s2);
	}
}
