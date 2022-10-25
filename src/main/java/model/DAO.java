package model;

import java.sql.Connection; // JDBC
import java.sql.DriverManager;

public class DAO {
	/** Módulo de Conexão **/
	// Parâmetros de Conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC"; // Informando o IP do Servidor, Nome do Banco de Dados e o Fuso Horário de referência universal.
	private String user = "root"; // Usuário do Banco de Dados
	private String password = "31102013@DBOnline!"; // Senha do Banco de Dados
	// Método de Conexão
	private Connection conectar() { 
		// Criada a função conectar, da classe Connection, que faz parte do JDBC. 
		Connection con = null; 
		// Criado o objeto de nome "con", com o valor nulo. Será utilizado para estabelecer uma sessão com o banco de dados. 
		
		// É necessário utilizar o try catch para realizar os tratamentos de exceção, caso ocorra algum erro:
		try {
			Class.forName(driver); // O método forName irá ler o driver do banco de dados da variável que foi criada na linha 8, e verificará se a escrita está correta.
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
}
