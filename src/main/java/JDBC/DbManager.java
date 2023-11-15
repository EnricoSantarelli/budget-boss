package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

	private static DbManager instancia;
	static String dbURL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	static String username = "RM99116";
	static String password = "230703";

	private DbManager() {
	};

	public static DbManager obterInstancia() {
		if (instancia == null) {
			instancia = new DbManager();
		}
		return instancia;
	}

	public Connection obterConexao() throws ClassNotFoundException, SQLException {

		Connection conexao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conexao = DriverManager.getConnection(dbURL, username, password);

		} catch (SQLException e) {
			System.err.println("Não foi possível conectar ao banco de dados");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado");
			e.printStackTrace();
		}

		return conexao;
	}

}
