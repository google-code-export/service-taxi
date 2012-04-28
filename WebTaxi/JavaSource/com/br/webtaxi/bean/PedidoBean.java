package com.br.webtaxi.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.webtaxi.model.Pedido;

@ManagedBean
@SessionScoped
public class PedidoBean {

	private List<Pedido> listaPedidos;

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

}
