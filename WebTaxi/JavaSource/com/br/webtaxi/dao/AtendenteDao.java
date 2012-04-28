package com.br.webtaxi.dao;

import java.util.List;

import com.br.webtaxi.model.Atendente;

public interface AtendenteDao {
	
	void insertAtendente(Atendente at);

	void updateAtendente(Atendente at);
	
	void deleteAtendente(Atendente at);
	
	List<Atendente> listaAtendentes();
	
}
