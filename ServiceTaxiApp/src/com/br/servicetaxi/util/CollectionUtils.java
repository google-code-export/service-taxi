package com.br.servicetaxi.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CollectionUtils {

    /**
     * Retorna uma <code>java.util.Collection</code> a partir do <code>elements</code>.
     * Se <code>elements</code> for um Map, a coleção de valores é retornada, uma alteração na collection
     * afetará o Map.
     * Se <code>elements</code> for um array de objetos(<code>Object[]</code>), uma lista representando esse array
     * será retornada.
     * Se <code>elements</code> for uma String, o conteúdo será separado por "," e retornado uma lista.
     * Se <code>elements</code> for <code>null</code>, uma coleção vazia será retornada.
     * 
     * @param elements elementos para serem transformados em collection
     * @return uma <code>java.util.Collection</code> a partir do <code>elements</code>.
     * @see java.util.Arrays#asList(Object[])
     */
    public static Collection collect(Object obj) {
    	if (obj instanceof Map) {
            return ((Map) obj).values();
        } else {
            if (obj instanceof Object[]) {
                return Arrays.asList((Object[]) obj);
            } else {
                if (obj instanceof String) {
                    return Arrays.asList(((String) obj).split(","));
                } else {
                    if (obj instanceof Collection) {
                        return (Collection) obj;
                    }
                }
            }
        }
        ArrayList<Object> list = new ArrayList<Object>();
        if(obj != null){
            list.add(obj);
        }
        return list;
    }
    
    /**
     * Retorna <code>true</code> se <code>obj</code> estiver contido em <code>c</code>.
     * 
     * @param c coleção de objetos
     * @param obj objeto para estar se faz parte de <code>c</code>
     * @return <code>true</code> se <code>element</code> tiver contido em <code>source</code>.
     */
    public static boolean contains(Collection c, Object obj){
        try {
            return c.contains(obj);
        } catch (Exception e) {
            return false;
        }
    }
    
    public static final List toList(ResultSet rs, List wantedColumnNames) 
    throws SQLException{
        List<Object> rows = new ArrayList<Object>();
        
        int numWantedColumns = wantedColumnNames.size();
        while (rs.next()){
            Map<String, Object> row = new LinkedHashMap<String, Object>();
 
            for (int i = 0; i < numWantedColumns; ++i){
                String columnName = (String) wantedColumnNames.get(i);
                Object value = rs.getObject(columnName);
                row.put(columnName, value);
            }
            rows.add(row);
        } 
        return rows;
    }

    public static List orderMapListByName(List<Object> list){
    	Collections.sort (list, new Comparator(){
            public int compare(final Object o1, final Object o2) {
                final String s1 = (String)((Map) o1).get("nome");
                final String s2 = (String)((Map) o2).get("nome");
                
                return s1.compareTo(s2);
            }
        });
    	return list;
    }
    
    public static String listToString(String[] itens){
    	String result = null;
    	for (int i = 0; i < itens.length; i++){
    		if (result == null){
    			result = itens[i];
    		}else{
    			result = result + ", " + itens[i];
    		}
    	}
    	return result;
    }
    
    /**
	 * Returns a list containing the elements of the array of string. 
	 * 
	 * @param array  array of string.
	 * @param field  name of the field.
	 * @return list
	 */
    public static List toList(String[] array, String field){
		List list = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			Map map = new HashMap();
			map.put(field, array[i]);
			list.add(map);
		}
		return list;
	}
    
    public static HashMap listToMap(List list) {   
    	  HashMap ret = new HashMap();   
    	  for(Iterator i = list.iterator(); i.hasNext();) {   
    	    String e = (String) i.next();   
    	    ret.put(e,e);   
    	  }   
    	  return ret;   
    }  

}
