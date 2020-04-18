package br.com.desenvolvimentoweb.test;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.desenvolvimentoweb.model.Pais;
import br.com.desenvolvimentoweb.service.PaisService;

public class TestePais {
	private static String str = "Escolha a a��o que deseja realizar.\n\n" +
			 "1 - Criar um novo pa�s\n\n" +
			 "2 - Atualizar um pa�s existente\n\n" +
			 "3 - Exclu�r um pa�s existenten\n\n" +
			 "4 - Carregar um pa�s\n\n" +
			 "5 - Carregar os pa�ses com a maior popula��o\n\n" +
			 "6 - Carregar os pa�ses com a menor �rea\n\n" +
			 "7 - Carregar 3 pa�ses em tela\n\n" +
			 "8 - Sair do sistema";
	
	public static void main(String[] args) {
		while (true) {
			int escolha = Integer.parseInt(JOptionPane.showInputDialog(str));
			
			switch (escolha) {
				case 1: cadastrar(); break;
				case 2: atualizar(); break;
				case 3: excluir(); break;
				case 4: carregar(); break;
				case 5: maiorPopulacao(); break;
				case 6: menorArea(); break;
				case 7: vetorTresPaises(); break;
				case 8: System.exit(0); break;
				default: JOptionPane.showMessageDialog(null, "Escolha uma das op��es fornecidas!"); break;
			}
		}
	}
	
	static void cadastrar() {
		String nome = JOptionPane.showInputDialog("Digite o nome do pa�s: ");
		long populacao = Long.parseLong(JOptionPane.showInputDialog("Digite o n�mero da popula��o do pa�s: "));
		double area = Double.parseDouble(JOptionPane.showInputDialog("Digite a �rea total do pais: "));
		
		Pais pais = new Pais(nome, populacao, area);
		
		try {
			PaisService service = new PaisService();
			service.criar(pais);
			
			JOptionPane.showMessageDialog(null, "Pa�s cadastrado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void atualizar() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do pa�s: "));
		
		String nome = JOptionPane.showInputDialog("Digite o novo nome deste pa�s: ");
		long populacao = Long.parseLong(JOptionPane.showInputDialog("Digite a nova popula��o do pa�s: "));
		double area = Double.parseDouble(JOptionPane.showInputDialog("Digite a nova �rea total do pais: "));
		
		Pais pais = new Pais(id, nome, populacao, area);
		
		try {
			PaisService service = new PaisService();
			service.atualizar(pais);
			
			JOptionPane.showMessageDialog(null, "Pa�s atualizado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void excluir() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do pa�s que deseja exclu�r"));
		
		Pais pais = new Pais();
		pais.setId(id);
		
		try {
			PaisService service = new PaisService();
			service.excluir(pais.getId());
			
			JOptionPane.showMessageDialog(null, "Pa�s exclu�do com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void carregar() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do pa�s que deseja exclu�r"));
		
		try {
			PaisService service = new PaisService();
			Pais pais = service.carregar(id);
			
			JOptionPane.showMessageDialog(null, "Pa�s encontrado: " + pais.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void maiorPopulacao() {
		try {
			PaisService service = new PaisService();
			Pais pais = service.maiorPopulacao();
			
			JOptionPane.showMessageDialog(null, "Pa�s com maior popula��o: " + pais.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void menorArea() {
		try {
			PaisService service = new PaisService();
			Pais pais = service.menorArea();
			
			JOptionPane.showMessageDialog(null, "Pa�s com menor �rea: " + pais.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	static void vetorTresPaises() {
		try {
			PaisService service = new PaisService();
			ArrayList<Pais> paises = service.vetorTresPaises();
			
			String paisesString = "";
			
			for (Pais pais : paises) {
				paisesString += pais.toString() + "\n";
			}
			
			JOptionPane.showMessageDialog(null, "3 Pa�ses listados:\n\n" + paisesString);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
