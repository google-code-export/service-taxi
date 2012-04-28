package com.br.webtaxi.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.br.webtaxi.dao.AtendenteDao;
import com.br.webtaxi.dao.AtendenteDaoImpl;
import com.br.webtaxi.model.Atendente;

@ManagedBean
@SessionScoped
public class AtendenteBean {

	private AtendenteDao atendenteDao;
	private Atendente atendente;

	/**
	 * Construtor
	 */
	public AtendenteBean() {

		atendente = new Atendente();
		atendenteDao = new AtendenteDaoImpl();

	}

	public AtendenteDao getAtendenteDao() {
		return atendenteDao;
	}

	public void setAtendenteDao(AtendenteDao atendenteDao) {
		this.atendenteDao = atendenteDao;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	
	public void insertAtendente(){
		
		FacesMessage msg = null; 
		
		try{
		
		atendenteDao.insertAtendente(atendente);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro realizado com sucesso.", "");	
		
		}catch(Exception e){
			
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar a atendente.", "");	
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg); 
		
	}
	
	public void updateAtendente(){
		
		FacesMessage msg = null; 
		
		try{
		
		atendenteDao.updateAtendente(atendente);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro atualizado com sucesso.", "");	
		
		}catch(Exception e){
			
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar a atendente.", "");	
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg); 
		
	}
	
	public void deleteAtendente(){
		
		FacesMessage msg = null; 
		
		try{
		
		atendenteDao.deleteAtendente(atendente);
		
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "cadastro excluído com sucesso.", "");	
		
		}catch(Exception e){
			
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir a atendente.", "");	
		}
		
		FacesContext.getCurrentInstance().addMessage(null, msg); 
		
	}

	
}
