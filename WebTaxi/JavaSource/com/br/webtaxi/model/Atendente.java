package com.br.webtaxi.model;

public class Atendente {

	private Integer idAtendente;
	private String matricula;
	private String nome;
	private String cpf;
	private String telefone;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getIdAtendente() {
		return idAtendente;
	}

	public void setIdAtendente(Integer idAtendente) {
		this.idAtendente = idAtendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
