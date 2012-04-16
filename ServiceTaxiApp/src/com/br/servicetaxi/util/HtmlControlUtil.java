package com.br.servicetaxi.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HtmlControlUtil {
	
	public static final String SEPARATOR = "_";

	public static final String CONTROL = "control";
	
	public static final String ID = "id";
	
	
	public static List toList(HttpServletRequest request){
		ArrayList l = new ArrayList();
		HashSet ids = new HashSet();
		ArrayList controls = new ArrayList();
		
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			
			String[] s = name.split(SEPARATOR);
			if (s.length == 3 && s[0].equals(CONTROL)) {
				Map m = new HashMap();
				
				m.put(ID, s[1]);
				
				ids.add(m);
				
				Map n = new HashMap();
				
				n.put(ID, s[1]);
				n.put("key", s[2]);
				String value = request.getParameter(name);
				if (value.equals("")) {
					n.put("value", null);
				} else {
					n.put("value", value);
				}
				controls.add(n);
			}
		}
		for (Iterator ite = ids.iterator(); ite.hasNext();) {
			Map m = (Map) ite.next();
			
			String id = (String) m.get("id");
			Map n = new HashMap();
			for (Iterator iter = controls.iterator(); iter.hasNext();) {
				Map p = (Map) iter.next();
				
				String controlId = (String) p.get("id");
				if (controlId.equals(id)) {
					n.put(p.get("key"), p.get("value"));
				}
			}
			l.add(n);
		}
		l.trimToSize();
		
		return l;
	}
}
