package com.br.servicetaxi.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import br.com.jmacedo.util.StringHelper;

public class NumberUtil {
	
	private static Locale brazil = new Locale("pt", "BR");

	private static DecimalFormatSymbols dSymbols = new DecimalFormatSymbols(
			brazil);
	
	public static final Long ONE = Long.valueOf(1);
	
	public static final Long TWO = Long.valueOf(2);
	
	public static  Long THREE = Long.valueOf(3);
	
	public static final BigDecimal CEM = new BigDecimal(100);
	
	
	public static String formatNumber(BigDecimal number, Long decimal){
		String value = null;
		
		DecimalFormat decFmt = getDecimalFormat(decimal);
		
		if (number != null){
			value = decFmt.format(number.doubleValue());
		}
		return value;
	}
	
	public static Number parseNumber(String number, Long decimal) throws ParseException{
		Number value = null;
		
		DecimalFormat decFmt = getDecimalFormat(decimal);
		
		if (!StringHelper.isEmpty(number)){
			value = decFmt.parse(number);
		}
		return value;
	}
	
	private static DecimalFormat getDecimalFormat(Long decimal){
		String casas = null;
		DecimalFormat decFmt = null;
		
		switch(decimal.intValue()){
			case 1:{
				casas = "0.0";
				break;
			}
			case 2:{
				casas = "0.00";
				break;
			}
			case 3:{
				casas = "0.000";
				break;
			}
			default:{
				casas = "0";
			}
		}
		decFmt = new DecimalFormat(casas, dSymbols);
		
		return decFmt;
	}
	
	public static BigDecimal getBigDecimal(String valor){
		BigDecimal result = null;
		
		if (!StringHelper.isEmpty(valor)){
			result = BigDecimal.valueOf(Long.valueOf(valor).longValue());
		}
		return result;
	}
	

	public static String toCurrency(double d){
		String result = NumberFormat.getCurrencyInstance(brazil).format(d);
			
		return result.substring(2, result.length());
	}
}
