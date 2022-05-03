<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,business.critica.*" %>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
  <html>
	<head>
	  <meta charset="UTF-8">
	  <title>Cr&iacute;ticas</title>
	  <link rel="stylesheet" href="css/fuentes.css">
	  <link rel="stylesheet" href="css/general.css">
	  <script type="text/javascript" src="/practica3/js/funciones.js"></script>
	</head>	
		<%!ArrayList<CriticaDTO> criticas;
		   Float vals;
		   String eliminado, valorado;
		   Integer i;
		%>
		<%
		//Obtenemos las críticas del espectáculo seleccionado a través de un atributo de sesión modificado por el servlet
		criticas = (ArrayList<CriticaDTO>) session.getAttribute("criticas");%>
	
	<body onload="set_size()"><%
		
		//Comprobamos que estamos logueados
		if(customerBean != null && !customerBean.getNombre().equals("")) {
			
			//Comprobamos que el usuario es de tipo espectador
			if(customerBean.getTipo().equals("espectador")) {
			%>
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
			
			<span class="criticas_container"><%
				i = 0;
				
				//Mostramos todas las críticas del espectáculo seleccionado
				for(CriticaDTO critica: criticas){%>
				
					<div class="critica" style="text-align: center;">
					
						<h5 style="text-align: left;">
						  Espect&aacute;culo: <%= critica.get_espectaculo_titulo()%><br>
						  Autor: <%= critica.get_autor()%>
						  
						  <%if(critica.get_autor().equals(customerBean.getUsuario())) {%>
						  		(T&uacute;)<%
						  }%>
						</h5>
						<div class="focus">
							<h3 class="titulo"><%= critica.get_titulo().toUpperCase() %></h3>
							<%= critica.get_resenia()%><br>
							<h5>
								Puntuaci&oacute;n: <%= critica.get_puntuacion()%>/10<br><%
								vals = critica.get_valoracion();
								
								// Si la crítica tiene valoraciones registradas
								if(vals != -1) {
									
									// Si la media de la valoración es un entero se mostrará tal cual pero si tiene decimales
									// mostrará solo un decimal y redondeando
									if(vals % 1 == 0){%>
										Valoraciones: <%= vals.intValue() %>/5<%
									}
									else {
										vals = vals.floatValue();
										vals = Math.round(vals * 10f) / 10f; %> 
										Valoraciones: <%= vals%>/5<%
									}
								} 
								else {%>
									Valoraci&oacute;n: NO HAY VALORACIONES<%
								}
								
								// Si la crítica pertenece al usuario logueado le daremos la opción de eliminarla
								if(critica.get_autor().equals(customerBean.getUsuario())) {%>
								
									<form method="post" action="/practica3/eliminar" id="eliminar<%=i%>">
										<input type="hidden" name="id" value="<%=critica.get_id()%>"></input>
									</form>
									<span class="control">
										<button type="submit" form="eliminar<%=i%>" onclick="return confirm('¿Seguro que deseas eliminar esta cr&iacute;tica?');">
											Eliminar cr&iacute;tica
										</button>
									</span><%
								}
								// En caso contrario se le dará la opción de valorarla
								else{%>
									<form method="post" action="/practica3/valorar" id="valorar<%=critica.get_id()%>" style="font-size: 20px;">
										<input type="hidden" name="id" value="<%=critica.get_id()%>"></input>
									</form>
									<span class="control">
										<button id="val<%=critica.get_id()%>" type="button" onclick="valorar(<%=critica.get_id()%>)">
											Valorar cr&iacute;tica
										</button>
									</span><%
								}
						%></h5>
					</div> <!-- RESALTO CONTENIDO CRITICA -->
				  </div><%
					i++;
				}%>
			</span><%
			}
			else {
				session.setAttribute("error", "FUNCI&Oacute;N RESERVADA PARA USUARIOS ESPECTADORES");
				response.sendRedirect("/practica3/error");
			}
		}
		else 
			response.sendRedirect("/practica3/login");%>
	</body>
</html>