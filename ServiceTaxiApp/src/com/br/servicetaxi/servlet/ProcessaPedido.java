package com.br.servicetaxi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet que recebe o pedido e o processa
 * e devolve a resposta para o cliente.
 * 
 * 
 */
public class ProcessaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessaPedido() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nomeUsuario = request.getParameter("nomeUsuario");
		String localizacao = request.getParameter("localizacao");
		String complemento = request.getParameter("complemento");
		
		

		
		
		
	}
}
