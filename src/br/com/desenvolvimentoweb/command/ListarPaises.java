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

public class ListarPaises implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pChave = request.getParameter("data[search]");
		String pAcao = request.getParameter("acao");
		
		PaisService ps = new PaisService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		
		if (pAcao.equals("Buscar")) {
			
			if (pChave != null && pChave.length() > 0) {
				
				lista = ps.listarPaises(pChave);
				
			} else {
				
				lista = ps.listarPaises();
				
			}
			
			session.setAttribute("lista", lista);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("listarPaises.jsp");
		view.forward(request, response);
	}
}
