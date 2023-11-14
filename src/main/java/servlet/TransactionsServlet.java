package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ContaDAOImpl;
import DAO.OperacaoDAOImpl;
import DAO.UsuarioDAOImpl;
import enums.TipoOperacao.TipoOperacaoEnum;
import model.Conta;
import model.Operacao;
import model.Usuario;

@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAOImpl daoUsuario;
	private ContaDAOImpl daoConta;
	private OperacaoDAOImpl daoOperacao;
  
	public TransactionsServlet() {
		daoUsuario = UsuarioDAOImpl.obterInstancia();
		daoConta = ContaDAOImpl.getInstacia();
        daoOperacao = OperacaoDAOImpl.getInstancia();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user");
		
		try {
			Usuario usuario = daoUsuario.getUsuario(email);
			Conta conta = daoConta.getConta(usuario.getId());
			usuario.setConta(conta);
			
			List<Operacao> operacoes = daoOperacao.getAllOperacoes(usuario.getId());
			
			List<Operacao> receitas = new ArrayList<Operacao>();
			List<Operacao> despesas = new ArrayList<Operacao>();
			double receita = 0;
			double despesa = 0;
			for (Operacao operacao : operacoes) {
				if(operacao.getTipoOperacao() == TipoOperacaoEnum.DEPOSIT) {
					receita += operacao.getValor();
					receitas.add(operacao);
				} else {
					despesa += operacao.getValor();
					despesas.add(operacao);
				}
			}
			
			usuario.getConta().setDespesa(despesa);
			usuario.getConta().setReceita(receita);
			
			request.setAttribute("usuario", usuario);
			request.setAttribute("receitas", receitas);
			request.setAttribute("despesas", despesas);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("transactions.jsp").forward(request, response);
	}

}
