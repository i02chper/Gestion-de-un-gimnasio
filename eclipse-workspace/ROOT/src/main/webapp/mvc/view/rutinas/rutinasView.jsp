<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,business.novedad.NovedadDTO" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Always Gym</title>
	<link rel="stylesheet" href="/css/fuentes.css">
	<link rel="stylesheet" href="/css/general.css">
	<script type="text/javascript" src="/js/funciones.js"></script>
  </head>
  <body onload="set_size()">
    <%!String mensaje, tipo, hoy, bool_nov;
       ArrayList<NovedadDTO> novedades;
       int cont;
    %><%
	//Si NO estamos logueados
	if(user == null || user.getNombre().equals("")) { %>
	<div class="header">
		<a href="/">
			<div class="home"></div>
		</a>	
		<a href="/login">
			<div class="btn">
				INICIAR SESI&Oacute;N
			</div>
		</a>
	
		<a href="/signup">
			<div class="btn">
				REGISTRARSE
			</div>
		</a>
		<a href="/getClases">
			<div class="btn">
				CLASES DISPONIBLES
			</div>
		</a>
	</div>
	<%}
	
	//En caso contrario
	else {%>
			<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/getInfo">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
		//ADMINISTRADOR
		if((tipo = user.getTipo()).equals("admin")){%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div> 
	  <%}
		//INSTRUCTOR
		else if((tipo = user.getTipo()).equals("instr")){%>
		
				<a href="/getClases">
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
				<a href="/logout">
					<div class="btn">RESERVAS</div>
				</a>
				<a href="/rutinas">
					<div class="btn">RUTINAS</div>
				</a>
				<a href="/getClases">
					<div class="btn">CLASES DISPONIBLES</div>
				</a>
			</div><%
	  	}
	}
	
	if(user != null && user.getTipo().equals("admin")) {%>
	  	<a href="/crearNovedad">
			<div class="aniadir_clase">A&Ntilde;ADIR NOVEDAD</div>
		</a><% 
  	}%>
	
	<div class="informacion"><%
	if(user.getTipo().equals("socio")) {%>
		<div class="titulo"> BIENVENIDO: <%=user.getNombre()%></div>
			<div class="subtitulo">Nos complace volver a verte por Always Gym<br><br><br></div>
			<div class="columna"> Estas son tus rutinas de hoy: <br>
				<ul>
				  <li class="lista">Rutina número 1</li>
				  <li class="lista">Rutina número 2</li>
				  <li class="lista">Rutina número 3</li>
				</ul>
			</div>
			<div>
				<img src="/img/socio.jpg" alt="Always Gym" width="450">
			</div>
		</div><%
	}
	else if(user.getTipo().equals("instr")){%>
		<div class="titulo"> BIENVENIDO: <%=user.getNombre()%></div>
			<div class="subtitulo">Área el instructor<br><br><br></div>
			<div class="columna"> Estas son las rutinas de tus socios asignados: <br>
				<ul>
				  <li class="lista">Rutina socio 1</li>
				  <li class="lista">Rutina socio 2</li>
				  <li class="lista">Rutina socio 3</li>
				</ul>
			</div>
			<div>
				<img src="/img/instructor.jpg" alt="Always Gym" width="450">
			</div>
		</div><%
	}
	else{%>
		<%
	}%>
	</div> 
  </body>
</html>
