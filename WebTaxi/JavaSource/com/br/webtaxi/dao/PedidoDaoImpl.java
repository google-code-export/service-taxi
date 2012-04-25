package com.br.webtaxi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.br.webtaxi.conexao.ConnectionFactory;
import com.br.webtaxi.model.Pedido;

public class PedidoDaoImpl implements PedidoDao {

	@Override
	public List<Pedido> listaPedidos() throws SQLException, Exception {
		
		String sql = "select * from tb_pedidos where status='A'";
		
		PreparedStatement pstm = null;
		
		ResultSet rs = null;
		
		List<Pedido> listaPedidos = new ArrayList<Pedido>();
		
		try {
		pstm = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
		
		rs = pstm.executeQuery();
		
		} catch (SQLException e) {
			ConnectionFactory.getInstance().getConnection().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(rs.next()){
			
		Integer idPedido = rs.getInt("id_pedido");
		String localizacao = rs.getString("localizacao");
		String complemento = rs.getString("complemento");
		String status = rs.getString("status");
		String cpfUsuario = rs.getString("cpf_usuario");
		Date dtPedido = rs.getDate("data_pedido");
			
			
		Pedido ped = new Pedido();
		
		ped.setIdPedido(idPedido);
		ped.setLocalizacao(localizacao);
		ped.setComplemento(complemento);
		ped.setCpfUsuario(cpfUsuario);
		ped.setStatus(status);
		ped.setDataPedido(dtPedido);
			
		listaPedidos.add(ped);
			
		}
				
		return listaPedidos;
	}

	@Override
	public List<Pedido> listaPedidosPeriodo(Date dtInicio, Date dtFim) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
