package com.br.webtaxi.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {  


	private static ConnectionFactory connectionFactory = null;  
	private static Connection connection = null;  

	private ConnectionFactory(){   }  


	public static ConnectionFactory getInstance(){  

		if(connectionFactory == null)  
			connectionFactory = new ConnectionFactory();   

		return connectionFactory;  

	}  



	public Connection getConnection() throws Exception{  

		if(connection == null){  
			try {  
				// Obtém a raiz da hierarquia de nomes
				InitialContext contexto = new InitialContext();

				// Obtém a origem dos dados
				DataSource ds = (DataSource)contexto.lookup("java:comp/env/jdbc/serviceTaxi");

				connection = ds.getConnection();

			}   
			catch (Exception e) {  
				System.out.println("\n>> Problema ao obter conexao\n");  
				e.printStackTrace();  
				throw new Exception();  
			}  
		}  

		return connection;  
	}  




	public void closeConnection(){  

		if(connection != null){  

			try {     
				connection.close();   
			}   
			catch (SQLException e) {  

				System.out.println("\n>> Problema ao fechar conexao\n");  
				e.printStackTrace();  

			}  
		}  

	}  


	public void finalize(){  

		closeConnection();  
	}  
	
	public ResultSet executeSql(String sql) throws SQLException, Exception{
		
		PreparedStatement pstm = getConnection().prepareStatement(sql);
		
		ResultSet rs = pstm.executeQuery();
		
		if(rs != null)
			return rs;
		else
			return null;
		
		
	}


} 