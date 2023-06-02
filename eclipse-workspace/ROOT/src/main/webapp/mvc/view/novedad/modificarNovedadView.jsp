<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<%@ page import="business.novedad.NovedadDTO" %>
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
		<%!String redireccionar;
		   NovedadDTO novedad;
		%>
		<%
		novedad = (NovedadDTO) session.getAttribute("novedad");
		
		if(novedad == null)
			response.sendRedirect("/");
		
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
			
			  <form method="post" action="eliminarNovedad" id="eliminar">
			  	<input type="hidden" name="id_novedad" value="<%=novedad.get_id()%>"/>
			  </form>
			  
			  <form method="post" action="modNovedad" id="modificar">
			    <input type="hidden" name="id_novedad" value="<%=novedad.get_id()%>"/>
		  		<div style="display: flex">
				  	<div id="labels">
				  		<label for="titulo">T&Iacute;TULO</label>
				  		<label for="cuerpo" style="height: 215px;">CUERPO</label>
				  	</div>
					
					<div style="width: 85%">						
						<input name="titulo" type="text" required oninvalid="this.setCustomValidity('Introduce un t&iacute;tulo')" 
						oninput="this.setCustomValidity('')" value="<%=novedad.get_titulo()%>"></input>
						
						<textarea form="modificar" name="cuerpo" required oninvalid="this.setCustomValidity('Introduce el cuerpo de la novedad')" 
						oninput="this.setCustomValidity('')" ><%=novedad.get_cuerpo()%></textarea>
					</div>
				</div>
			</form>
			<div class="control">
				<button type="submit" form="modificar">Modificar novedad</input>
				<button type="submit" form="eliminar" onclick="return confirm('¿Seguro que desea eliminar esta novedad?')">Eliminar novedad</button>
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