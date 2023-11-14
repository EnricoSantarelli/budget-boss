package servlet;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAOImpl daoUsuario;
	private ContaDAOImpl daoConta;
	private OperacaoDAOImpl daoOperacao;
  
	public DashboardServlet() {
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
			double receita = 0;
			double despesa = 0;
			for (Operacao operacao : operacoes) {
				if(operacao.getTipoOperacao() == TipoOperacaoEnum.DEPOSIT) {
					receita += operacao.getValor();
				} else {
					despesa += operacao.getValor();
				}
			}
			
			usuario.getConta().setSaldo(receita - despesa);
			usuario.getConta().setDespesa(despesa);
			usuario.getConta().setReceita(receita);
			
			request.setAttribute("usuario", usuario);
			
			List<Usuario> usuarios = daoUsuario.getAllUsuariosFilhos(usuario.getId());
			for(Usuario usuarioFilho : usuarios) {
				Conta contaFilho = daoConta.getConta(usuarioFilho.getId());
				usuarioFilho.setConta(contaFilho);
				
				double receitaFilho = 0;
				double despesaFilho = 0;
				List<Operacao> operacoesFilho = daoOperacao.getAllOperacoes(usuarioFilho.getId());
				
				for (Operacao operacao : operacoesFilho) {
					if(operacao.getTipoOperacao() == TipoOperacaoEnum.DEPOSIT) {
						receitaFilho += operacao.getValor();
					} else {
						despesaFilho += operacao.getValor();
					}
				}
				
				System.out.println(receitaFilho);
				
				usuarioFilho.getConta().setSaldo(receitaFilho - despesaFilho);
				usuarioFilho.getConta().setDespesa(despesaFilho);
				usuarioFilho.getConta().setReceita(receitaFilho);
				
			}
			
			request.setAttribute("usuariosFilhos", usuarios);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String emailPai = (String) session.getAttribute("user");
		
		Usuario usuario = new Usuario(email, senha);
		
		try {
			if (daoUsuario.validarUsuario(usuario)) {
				Usuario usuarioPai = daoUsuario.getUsuario(emailPai);
				Usuario usuarioFilho = daoUsuario.getUsuario(email);
				if(usuarioPai.getId() != usuarioFilho.getId()){
					daoUsuario.adicionarUsuarioFilho(usuarioPai.getId(), usuarioFilho.getId());
				} else {
					request.setAttribute("erro", "Usuário e/ou senha inválidos");
				}
				
			} else {
				System.out.println("erro");
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", e.getMessage());
		}
		doGet(request, response);
		
	}

}
