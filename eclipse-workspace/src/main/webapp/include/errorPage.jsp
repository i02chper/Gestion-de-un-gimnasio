<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ERROR</title>
		<link rel="stylesheet" href="/css/fuentes.css">
		<link rel="stylesheet" href="/css/general.css">
		<script type="text/javascript" src="/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
		<%!String mensaje;%>
		<%
		mensaje = (String) session.getAttribute("error");
		
		if(mensaje == null)
			response.sendRedirect("/");
		
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
			<a href="/getClases">
				<div class="btn">
					CLASES DISPONIBLES
				</div>
			</a>
		</div>
		<%}
		
		//En caso contrario
		else {%>
				<div class="header">
					<a href="/">
						<div class="home"></div>
					</a>
					<a href="/perfil">
						<div class="btn"><%=user.getNombre()%></div>
					</a><%
			//ADMINISTRADOR
			if(user.getTipo().equals("admin")){%>
					<a href="/getClases">
						<div class="btn">GESTIONAR CLASES</div>
					</a>
					<a href="/gestionarNovedades">
						<div class="btn">GESTIONAR NOVEDADES</div>
					</a>
					<a href="/logout">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
				</div> 
		  <%}
			//INSTRUCTOR
			else if(user.getTipo().equals("instr")){%>
			
					<a href="/getClases">
						<div class="btn">GESTIONAR CLASES</div>
					</a>
					<a href="/modificar">
						<div class="btn">GESTIONAR RUTINAS</div>
					</a>
					<a href="/logout">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
				</div><%
			}
			//SOCIO
			else{%>
					<a href="/logout">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/logout">
						<div class="btn">RESERVAS</div>
					</a>
					<a href="/modificar">
						<div class="btn">RUTINAS</div>
					</a>
					<a href="/getClases">
						<div class="btn">CLASES DISPONIBLES</div>
					</a>
				</div><%
		  	}
		}%>
		<div class="info">
			<h3 class="error">ERROR</h3>
			<p><%=mensaje%></p>
		
			<a href="/">
				<button type="button">Volver al inicio</button>
			</a>
		</div>
		
	<%session.setAttribute("error", null);%>
	</body>
</html>
