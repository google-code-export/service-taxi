package com.br.webtaxi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.br.webtaxi.conexao.ConnectionFactory;

public class JdbcUtil {

	
	public static boolean validaLogin(String usuario,String senha){
		
		ResultSet rs = null;
		try {
			rs = ConnectionFactory.getInstance().executeSql("select matricula,senha from tb_login where" +
					" matricula="+usuario+" and senha='"+senha+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(rs != null && rs.next())
				return true;
			else 
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return false;
		
	}
	
	
}
