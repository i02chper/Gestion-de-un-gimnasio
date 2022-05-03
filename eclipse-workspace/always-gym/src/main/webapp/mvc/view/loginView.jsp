<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Login</title>
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
		<a href="/practica3/signup">
			<div class="btn">
				REGISTRARSE
			</div>
		</a>
	</div>
	<%
	
	
	//Si estamos logueados redirigimos al index
	if(customerBean != null && !customerBean.getNombre().equals("")) {
		next = "/practica3";
		response.sendRedirect(next);
	}
	else{%>
		<div class="login">
			<form method="post" action="/always-gym/loging" id="login_form">
				<div>
					<label for="usuario" class="label">USUARIO </label>
					<label for="pass" class="label">CONTRASE&Ntilde;A </label>
				</div>
				<div>
					<input name="usuario" type="text"></input>
					<input name="pass" type="password"></input>
				</div>
			</form>
			<span class="control">
				<button type="submit" form="login_form" >Iniciar sesi&oacute;n</button>
				<a href="/practica3">
					<button type="button">Volver</button>
				</a>
			</span>
		</div><%
	}%>		
  </body>
</html>