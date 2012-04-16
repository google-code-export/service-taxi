package com.br.servicetaxi.util;

public class HtmlUtil {
	
	public static String setContentType(String tipo){
		String contentType = "text/html";
		if (tipo.equals("xls")){
			contentType = "application/vnds";
		} else if (tipo.equals("ods")){
			contentType = "application/vnd.oasis.opendocument.spreadsheet";
		}
		return contentType;
	}
}
