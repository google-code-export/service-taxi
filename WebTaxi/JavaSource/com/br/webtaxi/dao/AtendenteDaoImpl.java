package com.br.webtaxi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.br.webtaxi.conexao.ConnectionFactory;
import com.br.webtaxi.model.Atendente;

public class AtendenteDaoImpl implements AtendenteDao {

	@Override
	public void insertAtendente(Atendente at) {
		
		String sql = "insert into tb_atendentes(matricula,cpf,nome,telefone) values(?,?,?,?)";
		
		try {
		
		PreparedStatement pstm = null;
		
		pstm = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
		
		pstm.setString(1, at.getMatricula());
		
		pstm.setString(2, at.getCpf());
		pstm.setString(3, at.getNome());
		pstm.setString(4, at.getTelefone());
		
		pstm.execute();	
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void updateAtendente(Atendente at) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update tb_atendentes set nome='"+at.getNome()+"',cpf='"+at.getCpf()+"' , telefone='"+at.getTelefone()+"'");
		
			
		try {
		
		PreparedStatement pstm = null;
		
		pstm = ConnectionFactory.getInstance().getConnection().prepareStatement(sql.toString());

		pstm.executeUpdate();
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAtendente(Atendente at) {
		
		String sql = "delete from tb_atendentes where id_atendente="+at.getIdAtendente()+";";
		
		try{
		
		PreparedStatement pstm = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
		
		pstm.execute();
	
		}catch(Exception e){
			
			e.printStackTrace();		
			
		}
	}

	@Override
	public List<Atendente> listaAtendentes() {
		
		String sql = "select * from tb_atendentes;";
		
		List<Atendente> lista = new ArrayList<Atendente>();
		
		try{
		
		ResultSet rs = ConnectionFactory.getInstance().executeSql(sql);
		
			
		if(rs != null){
			
		while(rs.next()){
			
		Atendente at = new Atendente();
		
		at.setIdAtendente(rs.getInt("id_atendente"));
		at.setNome(rs.getString("nome"));
		at.setCpf(rs.getString("cpf"));
		at.setMatricula(rs.getString("matricula"));
		at.setTelefone(rs.getString("telefone"));		
			
		lista.add(at);
			
		}
			
		}
			
		}catch(Exception e){
			
		e.printStackTrace();

		}
		
		return lista;
	}
	
	

}
