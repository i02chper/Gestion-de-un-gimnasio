<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ERROR</title>
		<link rel="stylesheet" href="css/fuentes.css">
		<link rel="stylesheet" href="css/general.css">
		<script type="text/javascript" src="/practica3/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
		<%!String mensaje;%>
		<%
		mensaje = (String) session.getAttribute("error");
		
		if(customerBean != null && !customerBean.getNombre().equals("")){
			
			if(customerBean.getTipo().equals("administrador")){%>
				<div class="header">
					<a href="/practica3">
						<div class="home"></div>
					</a>
					<a href="/practica3/logout" onclick="return confirm('¿Seguro que deseas cerrar sesi&oacute;n?')">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/practica3/modificar">
						<div class="btn">MODIFICAR PERFIL</div>
					</a>
					<a href="/practica3/getSesiones">
						<div class="btn">GESTIONAR SESIONES</div>
					</a>
					<a href="/practica3/nuevoEspectaculo">
						<div class="btn">A&Ntilde;ADIR ESPECT&Aacute;CULO</div>
					</a>
					<a href="/practica3/infoUsuarios">
						<div class="btn">INFORMACI&Oacute;N USUARIOS</div>
					</a>
				</div><%
			}
			else {%>
				<div class="header">
					<a href="/practica3">
						<div class="home"></div>
					</a>
					<a href="/practica3/logout" onclick="return confirm('¿Seguro que deseas cerrar sesi&oacute;n?')">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/practica3/modificar">
						<div class="btn">MODIFICAR PERFIL</div>
					</a>
					<a href="/practica3/buscar">
						<div class="btn">BUSCAR</div>
				  	</a>
				  	<a href="/practica3/espectaculos">
						<div class="btn">REDACTAR CR&Iacute;TICA</div>
				  	</a>
				  	<a href="/practica3/getCriticas"">
						<div class="btn">CONSULTAR CR&Iacute;TICAS</div>
				  	</a>
				</div><%
			}
		}
		else {%>
			<div class="header">
				<a href="/practica3">
					<div class="home"></div>
				</a>	
				<a href="/practica3/login">
					<div class="btn">
						ACCEDER
					</div>
				</a>
			
				<a href="/practica3/signup">
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
		
			<a href="/practica3">
				<button type="button">Volver al inicio</button>
			</a>
		</div>
	</body>
</html>
