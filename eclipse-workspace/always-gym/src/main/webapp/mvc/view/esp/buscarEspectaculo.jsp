<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import='java.util.ArrayList,business.espectaculo.*,java.time.LocalDateTime,java.time.format.DateTimeFormatter'%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html> 
  <head>
	<meta charset="UTF-8">
	<title>Buscar espect&aacute;culo</title>
	<link rel="stylesheet" href="css/fuentes.css">
	<link rel="stylesheet" href="css/general.css">
	<script type="text/javascript" src="/practica3/js/funciones.js"></script>
  </head>
  <body onload="set_size()">
  	<%!
  	ArrayList<EspectaculoPuntualDTO> puntuales_bus;
    ArrayList<EspectaculoTemporadaDTO> temporada_bus;
    ArrayList<EspectaculoMultipleDTO> multiples_bus;
    Boolean no_res;
  	%><%
  	// Comprobamos que estamos logueados, en caso contrario redirigimos al login
  	if(customerBean != null && !customerBean.getNombre().equals("")){
  		
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
			
	  		<div class="busqueda">
				<form method="post" action="/practica3/buscando" id="barra_busqueda" autocomplete="off">
					<div>
						<label for="titulo">T&iacute;tulo</label>
						<label for="categoria">Categor&iacute;a</label>
					</div>
					<div>
						<input type="text" name="titulo" onchange="enviar_vis()" value=""></input>
						<input type="text" name="categoria" onchange="enviar_vis()" value=""></input>
					</div>
				</form>
				<span class="control"><button type="submit" name="send" form="barra_busqueda" disabled>Buscar</button></span>
			</div><%
			
			no_res = (Boolean) session.getAttribute("sin_resultados");
			
			// En caso de que no haya resultados que coincidan con la búsqueda, se indicará por pantalla
			if(no_res != null && no_res == true) {%>
				<div class="sin_resultados">No existen resultados para esta b&uacute;squeda</div><%
			}
		
	  		// Obtenemos los espectáculos coincidentes con los criterios buscados desde el servlet
		  	puntuales_bus = (ArrayList<EspectaculoPuntualDTO>)session.getAttribute("puntuales_bus");
		  	temporada_bus = (ArrayList<EspectaculoTemporadaDTO>)session.getAttribute("temporada_bus");
		  	multiples_bus = (ArrayList<EspectaculoMultipleDTO>)session.getAttribute("multiples_bus");
		  	
		  	%><div class="resultados_container" style="top: 500px"><%
		  	
		  	// Si existen espectáculos puntuales coincidentes los mostramos
			if(puntuales_bus != null && !puntuales_bus.isEmpty()){
				
				for(EspectaculoPuntualDTO espectaculo: puntuales_bus) {%>
					<div class="resultado">
						<h3 class="titulo"><%=espectaculo.get_titulo().toUpperCase()%></h3>
						<p><%=espectaculo.get_descripcion() %></p>
						<label>Categor&iacute;a: </label>
						<%=espectaculo.get_categoria() %><br>
						<label>Localidades disponibles: </label>
						<%=espectaculo.get_localidades() - espectaculo.get_vendidas()%><br>
						<label>Fecha: </label>
						<%=espectaculo.getFechaHora()%><br>
						
					</div><%
				}
			}
			
		 // Si existen espectáculos de temporada coincidentes los mostramos
			if(temporada_bus != null && !temporada_bus.isEmpty()){
				
				for(EspectaculoTemporadaDTO espectaculo: temporada_bus) {%>
					<div class="resultado">
						<h3 class="titulo"><%=espectaculo.get_titulo().toUpperCase()%></h3>
						<p><%=espectaculo.get_descripcion() %></p>
						<label>Categor&iacute;a: </label>
						<%=espectaculo.get_categoria() %><br>
						<label>Localidades disponibles: </label>
						<%=espectaculo.get_localidades() - espectaculo.get_vendidas()%><br>
						<label>D&iacute;a de la semana: </label>
						<%=espectaculo.get_dia()%><br>
						<label>Fecha de inicio: </label>
						<%=espectaculo.get_inicio().substring(0, espectaculo.get_inicio().indexOf(' '))%><br>
						<label>&Uacute;ltima fecha: </label>
						<%= espectaculo.get_fin().substring(0, espectaculo.get_fin().indexOf(' ')) %><br>
					</div><%
				}
			}
			
			// Si existen espectáculos múltiples coincidentes los mostramos
			if(multiples_bus != null && !multiples_bus.isEmpty()){
				
				for(EspectaculoMultipleDTO espectaculo: multiples_bus) {%>
					<div class="resultado">
						<h3 class="titulo"><%=espectaculo.get_titulo().toUpperCase()%></h3>
						<p><%=espectaculo.get_descripcion() %></p>
						<label>Categor&iacute;a: </label>
						<%=espectaculo.get_categoria() %><br>
						<label>Localidades disponibles: </label>
						<%=espectaculo.get_localidades() - espectaculo.get_vendidas()%><br>
						<p>
							<label>Fechas: </label><br><%
							
							for(LocalDateTime fecha: espectaculo.getFechasHoras()){%>
								&nbsp;&nbsp;<%= fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %><br><%
							}%>
						</p>
					</div><%
				}
			}
			%>
			</div> <!-- RESULTADOS CONTAINER -->
			<%
			// Invocamos al colector de basura para eliminar los residuos para futuras búsquedas
			session.setAttribute("puntuales_bus", null);
			session.setAttribute("temporada_bus", null);
			session.setAttribute("multiples_bus", null);
			session.setAttribute("sin_resultados", false);
	  	}
	  	else{
	  		session.setAttribute("error", "FUNCIONALIDAD EXCLUSIVA PARA USUARIOS ESPECTADORES");
	  		response.sendRedirect("/practica3/error");
	  	}
  	}
  	else
  		response.sendRedirect("/practica3");
		%>
	</body>
</html>