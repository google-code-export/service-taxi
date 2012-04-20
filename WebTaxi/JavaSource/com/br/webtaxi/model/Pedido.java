package com.br.webtaxi.model;

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

	private Integer idPedido;
	private String localizacao;
	private String complemento;
	private String cpfUsuario;
	private Date dataPedido;
	private String status;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
