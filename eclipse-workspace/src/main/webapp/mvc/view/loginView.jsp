<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Iniciar Sesi&oacute;n</title>
	<link rel="stylesheet" href="/css/fuentes.css">
	<link rel="stylesheet" href="/css/general.css">
	<script type="text/javascript" src="/js/funciones.js"></script>
  </head>
  <body>
    <%! String next; %> 
    <div class="header">
		<a href="/">
			<div class="home"></div>
		</a>
		<a href="/signup">
			<div class="btn">
				REGISTRARSE
			</div>
		</a>
	</div>
	<%
	
	
	//Si estamos logueados redirigimos al index
	if(user != null && !user.getNombre().equals("")) {
		next = "/";
		response.sendRedirect(next);
	}
	else{%>
		<div class="login">
			<form method="post" action="/loging" id="login_form">
				<div>
					<label for="correo" class="label">CORREO </label>
					<label for="pass" class="label">CONTRASE&Ntilde;A </label>
				</div>
				<div>
					<input name="correo" type="text"></input>
					<input name="pass" type="password"></input>
				</div>
			</form>
			<span class="control">
				<button type="submit" form="login_form" >Iniciar sesi&oacute;n</button>
				<a href="/">
					<button type="button">Volver</button>
				</a>
			</span>
		</div><%
	}%>		
  </body>
</html>