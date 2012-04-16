package com.br.servicetaxi.util;

import java.io.InvalidObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class BeanHelper {

	public static final String SET = "set";

	public static final String GET = "get";

	public static final Boolean ORDER_ASCENDING = Boolean.TRUE;

	public static final Boolean ORDER_DESCENDING = Boolean.FALSE;

	
	/**
	 * Ordena uma lista de acordo com uma propriedade do bean que a lista
	 * contenha.
	 * 
	 * @param list Lista a ser ordenada.
	 * @param property Propriedade que será usada na ordenação.
	 * @throws InvalidObjectException Disparada quando não for informados os parâmetros.
	 */
	public static void sortList(List list, String property) 
	throws InvalidObjectException {
		sortList(list, property, ORDER_ASCENDING);
	}

	/**
	 * Ordena uma lista de acordo com uma propriedade do bean que a lista
	 * contenha.
	 * 
	 * @param list Lista a ser ordenada.
	 * @param property Propriedade que será usada na ordenação.
	 * @param order Ordem (crescente ou decrescente) da ordenação. Use
	 *        BeanHelper.ORDER_ASCENDING ou BeanHelper.ORDER_DESCENDING.
	 * @throws InvalidObjectException Disparada quando não for informados os parâmetros.
	 */
	public static void sortList(List list, String property, Boolean order) 
	throws InvalidObjectException {
		if (list != null && property != null && !property.trim().equals("") 
				&& order != null) {
			if (!list.isEmpty()) {
				ReflectionComparator comparator = new ReflectionComparator(
						property, order);
				Collections.sort(list, comparator);
			}
		} else {
			throw new InvalidObjectException("Atributos 'list', 'property' e 'order' devem ser informados.");
		}
	}

	public static void setListProperty(List list, String property, Object value) 
	throws Exception { 
		if (list != null && property != null && value != null) {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object element = iter.next();
				BeanUtils.setProperty(element, property, value);
			}
		} else {
			throw new IllegalArgumentException("Arguments cannot be null.");
		}
	}

	public static Map parseMap(ResultSet rs) throws Exception {
		HashMap beans = null;
		if (rs != null) {
			for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
				if (beans == null) {
					beans = new HashMap(rs.getMetaData().getColumnCount());
				}
				beans.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
		}
		return beans;
	}

	public static List getPopulatedList(Map map, String arrayFilter,
			Class classId) throws Exception {
		ArrayList result = null;
		HashMap beans = new HashMap();

		Set keys = map.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if (key.startsWith(arrayFilter)) {
				String index = getIndex(key);
				String property = getProperty(key);
				if (!beans.containsKey(index)) {
					beans.put(index, classId.newInstance());
				}
				Object bean = beans.get(index);

				BeanUtils.setProperty(bean, property, map.get(key));
			}
		}
		if (beans.values().size() > 0) {
			result = new ArrayList(beans.values());
			result.trimToSize();
		}
		return result;
	}

	public static List getPopulatedList(HttpServletRequest request, 
			String arrayFilter) throws Exception {
		Map map = request.getParameterMap();
		ArrayList result = null;
		HashMap bean = null;
		HashMap beans = new HashMap();

		Set keys = map.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if (key.startsWith(arrayFilter)) {
				String index = getIndex(key);
				String property = getProperty(key);
				if (!beans.containsKey(index)) {
					bean = new HashMap();
					beans.put(index, bean);
				} else {
					bean = (HashMap) beans.get(index);
				}
				bean.put(property, request.getParameter(key));
			}
		}

		if (beans.values().size() > 0) {
			result = new ArrayList(beans.values());
			result.trimToSize();
		}
		return result;
	}

	private static String getProperty(String key) {
		String result = "";
		int i = key.indexOf(".");
		if (i != -1) {
			result = key.substring(i + 1, key.length());
		}
		return result;
	}

	private static String getIndex(String key) {
		String result = "";
		int i = key.indexOf("[");
		int y = key.indexOf("]");

		if (i != -1 && y != -1 && i < y) {
			result = key.substring(i + 1, y);
		}
		return result;
	}


	/**
	 * Popula as propriedades do objeto <code>target</code> com o
	 * <code>java.util.Map</code>
	 * <code>map</code>. O valor de cada chave
	 * encontrada no map será atribuída a uma propriedade com mesmo nome da
	 * chave no target.
	 * 
	 * @param map Map com os dados a serem atribuídos ao target
	 * @param target Objeto que terá suas propriedades populadas.
	 * @throws NoSuchMethodException em caso da propriedade não existir no target
	 * @throws java.lang.IllegalArgumentException 
	 * 				em caso de um dos argumentos forem <code>null</code>
	 */
	public static void populate(Map map, Object target) 
	throws NoSuchMethodException, IllegalArgumentException {
		if (map != null && target != null) {
			try {
				for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
					String key = (String) iter.next();
					Object value = map.get(key);
					if (!value.equals("")) {
						try {
							setProperty(target, key, value);
						} catch (Exception e) {
						}
					}
				}
			} catch (Exception ex) {
				throw new NoSuchMethodException(ex.getMessage());
			}
		} else {
			throw new IllegalArgumentException("Arguments cannot be null.");
		}
	}
	
	public static Object getProperty(Object target, String propertyName) 
	throws NoSuchMethodException, SecurityException, InvocationTargetException, 
	IllegalArgumentException, IllegalAccessException {
		return getReadMethod(target.getClass(), propertyName).invoke(target, 
				(Object[])null);
	}
	
	/**
	 * setProperty
	 * 
	 * @param target TextFaturaDataSource
	 * @param propertyName String
	 * @param value Object
	 */
	public static void setProperty(Object target, String propertyName,
			Object value) throws NoSuchMethodException, SecurityException, 
			InvocationTargetException, IllegalArgumentException, 
			IllegalAccessException {
		boolean stop = false;
		Method writeMethod = null;
		Class cls = value.getClass();
		while (!stop) {
			try {
				writeMethod = getWriteMethod(target.getClass(), propertyName, 
						new Class[] { cls });
				break;
			} catch (NoSuchMethodException ex) {
				if (Object.class.equals(cls)) {
					throw new NoSuchMethodException(
							"No method found for property "	+ propertyName + 
							" that is compatible with 'value' argument class: " 
							+ value.getClass());
				} else {
					cls = cls.getSuperclass();
				}
			}
		}
		writeMethod.invoke(target, new Object[] { value });
	}
	
	public static Method getReadMethod(Class cls, String propertyName) 
	throws SecurityException, NoSuchMethodException {
		return cls.getMethod(getMethodName(GET, propertyName), (Class[]) null);
	}
	
	public static Method getWriteMethod(Class cls, String propertyName, 
			Class[] parameterTypes) throws SecurityException, 
			NoSuchMethodException {
		return cls.getMethod(getMethodName(SET, propertyName), parameterTypes);
	}
	
	private static String getMethodName(String mod, String propertyName) {
		return mod + propertyName.substring(0, 1).toUpperCase() + 
		propertyName.substring(1, propertyName.length());
	}
	
	/**
     * Copia os valores das propriedades de <code>source</code> para <code>target</code>.
     * <code>target</code> deve ser uma isntância de <code>source</code>.
     *
     * @param source objeto que terá suas propriedades lidas
     * @param target objeto que terá suas propriedades atribuídas
     */
    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
