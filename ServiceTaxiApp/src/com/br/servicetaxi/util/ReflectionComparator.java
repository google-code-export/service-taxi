package com.br.servicetaxi.util;

import java.lang.reflect.Method;
import java.util.Comparator;

public class ReflectionComparator implements Comparator {

	private String property;

	private boolean ascending;

	
	public ReflectionComparator(String property, boolean ascending) {
		setProperty(property);
		setAscending(ascending);
	}
	
	public void setProperty(String property) {
		this.property = property;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public String getProperty() {
		return property;
	}

	public boolean isAscending() {
		return ascending;
	}

	public int compare(Object o1, Object o2) {
		if (o1 != null && o2 != null) {
			try {
				Method method = BeanHelper.getReadMethod(o1.getClass(), 
						property);
				Object value1 = method.invoke(o1, (Object[]) null);
				Object value2 = method.invoke(o2, (Object[]) null);
				
				return genericCompare(value1, value2, ascending);
			} catch (Exception e) {
			}
		}
		return 0;
	}

	public static int genericCompare(Object oOne, Object oTwo, 
			boolean ascending) {
		if (oOne instanceof String && ((String) oOne).length() == 0) {
			oOne = null;
		}
		if (oTwo instanceof String && ((String) oTwo).length() == 0) {
			oTwo = null;
		}
		if (oOne == null && oTwo == null) {
			return 0;
		} else if (oOne == null) {
			return 1;
		} else if (oTwo == null) {
			return -1;
		} else if (oOne instanceof Comparable && oTwo instanceof 
				Comparable && oOne.getClass().equals(oTwo.getClass())) {
			if (ascending) {
				return ((Comparable) oOne).compareTo(oTwo);
			} else {
				return ((Comparable) oTwo).compareTo(oOne);
			}
		} else {
			if (ascending) {
				return oOne.toString().compareTo(oTwo.toString());
			} else {
				return oTwo.toString().compareTo(oOne.toString());
			}
		}
	}
}
