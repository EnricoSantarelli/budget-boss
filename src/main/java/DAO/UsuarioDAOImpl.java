package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.interfaces.UsuarioDAO;
import model.Usuario;
import JDBC.DbManager;

public class UsuarioDAOImpl implements UsuarioDAO {
		
	private static UsuarioDAOImpl instancia;
	private Connection conexao;
	
	
	private UsuarioDAOImpl() {};
		
	public static UsuarioDAOImpl obterInstancia() {
			if(instancia == null) {
				instancia = new UsuarioDAOImpl();
			}
			return instancia;
	}

	
	@Override
	public List<Usuario> getAllUsuarios() throws SQLException, ClassNotFoundException {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO");
			rs = stmt.executeQuery();
		
			
			while(rs.next()) {
				int id = rs.getInt("cd_usuario");		
				String nome = rs.getString("nm_nome");
				String senha = rs.getString("tx_senha");
				String email = rs.getString("tx_email");
				
				Usuario usuario = new Usuario(nome,email,senha, id, new ArrayList<Usuario>(), null);
				
				usuarios.add(usuario);
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
		
		return usuarios;
	}

	@Override
	public List<Usuario> getAllUsuariosFilhos(int id) throws SQLException, ClassNotFoundException{
		List<Usuario> usuariosFilhos = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE cd_usuario_pai = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int idUsuario = rs.getInt("cd_usuario");
				String nome = rs.getString("nm_nome");
				String senha = rs.getString("tx_senha");
				String email = rs.getString("tx_email");
				
				Usuario usuario = new Usuario(nome,email,senha, idUsuario, new ArrayList<Usuario>(), null);
				
				usuariosFilhos.add(usuario);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("O Driver JDBC não foi encontrado");
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuariosFilhos;
	}

	@Override
	public void createUsuario(Usuario usuario, Integer usuarioPaiId) throws SQLException, ClassNotFoundException{
		PreparedStatement stmt = null;
		
		try {
			 conexao = DbManager.obterInstancia().obterConexao();
				stmt = conexao.prepareStatement("INSERT INTO T_USUARIO ( nm_nome, tx_senha, tx_email) VALUES ( ?, ?, ?)");
				if(usuarioPaiId != null) {
					stmt = conexao.prepareStatement("INSERT INTO T_USUARIO ( cd_usuario_pai, nm_nome, tx_senha, tx_email) VALUES (?, ?, ?, ?)");
					stmt.setInt(1, usuarioPaiId);
					stmt.setString(2, usuario.getNome());
					stmt.setString(3, usuario.getSenha());
					stmt.setString(4, usuario.getEmail());
				} else {
					stmt.setString(1, usuario.getNome());
					stmt.setString(2, usuario.getSenha());
					stmt.setString(3, usuario.getEmail());
				}
					
			stmt.executeUpdate();
			System.out.println(usuario.toString() + " criado");
		} catch (SQLException e) {
			throw(e);
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
	public Usuario getUsuario(String email) throws SQLException, ClassNotFoundException{
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE tx_email = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
		
			
			while(rs.next()) {		
				String nome = rs.getString("nm_nome");
				String senha = rs.getString("tx_senha");
				String emailUsuario = rs.getString("tx_email");
				int id = rs.getInt("cd_usuario");
				
				usuario = new Usuario(nome,emailUsuario,senha, id, new ArrayList<Usuario>(), null);
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
		
		return usuario;
	}

	@Override
	public boolean validarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE tx_email = ? AND tx_senha = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				throw(e);
			}
		}
		return false;
	}

	@Override
	public void adicionarUsuarioFilho(int idPai, int idFilho) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = null;
		
		try {
			 conexao = DbManager.obterInstancia().obterConexao();
			stmt = conexao.prepareStatement("UPDATE T_USUARIO SET cd_usuario_pai = ? WHERE cd_usuario = ?");
			stmt.setInt(1, idPai);
			stmt.setInt(2, idFilho);
	
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw(e);
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
