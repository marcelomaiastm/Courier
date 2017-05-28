package br.com.courier.ui;

import java.sql.SQLException;

import br.com.courier.dominio.Cliente;
import br.com.courier.persistencia.ClienteDB;

public class Main {

	public static void main(String[] args) throws SQLException {
	    Cliente cliente = new Cliente(1, "Maria das Merdas", "12345678911");
	    ClienteDB clienteDB = new ClienteDB();
	    //String s = "insert into autor values( " + cliente.getId() +", '" + cliente.getNome() + "', '" + cliente.getCpf() + "')";
	    //System.out.println(s);
	    clienteDB.inserir(cliente);
	    
	  }

}
