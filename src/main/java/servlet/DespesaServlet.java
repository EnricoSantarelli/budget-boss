package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OperacaoDAOImpl;
import DAO.UsuarioDAOImpl;
import enums.TipoOperacao.TipoOperacaoEnum;
import model.Operacao;
import model.Usuario;

@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAOImpl daoUsuario;
	private OperacaoDAOImpl daoOperacao;
  
	public DespesaServlet() {
		daoUsuario = UsuarioDAOImpl.obterInstancia();
        daoOperacao = OperacaoDAOImpl.getInstancia();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user");
		
		float valor = Float.parseFloat(request.getParameter("valor"));
		String dataString = request.getParameter("data");
		 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		 Date data = null;

	        try {
	            java.util.Date dataUtil = formato.parse(dataString);
	            data = new java.sql.Date(dataUtil.getTime());

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	       
		String descricao = request.getParameter("descricao");
		
		try {
			Usuario usuario = daoUsuario.getUsuario(email);
			Operacao operacao = new Operacao(valor, TipoOperacaoEnum.WITHDRAW, descricao, data, 0);
			
			daoOperacao.createOperacao(operacao, usuario.getId());
			
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		response.sendRedirect("transactions");
	}

}
