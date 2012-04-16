package com.br.servicetaxi.util;

import java.util.Iterator;
import java.util.Map;

public class MapHelper {

	public static void sumValueMap(Map map, Object key, Object value) {
		Double val = (map.get(key) == null) ? 0.0D : 
			Double.valueOf(map.get(key).toString());
		if (value == null) {
			value = new Double(0);
		}
		val += Double.valueOf(value.toString());
		map.put(key, val);
	}

	public static String sumValuesMap(Map map){
		Double value = Double.valueOf(0);
		for (Iterator it = map.keySet().iterator(); it.hasNext();){
			String key = (String) it.next();
			
			Double val = (Double) map.get(key);
			
			value += val; 
		}
		return (value.equals(Double.valueOf(0))? "" : value.toString());
	}
	
	public static String valueOf(Map m, String key){
		return (m.get(key) == null)? null : m.get(key).toString().trim();
	}
}
