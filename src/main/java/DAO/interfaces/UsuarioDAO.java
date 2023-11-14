package DAO.interfaces;


import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
	List<Usuario> getAllUsuarios() throws SQLException, ClassNotFoundException;
	
	List<Usuario> getAllUsuariosFilhos(int id) throws SQLException, ClassNotFoundException;
	
	void createUsuario(Usuario usuario, Integer usuarioPaiId) throws SQLException, ClassNotFoundException;
	
	Usuario getUsuario(String email) throws SQLException, ClassNotFoundException;
	
	boolean validarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException;
	
	void adicionarUsuarioFilho(int idPai, int idFilho) throws SQLException, ClassNotFoundException;
}
