package br.com.courier.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static final String 
	URL = "jdbc:firebirdsql://localhost/c:/users/marce/documents/bsi/dsi/courier/courier.fdb?user=sysdba&password=masterkey";
	
	public static Connection criarConexao() throws SQLException {
		Connection con = (Connection) DriverManager.getConnection(URL);
		return con;
	}

}
