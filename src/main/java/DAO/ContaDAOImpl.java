package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.interfaces.ContaDAO;
import JDBC.DbManager;
import model.Conta;
import model.Operacao;


public class ContaDAOImpl implements ContaDAO {

	private Connection conexao;
	private static ContaDAOImpl instance;
	
	public static ContaDAOImpl getInstacia() {
		if(instance == null) {
			instance = new ContaDAOImpl();
		}
		
		return instance;
	}
	
	@Override
	public void createConta(Conta conta, int idUsuario) throws SQLException, ClassNotFoundException {
			PreparedStatement stmt = null;
		
		try {
			 conexao = DbManager.obterInstancia().obterConexao();
				stmt = conexao.prepareStatement("INSERT INTO T_CONTA ( cd_conta, vl_saldo ) VALUES ( ?, ?)");
				stmt.setDouble(2, conta.getSaldo());
				stmt.setInt(1, idUsuario);
					
				stmt.executeUpdate();
				System.out.println(conta.toString () + " criada");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Conta getConta(int id) throws SQLException, ClassNotFoundException {
		Conta conta = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = DbManager.obterInstancia().obterConexao();
			System.out.println(id);
			stmt = conexao.prepareStatement("SELECT * FROM T_CONTA WHERE cd_conta = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
		
			
			while(rs.next()) {		
				double valor = rs.getDouble("vl_saldo");
				conta = new Conta(valor, new ArrayList<Operacao>(),id);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conta;
	}

}
