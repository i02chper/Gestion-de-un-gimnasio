<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cerrar sesi&oacute;n</title>
		<link rel="stylesheet" href="css/fuentes.css">
		<link rel="stylesheet" href="css/general.css">
		<script type="text/javascript" src="/practica3/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
	<%
		//Comprobamos que estamos logueados, en caso contrario redirigiremos al login
		if(customerBean != null && !customerBean.getNombre().equals("")){
			
			//Distinguimos entre usuario administrador y espectador para el header
			if(customerBean.getTipo().equals("administrador")){%>
				<div class="header">
					<a href="/practica3">
						<div class="home"></div>
					</a>
					<a href="/practica3/logout">
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
			//ESPECTADOR
			else {%>
				<div class="header">
					<a href="/practica3">
						<div class="home"></div>
					</a>
					<a href="/practica3/logout">
						<div class="btn">CERRAR SESI&Oacute;N</div>
					</a>
					<a href="/practica3/modificar">
						<div class="btn">MODIFICAR PERFIL</div>
					</a>
					<a href="/practica3/buscar">
						<div class="btn">BUSCAR</div>
				  	</a>
				  	<a href="/practica3/espectaculos">
						<div class="btn">ESPECT&Aacute;CULOS</div>
				  	</a>
				</div><%
			}%>
			
			<div class="opciones">
			
				<div class="cerrar_sesion">
					<a href="/practica3/logingout"><button>Cerrar sesi&oacute;n</button></a>
					<p>Pulsa aqu&iacute; para cerrar la sesi&oacute;n.</p>
				</div>
				
				<div class="eliminar">
					<a href="/practica3/eliminarUsuario">
						<button onclick="return confirm('¿Seguro que deseas eliminar tu cuenta de forma PERMANENTE?')">Eliminar cuenta</button>
					</a>
					
					<p>Pulsa aqu&iacute; para eliminar tu cuenta de forma permanente.</p>
					<div class="advertencia">
						<p>¡ADVERTENCIA!</p>
						<p>TODAS TUS CR&Iacute;TICAS SER&Aacute;N ELIMINADAS</p>
					</div>
				</div>
			</div><%
		}
		else
			response.sendRedirect("/practica3/login");%>
	</body>
</html>