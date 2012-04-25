package com.br.webtaxi.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.br.webtaxi.model.Pedido;

public interface PedidoDao {
	
	public List<Pedido> listaPedidos() throws SQLException, Exception;
	
	public List<Pedido> listaPedidosPeriodo(Date dtInicio, Date dtFim);

	
}
