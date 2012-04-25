package com.br.webtaxi.managedbean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.webtaxi.dao.PedidoDao;
import com.br.webtaxi.dao.PedidoDaoImpl;
import com.br.webtaxi.model.Pedido;

@ManagedBean
@SessionScoped
public class PedidoBean {

	private List<Pedido> listaPedidos;
	private int count; 
	private PedidoDao pedidoDao;

	public PedidoBean(){
		
	pedidoDao = new PedidoDaoImpl();		
		
	}
	
	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDao pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public List<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	  
    public int getCount() {  
        return count;  
    }  
  
    public void setCount(int count) {  
        this.count = count;  
    }  
      
    public void increment() {  
        count++;  
    }  
    
    public void listaPedidos(){
    	
    try {
		listaPedidos = pedidoDao.listaPedidos();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
    	 	
    }

}
