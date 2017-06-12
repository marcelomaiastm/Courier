package br.com.courier.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultasDB {
	
	private Connection con;
	private ResultSet rs;
	private Statement stm;
	
	
	public List<Object[]> consultaEmprestimos(String nomeDest) {
		List<Object[]> lista = new ArrayList<Object[]>();
		String s;
		try {
			con = Conexao.criarConexao();
			stm = con.createStatement();
			
			if (nomeDest.equals("")) {
				s = "SELECT b.NOME_CLIENTE, a.DESC_ENCO, a.PGTO_STATUS FROM ENCOMENDA a, CLIENTE b " +
						"WHERE a.ID_CLIENTE_D = b.ID_CLIENTE AND a.DATA_SAIDA IS NULL ORDER BY b.NOME_CLIENTE";
	
			} else {
				s = "SELECT b.NOME_CLIENTE, a.DESC_ENCO, a.PGTO_STATUS FROM ENCOMENDA a, CLIENTE b " +
						"WHERE a.ID_CLIENTE_D = b.ID_CLIENTE AND a.DATA_SAIDA IS NULL AND a.NOME_CLIENTE = '" + nomeDest +
						"' ORDER BY b.NOME_CLIENTE";
			}
			
			rs = stm.executeQuery(s);
			while (rs.next()) {
				String dest = rs.getString("NOME_CLIENTE");
				String descEnco = rs.getString("DESC_ENCO");
				
				String pgtoStatus;
				if(rs.getInt("PGTO_STATUS") == 0){
					pgtoStatus = "A PAGAR";
				}else{
					pgtoStatus = "PAGO";
				}
				lista.add(new Object[] {dest, descEnco, pgtoStatus});
			}
		
		} catch (SQLException e) {
			System.out.println("Erro ao criar conexão");
		}
		finally {
			this.fecha(rs, stm, con);
		}
		return lista;
		}
	
	//método fecha()
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
		
}
