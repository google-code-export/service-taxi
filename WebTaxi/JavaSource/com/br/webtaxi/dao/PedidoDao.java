package com.br.webtaxi.dao;

import java.util.Date;
import java.util.List;

import com.br.webtaxi.model.Pedido;

public interface PedidoDao {
	
	public List<Pedido> listaPedidos();
	
	public List<Pedido> listaPedidosPeriodo(Date dtInicio, Date dtFim);

	
}
