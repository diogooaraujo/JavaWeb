package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO; // Importada a classe DAO
/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/main"})
// Adicionado o parâmetro ("urlPatterns = { ... "/main"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    DAO dao = new DAO(); // Criando o objeto DAO.
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Teste de Conexão
		dao.testeConexao(); // O método para testeConexao() foi utilizado com o objeto "dao", ele irá imprimir a String de conexão se tudo estiver OK. Se ocorrer algum problema, mostrará qual erro ocorreu.
	}

}
