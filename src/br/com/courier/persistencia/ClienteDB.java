package br.com.courier.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.courier.dominio.Cliente;

public class ClienteDB implements GenericDB<Cliente, Integer> {
	
	private Connection con;
	private ResultSet rs;
	private Statement stm;
	
	public void inserir(Cliente cliente){
		//comando insert com atb de cliente
		String s = "insert into cliente (nome_cliente, cpf) values( '" + cliente.getNome() + "', '" + cliente.getCpf() + "')";
        System.out.println(s);
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para inserir os dados na tabela
			stm.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir na tabela" + e.getMessage());
		} finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public void excluir(Cliente cliente) {
		// cria um comando DELETE usando o id do cliente
		String s = "delete from cliente where id_cliente = " + cliente.getId();
		System.out.println(s);
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para excluir o cliente da tabela
			stm.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Erro ao tentar excluir na tabela");
		} finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public void modificar(Cliente cliente) {
		// cria um comando UPDATE usando os atributos de cliente
		String s = "update cliente set nome_cliente = '" + cliente.getNome() + "' where id_cliente = " + cliente.getId();
		System.out.println(s);
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para modificar os dados na tabela
			stm.executeUpdate(s);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir na tabela");
		} finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public List<Cliente> buscarTodos() {
		// declara um ArrayList para receber os dados da tabela
		List<Cliente> lista = new ArrayList<Cliente>();
		String s = "select * from cliente";
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa a consulta de todos os registros
			rs = stm.executeQuery(s);
			// percorre o ResultSet lendo os dados de Cliente
			while (rs.next()){
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome_cliente");
				String cpf = rs.getString("cpf");
				// cria um Cliente com os dados de um registro
				Cliente cliente = new Cliente(id, nome, cpf);
				// adiciona o Cliente no ArrayList
				lista.add(cliente);
			}		
		} 
		catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
		} finally {
			this.fecha(rs, stm, con);
		}
		return lista;
	}
		
	public void fecha(ResultSet rs, Statement stm, Connection con) {
		if (rs != null) {
		try {
			rs.close();
			}catch (SQLException e){
			
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} 	catch (SQLException e){
				
			}
		}
		if (con != null) {
			try {
				con.close();
			} 	catch (SQLException e){
				
			}
		}
	}
	
	public Cliente buscarPorID(Integer id) {
		// cria um SELECT para retornar um Cliente pelo id
		String s = "select * from cliente where id_cliente = " + id;
		String nome;
		String cpf;
		Cliente cliente = null;
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa a consulta
			rs = stm.executeQuery(s);
			// cria um objeto Cliente com os dados retornados
			if (rs.next()) {
				id = rs.getInt("id_cliente");
				nome = rs.getString("nome_cliente");
				cpf = rs.getString("cpf");
				cliente = new Cliente(id, nome, cpf);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
			System.out.println(e.getMessage());
		} finally {
			this.fecha(rs, stm, con);
		}
		return cliente;
	}

}
