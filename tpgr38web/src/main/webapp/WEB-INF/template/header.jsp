<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logica.clases.Usuario" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>Salud.uy</title>
</head>
<body>
	<header class="d-flex align-items-center p-3 border-bottom">
			<h1 class="h3 mb-0 text-primary">Salud.uy</h1>
			<search class="mx-auto">
				<form class ="d-flex">
					<label for="caja-busqueda" class="visually-hidden">Buscar en el sitio:</label>
					<input type="search" id = "caja-busqueda" class= "form-control" name = "q" placeholder = "Consulta... Dias consulta..." required>
					<button type="submit" class="btn btn-primary ms-2">Buscar</button>
				</form>
			</search>
			<%
				Usuario usuario = (Usuario) session.getAttribute("usuario_logueado");
				if (usuario == null){
			%>
				<a href="login">Iniciar sesion / Registrarse</a>
			<%
				}else {
			%>
				<div class = d-flex "align-items-center">
					<a href="logout" class ="me-3">Cerrar sesion</a>
					<span class="me-2"><%= usuario.getNickname() %></span>
				</div>
			<%
				}
			%>
	</header>	
</body>
</html>