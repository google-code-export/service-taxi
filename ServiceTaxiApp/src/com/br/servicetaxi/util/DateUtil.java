package com.br.servicetaxi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>Copyright: Copyright (c) 2006</p>
 * 
 * <p>Company: Companhia Ferroviária do Nordeste. Todos os direitos reservados.</p>
 * 
 * @author Techway Informática Ltda. - Java Team Developers
 * @version 1.0
 */

public class DateUtil {
	
	// private static final Locale BRAZIL_LOCALE = new Locale("pt_BR");

	private static final double DAY_MILLIS = 1000 * 60 * 60 * 24.0015;

	private static final double WEEK_MILLIS = DAY_MILLIS * 7;

	private static final double MONTH_MILLIS = DAY_MILLIS * 30.43675;

	private static final double YEAR_MILLIS = WEEK_MILLIS * 52.2;

	
	public static Date getDate() {
		Calendar cal = new GregorianCalendar();
		
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static int getDateDiff(int calUnit, Date d1, Date d2) {
		// swap if d1 later than d2
		boolean neg = false;
		if (d1.after(d2)) {
			Date temp = d1;
			d1 = d2;
			d2 = temp;
			neg = true;
		}
		// estimate the diff. d1 is now guaranteed <= d2
		int estimate = (int) getEstDiff(calUnit, d1, d2);

		// convert the Dates to GregorianCalendars
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(d1);
		GregorianCalendar c2 = new GregorianCalendar();
		c2.setTime(d2);

		// add 2 units less than the estimate to 1st date,
		// then serially add units till we exceed 2nd date
		c1.add(calUnit, (int) estimate - 2);
		for (int i = estimate - 1;; i++) {
			c1.add(calUnit, 1);
			if (c1.after(c2)) {
				return neg ? 1 - i : i - 1;
			}
		}
	}

	private static int getEstDiff(int calUnit, Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		switch (calUnit) {
			case Calendar.DAY_OF_WEEK_IN_MONTH:
			case Calendar.DAY_OF_MONTH:
			// case Calendar.DATE : // codes to same int as DAY_OF_MONTH
				return (int) (diff / DAY_MILLIS + .5);
			case Calendar.WEEK_OF_YEAR:
				return (int) (diff / WEEK_MILLIS + .5);
			case Calendar.MONTH:
				return (int) (diff / MONTH_MILLIS + .5);
			case Calendar.YEAR:
				return (int) (diff / YEAR_MILLIS + .5);
			default:
				return 0;
		} 
	}

	public static Date parseDate(String date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			//System.out.println(date);
			return df.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date parseDateToFormat(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			//System.out.println(df.parse(date));
			return df.parse(date);
		} catch (Exception e) {
  			return null;
		}
	}
	
	public static String format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static Date parseDateShort(String date){
	       //	System.out.println(date);
			
			return parseDate(date, "yyyyMMdd");
	}
	
	public static Date parseDate(String date){
       //	System.out.println(date);
		
		return parseDate(date, "yyyyMMddHHmm");
	}
	
	/**
	 * Método que retorna o primeiro dia de um mês a partir de um número no
	 * formato: YYYMM. Ex.: 200701.
	 * 
	 * @param anoMes
	 *            Número no formato: YYYMM. Ex.: 200701.
	 * @return Primeiro dia do mês
	 */
	public static Date getFirstDayOfMonth(Long anoMes) {
		return getFirstDayOfMonth(anoMes.toString());
	}

	/**
	 * Método que retorna o primeiro dia de um mês a partir de uma string no
	 * formato: YYYMM. Ex.: 200701.
	 * 
	 * @param anoMes
	 *            String no formato: YYYMM. Ex.: "200701".
	 * @return Primeiro dia do mês
	 */
	public static Date getFirstDayOfMonth(String anoMes) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(anoMes.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.valueOf(anoMes.substring(4, 6)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Método que retorna o último dia de um mês a partir de uma string no
	 * formato: YYYMM. Ex.: 200701.
	 * 
	 * @param anoMes
	 *            String no formato: YYYMM. Ex.: "200701".
	 * @return Último dia do mês
	 */
	public static Date getLastDayOfMonth(Long anoMes) {
		return getLastDayOfMonth(anoMes.toString());
	}

	/**
	 * Método que retorna o último dia de um mês a partir de uma string no
	 * formato: YYYMM. Ex.: 200701.
	 * 
	 * @param anoMes
	 *            String no formato: YYYMM. Ex.: "200701".
	 * @return Último dia do mês
	 */
	public static Date getLastDayOfMonth(String anoMes) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(anoMes.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.valueOf(anoMes.substring(4, 6)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}
	
	public static boolean isDate(String date)
	throws ParseException {   
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");   
        Calendar cal = new GregorianCalendar();   

        if (date.length() != 10) {
        	return false;
        }
        cal.setTime(df.parse(date));   

        String[] array = date.split("/");   
        String day = array[0];   
        String month = array[1];   
        String year = array[2];   
        if ((new Integer(day)).intValue() != 
        	(new Integer(cal.get(Calendar.DAY_OF_MONTH))).intValue()) {   
        	   
            return false;   
        } else if ((new Integer(month)).intValue() != 
        	(new Integer(cal.get(Calendar.MONTH) + 1)).intValue()) {   
               
        	return false;   
        } else if ((new Integer(year)).intValue() != 
        	(new Integer(cal.get(Calendar.YEAR))).intValue()) {   
               
        	return false;   
        }   
        return true;                           
    }
}