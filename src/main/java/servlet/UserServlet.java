package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UsuarioDAOImpl;
import model.Usuario;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private UsuarioDAOImpl daoUsuario;
  
	public UserServlet() {
		daoUsuario = UsuarioDAOImpl.obterInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user");
		
		
		try {
			Usuario usuario = daoUsuario.getUsuario(email);
			
			request.setAttribute("nome", usuario.getNome());
			
			
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("user.jsp").forward(request, response);
	}


}
