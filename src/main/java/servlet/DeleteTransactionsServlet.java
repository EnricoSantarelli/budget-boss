package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OperacaoDAOImpl;
import DAO.UsuarioDAOImpl;
import model.Usuario;

@WebServlet("/delete")
public class DeleteTransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAOImpl daoUsuario;
	private OperacaoDAOImpl daoOperacao;
  
	public DeleteTransactionsServlet() {
		daoUsuario = UsuarioDAOImpl.obterInstancia();
        daoOperacao = OperacaoDAOImpl.getInstancia();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user");
		
		try {
			Usuario usuario = daoUsuario.getUsuario(email);
			
			daoOperacao.deleteAllOperacoes(usuario.getId());	
			
			
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		response.sendRedirect("transactions");
	}

	
}
