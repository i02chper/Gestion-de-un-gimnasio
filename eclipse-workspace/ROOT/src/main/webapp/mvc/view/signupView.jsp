<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrarse</title>
		<link rel="stylesheet" href="/practica3/css/fuentes.css">
		<link rel="stylesheet" href="/practica3/css/general.css">
		<script type="text/javascript" src="js/funciones.js"></script>
	</head>
	
	<body>
		<%! String next; %> 
		<div class="header">
			<a href="/practica3">
				<div class="home"></div>
			</a>
			<a href="/practica3/login">
				<div class="btn">
					ACCEDER
				</div>
			</a>
		</div>
		<%
		//Si estamos logueados
		if(customerBean != null && !customerBean.getNombre().equals("")) {
			next = "/practica3";
			response.sendRedirect(next);
		}
		else{%>
			<div class="signup">
				<form method="post" action="signingup" id="form_signup">
					<div>
						<label for="usuario" class="label">NOMBRE DE USUARIO</label>
						<label for="pass" class="label">CONTRASE&Ntilde;A</label>
						<label for="nombre" class="label">NOMBRE</label>
						<label for="apellidos" class="label">APELLIDOS</label>
						<label for="correo" class="label">CORREO</label>
						<label for="tipo" class="label" style="margin-top: 20px;">TIPO DE USUARIO</label>
					</div>
					<div>
						<input name="usuario" type="text" required></input>
						<input name="pass" type="text" required></input>
						<input name="nombre" type="text" required></input>
						<input name="apellidos" type="text" required></input>
						<input name="correo" type="text" required></input>
						
						<span style="margin-top: 20px;">
							<input name="tipo" type="radio" size="50px" value="admin">Administrador</input>
							<input name="tipo" type="radio" value="esp" checked>Espectador</input>
						</span>
					</div>
				</form>
				<span class="control">
					<button type="submit" form="form_signup">Enviar</button>
					<a href="/practica3"><button type="button">Volver</button></a>
				</span>
			</div><%
		}%>	
	</body>
</html>