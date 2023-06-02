<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar datos</title>
		<link rel="stylesheet" href="/css/fuentes.css">
		<link rel="stylesheet" href="/css/general.css">
		<script type="text/javascript" src="/js/funciones.js"></script>
	</head>
	
	<body onload="set_size()">
	<%! String next; %> 
	<%
	//Si NO estamos logueados redirigimos al index
	if(user == null || user.getNombre().equals("")) {
		next = "/";
		response.sendRedirect(next);
	}
	else {
		//ADMINISTRADOR
		%>	<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/getInfo">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
				
		if(user.getTipo().equals("admin")){%>
				<a href="/getSesiones">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div>
	  <%}
		//INSTRUCTOR
		else if(user.getTipo().equals("instr")){%>
		
				<a href="/logout">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/rutinas">
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
				<a href="/reservas">
					<div class="btn">RESERVAS</div>
				</a>
				<a href="/rutinas">
					<div class="btn">RUTINAS</div>
				</a>
				<a href="/getClases">
					<div class="btn">CLASES DISPONIBLES</div>
				</a>
			</div><%
	  	}%>
		<div class="modificar_perfil">
			<form method="post" action="/modificando" id="modificar_form" autocomplete="off">
				<div>
					<label for="nombre">NUEVO NOMBRE</label>
					<label for="apellidos">NUEVOS APELLIDOS</label>
					<label for="pass">NUEVA CONTRASE&Ntilde;A</label>
					<label for="telefono">NUEVO TEL&Eacute;FONO</label>
					<label for="lesion">NUEVA LESI&Oacute;N</label>
				</div>
				<div>
					<input name="nombre" type="text"></input>
					<input name="apellidos" type="text"></input>
					<input name="pass" type="text"></input>
					<input name="telefono" type="text"></input>
					<textarea name="lesion"></textarea>
					<%//TODO%>
				</div>
			</form>
			<span class="control">
				<button type="submit" form="modificar_form">Guardar cambios</button>
				<a href="/">
					<button type="button">Volver</button>
				</a>
			</span>
		</div><%
	}%>	
	</body>
</html>