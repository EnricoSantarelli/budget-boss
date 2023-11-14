package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.interfaces.OperacaoDAO;
import model.Operacao;
import JDBC.DbManager;
import enums.TipoOperacao;
import enums.TipoOperacao.TipoOperacaoEnum;

public class OperacaoDAOImpl implements OperacaoDAO {

	private Connection conexao;
	private static OperacaoDAOImpl instancia;

	public static OperacaoDAOImpl getInstancia() {
		if (instancia == null) {
			instancia = new OperacaoDAOImpl();
		}
		return instancia;
	}

	@Override
	public List<Operacao> getAllOperacoes(int cd_conta) throws SQLException, ClassNotFoundException {
		List<Operacao> operacoes = new ArrayList<Operacao>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conexao = DbManager.obterInstancia().obterConexao();
			System.out.println("ID: " + cd_conta);
			stmt = conexao.prepareStatement(
					"SELECT * FROM T_OPERACAO WHERE cd_conta = ?");
			stmt.setInt(1, cd_conta);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("cd_operacao");
				double valor = rs.getDouble("vl_operacao");
				TipoOperacaoEnum tipoOperacao = TipoOperacao.stringToEnum(rs.getString("tx_tipo_operacao"));
				java.sql.Date date = rs.getDate("dt_data");
				String descricao = rs.getString("ds_descricao");

				Operacao operacao = new Operacao(valor, tipoOperacao, descricao, date, id);

				operacoes.add(operacao);
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

		return operacoes;
	}

	@Override
	public void createOperacao(Operacao operacao, int idConta) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		String tipoOperacao = TipoOperacao.enumToString(operacao.getTipoOperacao());

		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement(
					"INSERT INTO T_OPERACAO (cd_conta, vl_operacao, tx_tipo_operacao, dt_data, ds_descricao) VALUES ( ?, ?, ?, ?, ?)");
			stmt.setInt(1, idConta);
			stmt.setDouble(2, operacao.getValor());
			stmt.setString(3, tipoOperacao);
			stmt.setDate(4, new java.sql.Date(operacao.getDate().getTime()));
			stmt.setString(5, operacao.getDescricao());
			stmt.executeUpdate();

			System.out.println(operacao.toString() + " criada");

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
	public void deleteAllOperacoes(int idConta) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;

		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement(
					"DELETE FROM T_OPERACAO WHERE cd_conta = ?");
			stmt.setInt(1, idConta);
			stmt.executeUpdate();
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

}
