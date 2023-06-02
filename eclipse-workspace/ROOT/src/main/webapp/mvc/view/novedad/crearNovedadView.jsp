<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="UTF-8">
		<title>Crear novedad</title>
		<link rel="stylesheet" href="/css/fuentes.css">
		<link rel="stylesheet" href="/css/general.css">
        <script type="text/javascript" src="/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
		<%!String redireccionar;%>
		<%
		// Comprobamos que estamos logueados
		if(user != null && !user.getNombre().equals("")) {
		%>	<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/getInfo">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
			
		//Comprobamos que el usuario es ADMINISTRADOR
		if(user.getTipo().equals("admin")){%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div>
				
			<div class="novedad_form" style="top: 150px">
			  <form method="post" action="addNovedad" id="novedad">
		  		<div style="display: flex">
				  	<div id="labels">
				  		<label for="titulo">T&Iacute;TULO</label>
				  		<label for="cuerpo" style="height: 215px;">CUERPO</label>
				  	</div>
					
					<div style="width: 85%">						
						<input name="titulo" type="text" required oninvalid="this.setCustomValidity('Introduce el t&iacute;tulo')" 
						oninput="this.setCustomValidity('')"></input>
						
						<textarea form="novedad" name="cuerpo" required oninvalid="this.setCustomValidity('Introduce el cuerpo de la novedad')" 
						oninput="this.setCustomValidity('')" ></textarea>
					</div>
				</div>
			</form>
			<div class="control">
				<button type="submit" form="novedad">A&ntilde;adir novedad</input>
				<a href="/">
					<button type="button">Volver</button>
				</a>
			</div>
			
		  </div>
		  <%}
			else{
				session.setAttribute("error", "Acceso reservado a administradores");
				redireccionar = "/error";
				response.sendRedirect(redireccionar);
			}
		}
		//Si no estamos logueados
		else{
			redireccionar = "/login";
			response.sendRedirect(redireccionar);
		}%>
	</body>
</html>