package model;

import java.sql.Connection; // JDBC
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** Módulo de Conexão **/
	// Parâmetros de Conexão
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC"; // Informando o IP do Servidor, Nome do Banco de Dados e o Fuso Horário de referência universal.
	private String user = "root"; // Usuário do Banco de Dados
	private String password = "Admin@12345"; // Senha do Banco de Dados
	// Método de Conexão
	private Connection conectar() { 
		// Criada a função conectar, da classe Connection, que faz parte do JDBC. 
		Connection con = null; 
		// Criado o objeto de nome "con", com o valor nulo. Será utilizado para estabelecer uma sessão com o banco de dados. 
		
		// É necessário utilizar o try catch para realizar os tratamentos de exceção, caso ocorra algum erro:
		try {
			new com.mysql.cj.jdbc.Driver();
			con = DriverManager.getConnection(url, user, password); // O objeto con é utilizado para estabelecer uma conexão com o banco de dados. A classe DriverManager irá gerenciar o driver. O método "getConnection() irá obter os parâmetros de conexão. URL, USER e PASSWORD são variáveis definidas anteriormente.
			return con; // Retornando se a conexão foi bem sucedida.
		} catch (Exception e) {
			System.out.println(e); // Para saber qual exceção ocorreu. 
			return null;
		}
	}
	// Teste de Conexão
	public void testeConexao() {
		try {
			// Usando como modelo a classe Connection, foi criado o objeto con, que invoca/executa o método conectar.
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// Caso ocorra alguma exceção, informar qual foi.
			System.out.println(e);
		}
	}
	
	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values(?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos(){ // O ArrayList é utilizado para criar um vetor dinâmico.
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read); // Query para o Java executar o comando no Servidor de Banco de Dados MySQL.
			ResultSet rs = pst.executeQuery(); // Utilizado para armazenar o retorno do Banco de Dados temporariamente em um objeto.
			// O laço abaixo será executado enquanto houver contatos.
			while(rs.next()) {
				// Variáveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1); // Essa variável receberá o primeiro campo do Banco de Dados
				String nome = rs.getString(2); // Essa variável receberá o segundo campo do Banco de Dados
				String fone = rs.getString(3); // Essa variável receberá o terceiro campo do Banco de Dados
				String email = rs.getString(4); // Essa variável receberá o quarto campo do Banco de Dados
				// Populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}