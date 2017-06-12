package br.com.courier.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.courier.dominio.Encomenda;
import br.com.courier.dominio.Cliente;
import br.com.courier.persistencia.Conexao;

public class EncomendaDB implements GenericDB<Encomenda, Integer> {
	
	private Connection con;
	private ResultSet rs;
	private Statement stm;
	
	//define formato do campo data
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void inserir(Encomenda encomenda){
		//cria comando insert na tabela encomenda
		String s = "insert into encomenda (id_cliente_r, id_cliente_d, data_entrada, "
				+ " desc_enco, vlr_frete, pgto_status) values("
				+ "'" + encomenda.getClienteR().getId()
				+ "', '" + encomenda.getClienteD().getId()
				+ "', '" + new String(dateFormat.format(encomenda.getDataEntrada().getTime()))
				+ "', '" + encomenda.getDescr()
				+ "', '" + encomenda.getFrete();
			if(encomenda.isStatPgto() == true){
				s += "', '1'";					
			}else{
				s += "', '0'";
			}
				s += ")";
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para inserir os dados na tabela
			stm.executeUpdate(s);
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir na tabela encomenda");
			System.out.println(s);
		}
		finally {
			this.fecha(rs, stm, con);
		}
				
	}
	
	public void excluir(Encomenda encomenda) {
		// cria um comando DELETE usando o id de encomenda
		String s = "delete from encomenda where id_enco = " + encomenda.getId();
		System.out.println(s);
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			
			// executa o comando para excluir o autor da tabela
			stm.executeUpdate(s);
		}
		catch (SQLException e) {
			System.out.println("Erro ao tentar excluir na tabela");
			System.out.println(e.getMessage());
		}
		finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public void modificar(Encomenda encomenda) {
		// cria um comando UPDATE usando os atributos de encomenda
		String s = "update encomenda set id_cliente_r = '" + encomenda.getClienteR().getId()
				+ "', id_cliente_d = '" + encomenda.getClienteD().getId()
				+ "', data_entrada = '" + new String(dateFormat.format(encomenda.getDataEntrada())
				+ "', desc_enco = '" + encomenda.getDescr()
				+ "', vlr_frete = '" + encomenda.getFrete());
				if(encomenda.isStatPgto() == true){
					s += "', pgto_status = '1'";					
				}else{
					s += "', pgto_status = '0'";
				}
			if (encomenda.getDataSaida() == null) {
				s += " where id_enco = " + encomenda.getId();
			}
			else {
			s += "', data_saida = '" + new String(dateFormat.format(encomenda.getDataSaida().getTime()))
				+ "' where id_enco = " + encomenda.getId();
		}
		System.out.println(s);
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para modificar os dados na tabela
			stm.executeUpdate(s);
		}
		catch (SQLException e) {
			System.out.println("Erro ao modificar a tabela");
		}
		finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public List<Encomenda> buscarTodos() {
		// declara um ArrayList para receber os dados da tabela
		List<Encomenda> lista = new ArrayList<Encomenda>();
		String s = "select * from encomenda";
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa a consulta de todos os registros
			rs = stm.executeQuery(s);
			// percorre o ResultSet lendo os dados de encomenda
			while (rs.next()) {
				int idEnco = rs.getInt("id_enco");
				int idClienteR = rs.getInt("id_cliente_r");
				int idClienteD = rs.getInt("id_cliente_d");
				Date dataEntrada = rs.getDate("data_entrada");
				Date dataSaida = rs.getDate("data_saida");
				String descEnco = rs.getString("desc_enco");
				String frete = rs.getString("vlr_frete");
				boolean statPgto = rs.getBoolean("pgto_status");
				Cliente clienteR = new ClienteDB().buscarPorID(idClienteR);
				Cliente clienteD = new ClienteDB().buscarPorID(idClienteD);
				
				
				// cria um encomenda com os dados de um registro
				Encomenda encomenda = new Encomenda(clienteR, clienteD, dataEntrada, dataSaida, descEnco, frete, statPgto);
				// adiciona o encomenda no ArrayList
				lista.add(encomenda);
			}
			
		}
		catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
			System.out.println(s);
		}
		finally {
			this.fecha(rs, stm, con);
		}
		return lista;
	}
	
	//códido do método buscarNãoEntregues
	public List<Encomenda> buscarNaoEntregues() {
		// declara um ArrayList para receber os dados da tabela
		List<Encomenda> lista = new ArrayList<Encomenda>();
		String s = "select * from encomenda where data_saida is null";
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa a consulta de todos os registros
			rs = stm.executeQuery(s);
			// percorre o ResultSet lendo os dados de encomenda
			while (rs.next()) {
				int idEnco = rs.getInt("id_enco");
				int idClienteR = rs.getInt("id_cliente_r");
				int idClienteD = rs.getInt("id_cliente_d");
				Date dataEntrada = rs.getDate("data_entrada");
				Date dataSaida = rs.getDate("data_saida");
				String descEnco = rs.getString("desc_enco");
				String frete = rs.getString("vlr_frete");
				boolean statPgto = rs.getBoolean("pgto_status");
				Cliente clienteR = new ClienteDB().buscarPorID(idClienteR);
				Cliente clienteD = new ClienteDB().buscarPorID(idClienteD);
								
				// cria um encomenda com os dados de um registro
				Encomenda encomenda = new Encomenda(idEnco, clienteR, clienteD, dataEntrada, 
						dataSaida, descEnco, frete, statPgto, idClienteR, idClienteD);
				
				// adiciona o encomenda no ArrayList
				lista.add(encomenda);
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
			//System.out.println(s);
			System.out.println(e.getMessage());
		}
		finally {
			this.fecha(rs, stm, con);
		}
		return lista;
	}
	
	
	// código do método fecha()
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
	
	public Encomenda buscarPorID(Integer id) {
		// cria um SELECT para retornar um Encomenda pelo id
		String s = "select * from Encomenda where id_enco = " + id;
		Encomenda encomenda = null;
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa a consulta
			rs = stm.executeQuery(s);
			// cria um objeto Encomenda com os dados retornados
			if (rs.next()) {
				int idEnco = rs.getInt("id_enco");
				int idClienteR = rs.getInt("id_cliente_r");
				int idClienteD = rs.getInt("id_cliente_d");
				Date dataEntrada = rs.getDate("data_entrada");
				Date dataSaida = rs.getDate("data_saida");
				String descEnco = rs.getString("desc_enco");
				String frete = rs.getString("vlr_frete");
				boolean statPgto = rs.getBoolean("pgto_status");
				Cliente clienteR = new ClienteDB().buscarPorID(idClienteR);
				Cliente clienteD = new ClienteDB().buscarPorID(idClienteD);
				
				
				// cria um encomenda com os dados de um registro
				encomenda = new Encomenda(clienteR, clienteD, dataEntrada, dataSaida, descEnco, frete, statPgto);

			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
			System.out.println(e.getMessage());
		}
		finally {
			this.fecha(rs, stm, con);
		}
		return encomenda;
	}

}
