package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import DAO.UsuarioDAOImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAOImpl dao;

  
	public LoginServlet() {
        dao = UsuarioDAOImpl.obterInstancia();
      
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(email, senha);
		
		
		try {
			if (dao.validarUsuario(usuario)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", email);
				response.sendRedirect("dashboard");
			}	else {
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}
}