<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<link rel="icon" href="img/favicon.png">
<title>Agenda</title>
</head>
<body>
	<div id="container-agenda">
		<h1>Editar contato</h1>
		<form name="frmContato" action="update">
			<table>
				<tr>
					<td><input type="text" name="idcon" id="id" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="nome" value="<%out.print(request.getAttribute("nome"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="fone" value="<%out.print(request.getAttribute("fone"));%>"></td>
				</tr>
				<tr>
					<td><input type="text" name="email" value="<%out.print(request.getAttribute("email"));%>"></td>
				</tr>
			</table>
			<a href="main" class="excluir"> Voltar </a>
			<input type="button" value="Salvar" onclick="validar()">
		</form>
	</div>
	<script src="scripts/validador.js"></script>
</body>
</html>