package br.com.courier.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		String s = "insert into encomenda (id_enco, cliente_rem, cliente_dest, data_entrada, "
				+ " desc_enco, vlr_frete, pgto_status, enco_status) values("
				+ encomenda.getId()
				+ ", '" + encomenda.getClienteR()
				+ ", '" + encomenda.getClienteD()
				+ "', '" + new String(dateFormat.format(encomenda.getDataEntrada().getTime()))
				+ ", ," + encomenda.getDescr()
				+ ", '" + encomenda.getFrete()
				+ ", '" + encomenda.isStatPgto()
				+ ", '" + encomenda.getEncoStatus()
				+ "')";
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para inserir os dados na tabela
			stm.executeUpdate(s);
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir na tabela encomenda");
		}
		finally {
			this.fecha(rs, stm, con);
		}
				
	}
	
	public void excluir(Encomenda encomenda) {
		// cria um comando DELETE usando o id de encomenda
		String s = "delete from encomenda where id = " + encomenda.getId();
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			// executa o comando para excluir o autor da tabela
			stm.executeUpdate(s);
		}
		catch (SQLException e) {
			System.out.println("Erro ao tentar excluir na tabela");
		}
		finally {
			this.fecha(rs, stm, con);
		}
	}
	
	public void modificar(Encomenda encomenda) {
		// cria um comando UPDATE usando os atributos de encomenda
		String s = "update encomenda set cliente_rem = '" + encomenda.getClienteR()
				+ "', cliente_dest = '" + encomenda.getClienteD()
				+ "', data_entrada = '" + new String(dateFormat.format(encomenda.getDataEntrada().getTime()
				+ "', desc_enco = '" + encomenda.getDescr()
				+ "', vlr_frete = '" + encomenda.getFrete()
				+ "', pgto_status = '" + encomenda.isStatPgto()
				+ "', enco_status = '" + encomenda.getEncoStatus()));
			if (encomenda.getDataSaida() == null) {
				s += "' where id = " + encomenda.getId();
			}
			else {
			s += "', data_saida = '" + new String(dateFormat.format(encomenda.getDataSaida().getTime()))
				+ "' where id = " + encomenda.getId();
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
				int id = rs.getInt("id_enco");
				int idCliente = rs.getInt("id_cliente");
				Cliente clienteR = new ClienteDB().buscarPorID(idCliente);
				//Cliente clienteD = rs.getString("cliente_dest");
				Date dataEntrada = rs.getDate("data_entrada");
				Date dataSaida = rs.getDate("data_saida");
				String descEnco = rs.getString("desc_enco");
				float frete = rs.getFloat("vlr_frete");
				boolean statPgto = rs.getBoolean("pgto_status");
				String encoStatus = rs.getString("enco_status");
				
				
				// cria um encomenda com os dados de um registro
				Encomenda encomenda = new Encomenda(id, clienteR, dataEntrada, dataSaida, descEnco, frete, statPgto, encoStatus);
				// adiciona o encomenda no ArrayList
				lista.add(encomenda);
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao consultar tabela");
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
				id = rs.getInt("id_enco");
				int idCliente = rs.getInt("id_cliente");
				Cliente clienteR = new ClienteDB().buscarPorID(idCliente);
				//Cliente clienteD = rs.getString("cliente_dest");
				Date dataEntrada = rs.getDate("data_entrada");
				Date dataSaida = rs.getDate("data_saida");
				String descEnco = rs.getString("desc_enco");
				float frete = rs.getFloat("vlr_frete");
				boolean statPgto = rs.getBoolean("pgto_status");
				String encoStatus = rs.getString("enco_status");
				
				
				// cria um encomenda com os dados de um registro
				encomenda = new Encomenda(id, clienteR, dataEntrada, dataSaida, descEnco, frete, statPgto, encoStatus);

			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao consultar na tabela");
		}
		finally {
			this.fecha(rs, stm, con);
		}
		return encomenda;
	}

}
