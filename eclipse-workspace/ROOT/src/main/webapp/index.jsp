<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter,java.util.ArrayList,business.user.UserInfo,data.dao.UserDAO" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Always Gym</title>
	<link rel="stylesheet" href="css/fuentes.css">
	<link rel="stylesheet" href="css/general.css">
	<script type="text/javascript" src="js/funciones.js"></script>
  </head>
  <body onload="set_size()">
    <%!String mensaje, tipo, hoy;
       UserDAO dao;
    %>
	<%
	//Si NO estamos logueados
	if(user == null || user.getNombre().equals("")) { %>
	<div class="header">
		<a href="/">
			<div class="home"></div>
		</a>	
		<a href="/login">
			<div class="btn">
				INICIAR SESI&Oacute;N
			</div>
		</a>
	
		<a href="/signup">
			<div class="btn">
				REGISTRARSE
			</div>
		</a>
	</div>
	<%}
	
	//En caso contrario
	else {%>
		<h1>¡Bienvenido <jsp:getProperty name="user" property="nombre"/>!</h1>
		
	<%	//Distinguimos entre administrador y espectador
		if((tipo = user.getTipo()).equals("administrador")){%>
			<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/getSesiones">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/nuevoEspectaculo">
					<div class="btn">GESTIONAR NOVEDADES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
				<a href="/modificar">
					<div class="btn">MODIFICAR PERFIL</div>
				</a>
			</div>
	  <%}
		//INSTRUCTOR
		else {%>
			<div class="header">
				<a href="/practica3">
					<div class="home"></div>
				</a>
				<a href="/logout">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/modificar">
					<div class="btn">GESTIONAR RUTINAS</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
				<a href="/modificar">
					<div class="btn">MODIFICAR PERFIL</div>
				</a>
			</div><%
		}
	}%>
  </body>
</html>
