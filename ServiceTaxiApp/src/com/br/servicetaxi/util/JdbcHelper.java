package com.br.servicetaxi.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JdbcHelper {
	
	public static void close(Connection conn, PreparedStatement pstmt, 
			Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
			}
		} catch (SQLException e) {
			;
		}
		rset = null;

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			;
		}
		stmt = null;

		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			;
		}
		pstmt = null;
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			;
		}
		conn = null;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, 
			ResultSet rset) {
		close(conn, pstmt, null, rset);
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rset) {
		close(conn, null, stmt, rset);
	}
}
