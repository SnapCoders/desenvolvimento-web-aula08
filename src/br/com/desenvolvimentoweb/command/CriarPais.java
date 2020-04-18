package br.com.desenvolvimentoweb.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.desenvolvimentoweb.model.Pais;
import br.com.desenvolvimentoweb.service.PaisService;

public class CriarPais implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		
		int populacao = 0;
		double area = 0.0; 
		
		try {
			if (pPopulacao != null) populacao = Integer.parseInt(pPopulacao);
			if (pArea != null) area = Double.parseDouble(pArea);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Pais pais = new Pais();
		
		pais.setNome(pNome);
		pais.setPopulacao(populacao);
		pais.setArea(area);
		
		PaisService ps = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		ps.criar(pais);
		@SuppressWarnings("unchecked")
		ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
		if (lista != null) lista.add(pais);
		else lista = ps.listarPaises();
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("listarPaises.jsp");
		
		view.forward(request, response);
	}
}
