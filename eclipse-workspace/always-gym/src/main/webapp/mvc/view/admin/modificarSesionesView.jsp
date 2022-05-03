<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import='java.util.ArrayList,business.espectaculo.*,java.time.LocalDateTime,java.time.format.DateTimeFormatter' %>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar sesiones</title>
        <link rel="stylesheet" href="/practica3/css/fuentes.css">
		<link rel="stylesheet" href="/practica3/css/general.css">
		<script type="text/javascript" src="js/funciones.js"></script>
	</head>
	
<%!String modificado;%>
<%modificado = (String)session.getAttribute("modificado");

  //Comprobamos si ha habido alguna modificación previa
  if( modificado != null && modificado.equals("true")) {%>
	<body onload="set_size(), exito()"><%
    session.setAttribute("modificado", "false");
  }
  else {%>
  	<body onload="set_size()"><%
  }
  
  //Si estamos logueados y la sesión sigue activa
  if(customerBean != null && !customerBean.getNombre().equals("")) {
  	
	//Comprobamos que el usuario que accede es administrador
  	if(customerBean.getTipo().equals("administrador")) {%>
  
	<%! ArrayList<EspectaculoPuntualDTO> puntuales;
	    ArrayList<EspectaculoTemporadaDTO> temporada;
	    ArrayList<EspectaculoMultipleDTO> multiples;
	    ArrayList<LocalDateTime> fechas;
	    String fecha_p, dia_t, inicio_t, final_t;
	    Integer i, j;
	%>
	<%
		// Obtenemos todos los espectáculos del servlet
		puntuales = (ArrayList<EspectaculoPuntualDTO>) session.getAttribute("puntuales");
		temporada = (ArrayList<EspectaculoTemporadaDTO>) session.getAttribute("temporada");
		multiples = (ArrayList<EspectaculoMultipleDTO>) session.getAttribute("multiples");
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
		<a href="/practica3/getSesiones">
			<div class="btn">GESTIONAR SESIONES</div>
		</a>
		<a href="/practica3/nuevoEspectaculo">
			<div class="btn">A&Ntilde;ADIR ESPECT&Aacute;CULO</div>
		</a>
		<a href="/practica3/infoUsuarios">
			<div class="btn">INFORMACI&Oacute;N USUARIOS</div>
		</a>
	</div>
	
	<span class="sesiones_container">
		<div class="sesiones">
			<h3 class="titulo">ESPECT&Aacute;CULOS PUNTUALES</h3><%
			
			i = 1;
			
			// Mostramos los espectáculos puntuales
			for(EspectaculoPuntualDTO espectaculo: puntuales){ 
				fecha_p = espectaculo.getFechaHora();%>
				
				<div class="espectaculo">
					<p><strong><%=espectaculo.get_titulo().toUpperCase()%></strong></p>
					<p><%=espectaculo.get_descripcion()%></p>
					<form method="post" action="/practica3/modificarSesion" id="punt<%=i%>">
						<input type="hidden" name="id" value="<%=espectaculo.get_id()%>">
						<input type="hidden" name="tipo" value="puntual">
						<label for="fecha_p">Sesi&oacute;n:</label>
						<input name="fecha_p" type="datetime-local" value="<%=fecha_p.replace(' ', 'T')%>" required>
					</form>
					<span class="control">
						<button type="submit" form="punt<%=i%>">Aplicar cambios</button>
					</span>
				</div><%
				i++;
			}%>
		</div>
		<div class="sesiones">
		<h3 class="titulo">ESPECT&Aacute;CULOS DE TEMPORADA</h3>
		<%
		i = 1;
		
		for(EspectaculoTemporadaDTO espectaculo: temporada){ 
			dia_t = espectaculo.get_dia();
			inicio_t = espectaculo.get_inicio();
			final_t = espectaculo.get_fin();%>
		
			<div class="espectaculo">
				<p><strong><%=espectaculo.get_titulo().toUpperCase() %></strong></p>
				<p><%=espectaculo.get_descripcion()%></p>
				<form method="post" action="/practica3/modificarSesion" id="temp<%=i%>">
					<input type="hidden" name="id" value="<%=espectaculo.get_id()%>">
					<input type="hidden" name="tipo" value="temporada"><br>
					
					<div>
						<label for="dia_temp">D&iacute;a de la semana: </label>
						<label for="inicio_t">Inicio:</label>
						<label for="fin_t">Fecha final:</label>
					</div>
					<div>
						<select name="dia_temp" form="temp<%=i%>">
							<option value="Lunes" <%if(dia_t.equals("Lunes")){%>selected<%}%>>Lunes</option>
							<option value="Martes" <%if(dia_t.equals("Martes")){%>selected<%}%>>Martes</option>
							<option value="Miercoles" <%if(dia_t.equals("Miercoles")){%>selected<%}%>>Mi&eacute;rcoles</option>
							<option value="Jueves" <%if(dia_t.equals("Jueves")){%>selected<%}%>>Jueves</option>
							<option value="Viernes" <%if(dia_t.equals("Viernes")){%>selected<%}%>>Viernes</option>
							<option value="Sabado" <%if(dia_t.equals("Sabado")){%>selected<%}%>>S&aacute;bado</option>
							<option value="Domingo" <%if(dia_t.equals("Domingo")){%>selected<%}%>>Domingo</option>
						</select>
						
						
						<input name="inicio_t" type="date" value="<%=inicio_t.substring(0, inicio_t.indexOf(" ")).replace(' ', 'T')%>" required>
						
						<input name="fin_t" type="date" value="<%=final_t.substring(0, final_t.indexOf(" ")).replace(' ', 'T')%>" required>
					</div>
				</form>
				<span class="control">
					<button type="submit" form="temp<%=i%>">Aplicar cambios</button>
				</span>
			</div><%
			i++;
		}%>
		</div>
		<div class="sesiones">
		<h3 class="titulo">ESPECT&Aacute;CULOS M&Uacute;LTIPLES</h3>
		<%
		i = 0;
		j = 0;
		
		for(EspectaculoMultipleDTO espectaculo: multiples){ 
			fechas = espectaculo.getFechasHoras();
		%>
		<div class="espectaculo">
			<p><strong><%=espectaculo.get_titulo().toUpperCase()%></strong></p>
			<p><%=espectaculo.get_descripcion()%></p>
			<form method="post" action="/practica3/modificarSesion" id="multi<%=j%>" style="display: inline-grid">
				<input type="hidden" name="id" value="<%=espectaculo.get_id()%>">
				<input type="hidden" name="tipo" value="multiple">
				<label for="fecha_m">Fechas:</label><br><%
				Integer n_fechas = 0;
				
				for(LocalDateTime fecha: fechas) {
					String date = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));%>
					<div id="<%=i%>" style="display: ruby">
					  	<input name="fecha_m" type="datetime-local" value="<%=date.replace(' ', 'T')%>" required>
					  	<%if(n_fechas != 0) {%>
					  		<input type="button" value="Cancelar sesi&oacute;n" onclick="cancelar(<%=i%>)"><%
					  	}%>
					</div><%
			        i++;
					n_fechas++;
			      }%>
			</form>
			<span class="control">
				<button id="multi<%=j%>_mod" type="submit" form="multi<%=j%>">Aplicar cambios</button>
				<button id="multi<%=j%>_add" onclick="add('multi<%=j%>', <%=i%>)">A&ntilde;adir fecha</button>
			</span>
		</div>
		<%
			j++;
		}
		%>
		</div>
	</span><%
  	 }
  	else{
  		session.setAttribute("error", "Funci&oacute;n solo disponible para administradores");
  		response.sendRedirect("/practica3/error");
  	}
  }
  else
	  response.sendRedirect("/practica3/login");
  %>
	</body>
</html>