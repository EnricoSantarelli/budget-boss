package DAO.interfaces;

import java.sql.SQLException;

import model.Conta;


public interface ContaDAO {
	void createConta(Conta conta, int idUsuario) throws SQLException, ClassNotFoundException;;
	
	Conta getConta(int id) throws SQLException, ClassNotFoundException;
}
