package com.br.servicetaxi.util;

public class StringFormat {
	
	private String value;
	
	private String pattern;
	
	public StringFormat(){
		super();
	}
	
	public StringFormat(String pattern){
		this.pattern = pattern;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void applyPattern(String pattern){   
		setPattern(pattern);   
	}
	
	public String format(String value){
		String str = pattern;
		for(int i = 0; i < value.length(); i++){   
	        str = str.replaceFirst("#", value.substring(i, i + 1));   
	    }   
		return str.replaceAll("#", "");   
	}
	
	public static String removeZerosSerie(String linha) {  
	       String x = null;  
	  
	       if ((linha.equals("01")) || (linha.equals("001"))) {
	    	   x = "1";
	       } else if ((linha.equals("02")) || (linha.equals("002"))) {
		       x = "2";
	       } else if ((linha.equals("03")) || (linha.equals("003"))) {
			   x = "3";
		   } else {	   
	    	   x = linha.trim();
	       }
	             
	         
	       return x;  
	}  
	
	
}
