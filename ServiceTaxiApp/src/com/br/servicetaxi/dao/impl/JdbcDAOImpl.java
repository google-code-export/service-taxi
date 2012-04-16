package com.br.servicetaxi.dao.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.br.servicetaxi.dao.JdbcDAO;
import com.br.servicetaxi.util.BeanHelper;
import com.br.servicetaxi.util.JdbcHelper;


public class JdbcDAOImpl extends JdbcDaoSupport implements JdbcDAO {

	private String xmlFile;

	private Map sqlList;

	
	protected void initDao() throws Exception {
		super.initDao();
		Document xml = new SAXReader().read(getClass().getResourceAsStream(xmlFile));
		List l = xml.selectNodes("/sqlfile/sql");

		sqlList = new HashMap(l.size());
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			DefaultElement node = (DefaultElement) iter.next();
			sqlList.put(node.attribute("id").getValue(), node.getText());
		}
	}
	
	private String makeSql(String sql, Map parms) throws Exception {
		if (sql != null && parms != null && !parms.isEmpty()) {
			Set parameters = parms.keySet();
			for (Iterator iter = parameters.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				sql = sql.replaceAll(element, parms.get(element).toString());
			}
		}
		return sql;
	}

	public String getSqlFromList(String sqlId) throws Exception {
		return sqlList.get(sqlId).toString();
	}

	public List execute(String id) throws Exception {
		return execute(id, null, null);
	}

	public List execute(String id, Map parms) throws Exception {
		return execute(id, parms, null);
	}

	public List execute(String id, Class classe) throws Exception {
		return execute(id, null, classe);
	}

	public List execute(String sqlId, Map parms, Class classe) throws Exception {
		String sql = getSqlFromList(sqlId);
		List result = new ArrayList();

		if (sql != null && !sql.equals("")) {
			sql = makeSql(sql, parms);
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();

			if (classe == null) {
				result = toMapList(rset);
			} else {
				result = toObjectList(rset, classe);
			}
			JdbcHelper.close(conn, pstmt, null, rset);
		}
		return result;
	}

	public List executeSelect(String sql) throws Exception {
		return executeSelect(sql, null, null);
	}

	public List executeSelect(String sql, Map parms) throws Exception {
		return executeSelect(sql, parms, null);
	}

	public List executeSelect(String sql, Class classe) throws Exception {
		return executeSelect(sql, null, classe);
	}

	public List executeSelect(String sql, Map parms, Class classe) throws Exception {
		List result = new ArrayList();
		if (sql != null && !sql.equals("")) {
			sql = makeSql(sql, parms);
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();

			if (classe == null) {
				result = toMapList(rset);
			} else {
				result = toObjectList(rset, classe);
			}
			//JdbcHelper.close(conn, pstmt, null, rset);
		}
		return result;
	}

	private List toMapList(ResultSet rset) throws Exception {
		ArrayList result = new ArrayList();
		ResultSetMetaData meta = rset.getMetaData();
		while (rset.next()) {
			Map element = new HashMap();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				element.put(meta.getColumnName(i), rset.getObject(i));
			}
			result.add(element);
		}
		result.trimToSize();
		
		return result;
	}

	/*
	private List toObjetcArrayList(ResultSet rset) throws Exception {
		ArrayList result = new ArrayList();
		ResultSetMetaData meta = rset.getMetaData();
		while (rset.next()) {
			Object[] element = new Object[meta.getColumnCount()];
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				element[i-1] = rset.getObject(i);
			}
			result.add(element);
		}
		result.trimToSize();
		return result;
	}
	*/

	private List toObjectList(ResultSet rset, Class classe) throws Exception {
		ArrayList result = new ArrayList();
		ResultSetMetaData meta = rset.getMetaData();
		while (rset.next()) {
			Object obj = classe.newInstance();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				BeanHelper.setProperty(obj, meta.getColumnName(i), rset.getString(i));
			}
			result.add(obj);
		}
		result.trimToSize();
		
		return result;
	}

	private String[] extractMetaData(ResultSet rset) throws Exception {
		ResultSetMetaData meta = rset.getMetaData();
		String[] vet = new String[meta.getColumnCount()];
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			vet[i - 1] = meta.getColumnName(i);
		}
		return vet;
	}
	
	private String[] extractMetaDataFromSql(String sql) throws Exception {
		Connection conn = getDataSource().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(sql);

		String[] result = extractMetaData(rset);

		JdbcHelper.close(conn, null, stmt, rset);
		
		return result;
	}
	
	public String[] extractMetaData(String tableName) throws Exception {
		return extractMetaDataFromSql("SELECT * FROM " + tableName + " WHERE rownum = 1");
	}

	public String[] extractMetaData(String sqlId, Map parms) throws Exception {
		String sql = getSqlFromList(sqlId);
		sql = makeSql(sql, parms);
		
		return extractMetaDataFromSql(sql);
	}
	
	public String getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public int executeUpdate(String sql) throws Exception {
		if (sql == null && sql.equals(""))
			return -1;
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int num = pstmt.executeUpdate();

		JdbcHelper.close(conn, pstmt, null, null);

		return num;
	}
}
