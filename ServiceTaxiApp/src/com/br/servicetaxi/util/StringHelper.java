package com.br.servicetaxi.util;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
	
	public static String[] REPLACES = { "a", "e", "i", "o", "u", "c", "A", "E", "I", "O", "U", "C" };  
	   
    public static Pattern[] PATTERNS = null;  
   
    public static void compilePatterns() {  
       PATTERNS = new Pattern[REPLACES.length];  
       PATTERNS[0]  = Pattern.compile("[âãáàä]");  
       PATTERNS[1]  = Pattern.compile("[éèêë]");  
       PATTERNS[2]  = Pattern.compile("[íìîï]");  
       PATTERNS[3]  = Pattern.compile("[óòôõö]");  
       PATTERNS[4]  = Pattern.compile("[úùûü]");  
       PATTERNS[5]  = Pattern.compile("[ç]");  
       PATTERNS[6]  = Pattern.compile("[ÂÃÁÀÄ]");  
       PATTERNS[7]  = Pattern.compile("[ÉÈÊË]");  
       PATTERNS[8]  = Pattern.compile("[ÍÌÎÏ]");  
       PATTERNS[9]  = Pattern.compile("[ÓÒÔÕÖ]");  
       PATTERNS[10] = Pattern.compile("[ÚÙÛÜ]");  
       PATTERNS[11] = Pattern.compile("[Ç]");  
    }  
   
    private StringHelper() {  
    }
    
    /** 
     * Substitui os caracteres acentuados por nao acentuados. 
     *  
     * @param text 
     * @return 
     */  
    public static String replaceSpecial(String text) {  
       if (PATTERNS == null) {  
          compilePatterns();  
       }  
   
       String result = text;  
       for (int i = 0; i < PATTERNS.length; i++) {  
          Matcher matcher = PATTERNS[i].matcher(result);  
          result = matcher.replaceAll(REPLACES[i]);  
       }  
       return result;  
    }
	
	
	/**
	 * Formata um número com espaços à esquerda.
	 * 
	 * @param value
	 * @param integerDigits
	 * @param fractionDigits
	 * 
	 * @return String
	 */
	public static String formatSpaces(double value, int integerDigits, int fractionDigits) {
		int tamanho = 0;
		NumberFormat numberFormater = NumberFormat.getNumberInstance();
		numberFormater.setMaximumIntegerDigits(integerDigits);
		numberFormater.setMinimumIntegerDigits(1);
		numberFormater.setMinimumFractionDigits(fractionDigits);
		numberFormater.setMaximumFractionDigits(fractionDigits);
		String res = numberFormater.format(value);
		
		if (fractionDigits > 0) {
			tamanho = (integerDigits + fractionDigits + 1) - res.length();
		} else {
			tamanho = (integerDigits + fractionDigits) - res.length();
		}
		res = repeat(" ", tamanho).concat(res);
		
		return (res);
	}
	
	/**
	 * Retorna uma String composta de vária Strings repetidas.
	 * 
	 * @param chr
	 * @param number
	 * 
	 * @return String
	 */	
	public static String repeat(String chr, int number){
		StringBuffer sb = new StringBuffer(number);
		for (int i = 1; i <= number; i++){
			sb.append(chr);
		}
		return sb.toString();
	}
	
	/**
	 * Retorna true se o objeto String for nulo ou o conteúdo estiver vazio.
	 * 
	 * @param s
	 * 
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null) || s.equals("");
	}
	
	public static String quotedStr(String s) {
		String newString = s.replaceAll("\'", "\'\'");
		newString = "'" + newString + "'";
		
		return newString;
	}
	
	public static String formatToIn(String[] item) {
		String in = "";
		String virgula = "";
		for (int i = 0; i < item.length; i++) {
			if (!isEmpty(item[i])) {
				in += virgula + item[i];
				virgula = ", ";
			}	
		}
		return in;
	}
	
	public static String formatToIn(List item) {
		String in = "";
		String virgula = "";
		for (Iterator iterator = item.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			in += virgula + string;
			virgula = ", ";
		}
		return in;
	}
	
	public static String verificaIn(String parametros){
	
		String result ="";  
		
		if(parametros != null){
		for(int i=0;i< parametros.length();i++){
			
		
		String string = String.valueOf(parametros.charAt(i));
		Pattern pattern = Pattern.compile("[0-9]");  
		Matcher match = pattern.matcher(string);
		if(match.find()){
			
			result = result+string;
		}else if(string.equals(",")){
			
			if(!result.endsWith(",")){
				result = result+string;
			}
		}
		}
		 
		
		}
		if(result.endsWith(",")){
		result = result.substring(0, result.length()-1);
		
	}	
		return result;
}

	public static String formatToAspasIn(String[] item) {
		String in = "";
		String virgula = "";
		for (int i = 0; i < item.length; i++) {
			if (!isEmpty(item[i])) {
				in += virgula + "'" + item[i] + "'";
				virgula = ", ";
			}	
		}
		return in;
	}
	
	public static Long toLong(Object obj) {
		if (!isEmpty((String) obj)) {
			return Long.valueOf((String) obj);	
		} else {
			return null;
		}	
	}
	
	/**
	 * Retorna uma String composta de vária palavras separadas por
	 * caracter.
	 * 
	 * @param words
	 * @param caracter
	 * 
	 * @return String
	 */
	public static String concat(String[] words, String caracter){
		String str = "";
		for (int i = 0; i < words.length; i++) {
			if (str.equals("")) {
				str = (String) words[i];
			} else {
				str = str + caracter + words[i];
			}
		}
		return str;
	}
	
	/**
	 * Insere uma String <code>text</code> numa determinada posição at 
	 * da String <code>source</code>.
	 * 
	 * @param source
	 * @param text
	 * @param at
	 * 
	 * @return String
	 */
	public static String insertAt(String source, String text, int at) {   
        StringBuilder sb = new StringBuilder(""); 
        
        sb.append(source.substring(0, at));   
        sb.append(text);   
        sb.append(source.substring(at));
        
        return sb.toString();   
    } 
}
