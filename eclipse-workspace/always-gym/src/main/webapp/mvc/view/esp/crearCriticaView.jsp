<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Crear cr&iacute;tica</title>
	<link rel="stylesheet" href="css/fuentes.css">
	<link rel="stylesheet" href="css/general.css">
	<script type="text/javascript" src="/practica3/js/funciones.js"></script>
  </head>
  <body onload="set_size()">
	<%!String id_espectaculo; %> 
	<% id_espectaculo = request.getParameter("espectaculo");
		
		//Comprobamos que estamos logueados
		if(customerBean != null && !customerBean.getNombre().equals("")) {
			
			//Comprobamos que el usuario es de tipo espectador
			if(customerBean.getTipo().equals("espectador")) {%>
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
			</div>
			 
  			<div class="nueva_critica">
				<form method="post" action="/practica3/crearCritica" id="critica_form">
					<input type="hidden" name="id" value="<%=id_espectaculo%>">
					<div>
						<label for="titulo">T&Iacute;TULO</label>
						<label for="resenia" style="height: 215px;">RESE&Ntilde;A</label>
						<label for="puntuacion">PUNTUACI&Oacute;N</label>
					</div>
					
					<div>
						<input type="text" name="titulo" required></input>
						<textarea name="resenia" required></textarea>
						<span style="padding-top: 5px;">
							<input name="puntuacion" type="radio" value="1">1</input>
							<input name="puntuacion" type="radio" value="2">2</input>
							<input name="puntuacion" type="radio" value="3">3</input>
							<input name="puntuacion" type="radio" value="4">4</input>
							<input name="puntuacion" type="radio" value="5" checked>5</input>
							<input name="puntuacion" type="radio" value="6">6</input>
							<input name="puntuacion" type="radio" value="7">7</input>
							<input name="puntuacion" type="radio" value="8">8</input>
							<input name="puntuacion" type="radio" value="9">9</input>
							<input name="puntuacion" type="radio" value="10">10</input>
						</span>
					</div>
				</form>
				<span class="control"><button type="submit" form="critica_form">A&ntilde;adir cr&iacute;tica</button></span>
			</div><%
			}
			else{
				session.setAttribute("error", "FUNCI&Oacute;N RESERVADA PARA USUARIOS ESPECTADORES");
				response.sendRedirect("/practica3/error");
			}
		}
		else{
			response.sendRedirect("/practica3/login");
		}%>
  </body>
</html>