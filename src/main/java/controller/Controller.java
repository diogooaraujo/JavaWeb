package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
// As requisições são recebidas por meio de urls configuradas nas linhas 11. São caminhos definidos em formulários, links, botões etc.
// Adicionado o parâmetro ("urlPatterns = { ... "/main"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
	
	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp"); -> Linha removida para modificar o método de listar contatos.
		
		// Criando um objeto vetor que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// O resultado será armazenado no objeto lista, que será utilizado para obter os dados do vetor dinâmico.
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response); // Encaminha o objeto lista ao agenda.jsp
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		response.sendRedirect("main");
	}
	
	// Editar Contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do ID do Contato que será Editado
		String idcon = request.getParameter("idcon");
		// Setar a variável JavaBeans
		contato.setIdcon(idcon);
		// Executar o método selecionar contato
		dao.selecionarContato(contato);
		// Setar os atributos do formulário com o conteúdo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
}
