package com.br.servicetaxi.dao;



import java.util.List;
import java.util.Map;


public interface JdbcDAO {
	
	public String getSqlFromList(String sqlId) throws Exception;

	public String[] extractMetaData(String tableName) throws Exception;
	
	public String[] extractMetaData(String sqlId, Map parms) throws Exception;

	public List execute(String id) throws Exception;

	public List execute(String id, Map parms) throws Exception;

	public List execute(String id, Class classe) throws Exception;

	public List execute(String id, Map parms, Class classe) throws Exception;

	public List executeSelect(String sql) throws Exception;

	public List executeSelect(String sql, Map parms) throws Exception;

	public List executeSelect(String sql, Class classe) throws Exception;

	public List executeSelect(String sql, Map parms, Class classe) throws Exception;

	public int executeUpdate(String sql) throws Exception;
}