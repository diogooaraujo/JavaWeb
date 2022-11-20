package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.x.protobuf.MysqlxResultset.ColumnMetaDataOrBuilder;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
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
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp"); -> Linha removida para modificar o
		// método de listar contatos.

		// Criando um objeto vetor que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// O resultado será armazenado no objeto lista, que será utilizado para obter os
		// dados do vetor dinâmico.
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
	// Primeiro é necessário listar o contato.
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

	// Editar Contato
	// Segundo, partimos para a edição
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setar as Variáveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// Executar o método alterar contato
		dao.alterarContato(contato);
		// Redirecionar para o documento agenda.jsp atualizando as informações
		response.sendRedirect("main");
	}

	// Remover um Contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do ID do Contato a ser excluído (confirmador.js)
		String idcon = request.getParameter("idcon");
		// Setar a variável JavaBeans
		contato.setIdcon(idcon);
		// Executar o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		// Redirecionar para o documento agenda.jsp atualizando as informações
		response.sendRedirect("main");
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// Tipo de Conteúdo
			response.setContentType("application/pdf");
			// Nome do Documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			// Criar o Documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// Abrir o Documento -> Conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));
			// Criando uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);		
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}
