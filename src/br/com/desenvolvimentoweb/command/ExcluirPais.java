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

public class ExcluirPais implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pId = request.getParameter("id");
		
		int id = -1;
		
		try {
			if (pId != null) id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Pais pais = new Pais();
		pais.setId(id);
		
		PaisService ps = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		ps.excluir(pais.getId());
		
		@SuppressWarnings("unchecked")
		ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
		
		lista.remove(busca(pais, lista));
		
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("listarPaises.jsp");
		
		view.forward(request, response);
	}
	
	public int busca(Pais pais, ArrayList<Pais> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId() == pais.getId()) return i;
		}
		return -1;
	}
}
