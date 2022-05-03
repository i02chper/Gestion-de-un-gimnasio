<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar datos</title>
		<link rel="stylesheet" href="/practica3/css/fuentes.css">
		<link rel="stylesheet" href="/practica3/css/general.css">
		<script type="text/javascript" src="js/funciones.js"></script>
	</head>
	
	<body onload="set_size()">
	<%! String next; %> 
	<%
	//Si NO estamos logueados redirigimos al index
	if(customerBean == null || customerBean.getNombre().equals("")) {
		next = "/practica3";
		response.sendRedirect(next);
	}
	else{
		
		//Distinguimos entre usuario administrador y espectador para el header
		if(customerBean.getTipo().equals("administrador")) {%>
		
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
		<div class="modificar_perfil">
			<form method="post" action="/practica3/modificando" id="modificar_form" autocomplete="off">
				<div>
					<label for="correo">NUEVO CORREO</label>
					<label for="pass">NUEVA CONTRASE&Ntilde;A</label>
					<label for="nombre">NUEVO NOMBRE</label>
					<label for="apellidos">NUEVOS APELLIDOS</label>
				</div>
				<div>
					<input name="correo" type="text"></input>
					<input name="pass" type="text"></input>
					<input name="nombre" type="text"></input>
					<input name="apellidos" type="text"></input>
				</div>
			</form>
			<span class="control">
				<button type="submit" form="modificar_form">Guardar cambios</button>
				<a href="/practica3">
					<button type="button">Volver</button>
				</a>
			</span>
		</div><%
	}%>	
	</body>
</html>