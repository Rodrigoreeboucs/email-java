package br.com.fabrica.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fabrica.model.Cliente;

public class ClienteService {
	
	private static List<Cliente> lista = new ArrayList<Cliente>(); 
	
	public void cadastrar(Cliente cliente) {
		lista.add(cliente);
	}
	
	public List<Cliente> getTodosClientes(){
		return lista;
	}
	
	public void excluir(int indice) {
		lista.remove(indice);
	}

	public Cliente buscarPorIndice(int indice) {
		return lista.get(indice);
		
	}

}
