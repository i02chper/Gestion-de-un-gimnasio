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
		
		if(user != null && !user.getNombre().equals("")){
			
			if(user.getTipo().equals("administrador")){%>
				<div class="header">
					<a href="">
						<div class="home"></div>
					</a>
					<a href="/logout" onclick="return confirm('¿Seguro que deseas cerrar sesi&oacute;n?')">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/modificar">
						<div class="btn">MODIFICAR PERFIL</div>
					</a>
					<a href="/getSesiones">
						<div class="btn">GESTIONAR SESIONES</div>
					</a>
					<a href="/nuevoEspectaculo">
						<div class="btn">A&Ntilde;ADIR ESPECT&Aacute;CULO</div>
					</a>
					<a href="/infoUsuarios">
						<div class="btn">INFORMACI&Oacute;N USUARIOS</div>
					</a>
				</div><%
			}
			else {%>
				<div class="header">
					<a href="/">
						<div class="home"></div>
					</a>
					<a href="/logout" onclick="return confirm('¿Seguro que deseas cerrar sesi&oacute;n?')">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/modificar">
						<div class="btn">MODIFICAR PERFIL</div>
					</a>
					<a href="/buscar">
						<div class="btn">BUSCAR</div>
				  	</a>
				  	<a href="/espectaculos">
						<div class="btn">REDACTAR CR&Iacute;TICA</div>
				  	</a>
				  	<a href="/getCriticas"">
						<div class="btn">CONSULTAR CR&Iacute;TICAS</div>
				  	</a>
				</div><%
			}
		}
		else {%>
			<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>	
				<a href="/login">
					<div class="btn">
						ACCEDER
					</div>
				</a>
			
				<a href="/signup">
					<div class="btn">
						REGISTRARSE
					</div>
				</a>
			</div><%
		}
		%>
		<div class="info">
			<h3 class="error">ERROR</h3>
			<p><%=mensaje%></p>
		
			<a href="/">
				<button type="button">Volver al inicio</button>
			</a>
		</div>
	</body>
</html>
