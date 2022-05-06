<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registrarse</title>
		<link rel="stylesheet" href="/css/fuentes.css">
		<link rel="stylesheet" href="/css/general.css">
		<script type="text/javascript" src="js/funciones.js"></script>
	</head>
	
	<body>
		<%! String next; %> 
		<div class="header">
			<a href="">
				<div class="home"></div>
			</a>
			<a href="/login">
				<div class="btn">
					ACCEDER
				</div>
			</a>
		</div>
		<%
		//Si estamos logueados
		if(user != null && !user.getNombre().equals("")) {
			next = "/";
			response.sendRedirect(next);
		}
		else{%>
			<div class="signup">
				<form method="post" action="signingup" id="form_signup">				
					<div>
						<label for="correo" class="label">CORREO<sup style="color: red">*</sup></label>
						<label for="pass" class="label">CONTRASE&Ntilde;A<sup style="color: red">*</sup></label>
						<label for="nombre" class="label">NOMBRE<sup style="color: red">*</sup></label>
						<label for="apellidos" class="label">APELLIDOS<sup style="color: red">*</sup></label>
						<label for="dni" class="label">DNI/NIF<sup style="color: red">*</sup></label>
						<label for="telefono" class="label">N&Uacute;MERO DE TEL&Eacute;FONO</label>
						<label for="tipo" class="label">C&Oacute;DIGO DE USUARIO</label>
					</div>
					<div>
						<input name="correo" type="text" required></input>
						<input name="pass" type="text" required></input>
						<input name="nombre" type="text" required></input>
						<input name="apellidos" type="text" required></input>
						<input name="dni" type="text" required></input>
						<input name="telefono" type="text"></input>
						<input name="tipo" type="text"></input>
					</div>
				</form>
				<span class="control">
					<button type="submit" form="form_signup">Enviar</button>
					<a href=""><button type="button">Volver</button></a>
				</span>
			</div><%
		}%>	
	</body>
</html>