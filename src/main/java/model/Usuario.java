package model;


import java.util.List;

import utils.CriptografiaUtils;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private int id;
	private Conta conta;
	private List<Usuario> usuariosFilhos; 
	
	
	public Usuario() {};
	
	public Usuario(String nome, String email, String senha, int id, List<Usuario> usuariosFilhos, Conta conta){
		this.nome = nome;
		this.email = email; 
		setSenha(senha);
		this.id = id;
		if(usuariosFilhos != null) {
			this.usuariosFilhos = usuariosFilhos;
		}
		if(conta != null) {
			this.conta = conta;
		}
	}	
	
	public Usuario(String email, String senha) {
		this.email = email; 
		setSenha(senha);
	}
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", senha=" + senha + ", id=" + id +
				  "conta=" + conta + ", usuariosFilhos=" + usuariosFilhos + "]";
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
			this.id = id;
	}
	
	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public List<Usuario> getUsuariosFilhos() {
		return usuariosFilhos;
	}

	public void setUsuariosFilhos(List<Usuario> usuariosFilhos) {
		this.usuariosFilhos = usuariosFilhos;
	}
	
	public void addUsuariosFilhos(Usuario usuario) {
		this.usuariosFilhos.add(usuario);
	}

	
	
	
	
}
