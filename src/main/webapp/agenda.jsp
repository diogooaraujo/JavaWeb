<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	//Recebendo o objeto lista.
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos"); 
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="img/favicon.png">
<title>Agenda de Contatos</title>
</head>
<body>
	<div class="agenda">
		<h1>Agenda de Contatos</h1>
		<a href="novo.html"> Novo Contato </a>
		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>E-mail</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%for (int i = 0; i < lista.size(); i++){%>
					<tr>
						<td><%=lista.get(i).getIdcon()%></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getFone()%></td>
						<td><%=lista.get(i).getEmail()%></td>
						<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>">Editar</a></td>
					</tr>
				<%} %>
				<!-- A "?" é utilizada para encaminhar um parâmetro ao documento -->
			</tbody>
		</table>
	</div>
</body>
</html>