package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ContaDAOImpl;
import DAO.UsuarioDAOImpl;
import bo.EmailBO;
import exception.EmailException;
import model.Conta;
import model.Operacao;
import model.Usuario;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAOImpl daoUsuario;
	private ContaDAOImpl daoConta;
	private EmailBO bo;
  
	public RegisterServlet() {
        daoUsuario = UsuarioDAOImpl.obterInstancia();
        daoConta = ContaDAOImpl.getInstacia();
        bo = new EmailBO();
        
    }

       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Conta conta = new Conta(0, new ArrayList<Operacao>(), 0);
		
		Usuario usuario = new Usuario(nome, email, senha, 0, new ArrayList<Usuario>(), conta);
		try {
			daoUsuario.createUsuario(usuario, null);
			Usuario usuarioCriado = daoUsuario.getUsuario(email);
			System.out.println(usuarioCriado.getId());
			daoConta.createConta(conta, usuarioCriado.getId());
			
			String mensagem = "Seu usuario foi registrado";
			request.setAttribute("mensagem", mensagem);
			
				try {
					bo.enviarEmail(email, "Usuario registrado", mensagem);
				} catch (EmailException e) {
					e.printStackTrace();
				}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("erro");
			request.setAttribute("erro", e.getMessage());
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
