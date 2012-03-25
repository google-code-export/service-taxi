package com.br.servicetaxi.model;

import java.util.Date;

/**
 * 
 * classe que representa o pedido do taxi apartir do cliente sendo esse site ou
 * android
 * 
 * @author fmota
 * 
 * 
 * 
 */
public class Pedido {

	private Integer id;
	private String nomeUsuario;
	private Date horaPedido;
	private String status;
	private String endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Date horaPedido) {
		this.horaPedido = horaPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
