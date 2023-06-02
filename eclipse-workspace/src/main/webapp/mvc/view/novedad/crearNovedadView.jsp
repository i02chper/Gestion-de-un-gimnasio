<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="UTF-8">
		<title>Crear clase</title>
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
				<a href="/perfil">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
			
		//Comprobamos que el usuario es ADMINISTRADOR
		if(user.getTipo().equals("admin")){%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/nuevoEspectaculo">
					<div class="btn">GESTIONAR NOVEDADES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div>
				
			<div class="novedad">
			  <form method="post" action="addNovedad" id="clase">
			  	<div style="display:block">
			  	
			  		<%// Elementos comunes a todos los espectáculos%>
			  		<div style="display: block">
					  	<div id="labels">
					  		<label for="titulo">T&Iacute;TULO</label>
					  		<label for="descripcion" style="height: 215px;">CUERPO</label>
					  	</div>
						
						<div>						
							<input name="titulo" type="text" required oninvalid="this.setCustomValidity('Introduce el t&iacute;tulo de la clase')" 
							oninput="this.setCustomValidity('')"></input>
							
							<textarea form="clase" name="descripcion" required oninvalid="this.setCustomValidity('Introduce una descripci&oacute;n para la clase')" 
							oninput="this.setCustomValidity('')" ></textarea>
						</div>
					</div>
				</div> 
			</form>
			<div class="control">
				<button type="submit" form="clase">A&ntilde;adir novedad</input>
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