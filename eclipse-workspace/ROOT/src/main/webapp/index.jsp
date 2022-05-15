<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter,java.util.ArrayList,business.user.UserInfo,data.dao.UserDAO" %>
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
    <%!String mensaje, tipo, hoy;
       UserDAO dao;
    %>
	<%
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
		
		<a href="/contacto">
			<div class="btn">
				CONTACTO
			</div>
		</a>
		
		<a href="/tarifas">
			<div class="btn">
				TARIFAS
			</div>
		</a>
		
		<a href="/caracteristicas">
			<div class="btn">
				CARACTER&Iacute;STICAS
			</div>
		</a>
	</div>
	<div class="informacion">
		<div class="titulo"> ALWAYS GYM</div>
		<div class="subtitulo">Siempre en forma<br></div>
		<div class="columna"> ACTIVIDADES
		<a href="/caracteristicas">
			<div class="btn">
				SPINNING
			</div>
		</a>
		<a href="/caracteristicas">
			<div class="btn">
				BOXEO
			</div>
		</a>
		<a href="/caracteristicas">
			<div class="btn">
				YOGA
			</div>
		</a>
		<a href="/caracteristicas">
			<div class="btn">
				PILATES
			</div>
		</a>
		</div>
		<div>
			<img src="../img/gym.jpg" alt="Always Gym" width="450">
		</div>
	</div>
	

	<%}
	
	//En caso contrario
	else {
		//ADMINISTRADOR
		%>	<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/perfil">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
				
		if((tipo = user.getTipo()).equals("admin")){%>
				<a href="/getSesiones">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/nuevoEspectaculo">
					<div class="btn">GESTIONAR NOVEDADES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
				<a href="/modificar">
					<div class="btn">MODIFICAR PERFIL</div>
				</a>
			</div>
			
			<div class="informacion">
				<div class="titulo"> BIENVENIDO: <%=user.getNombre()%></div>
				<div class="subtitulo">Área del administrador<br><br><br></div>
				<div class="columna_adm"> Área de novedades: <br>
				<ul>
				  <li class="lista">Novedad 1</li>
				  <li class="lista">Novedad 2</li>
				  <li class="lista">Novedad 3</li>
				</ul>
				</div>
				
				<div class="columna_adm"> Área de clases: <br>
				<ul>
				  <li class="lista">Spining</li>
				  <li class="lista">Boxeo</li>
				  <li class="lista">Pilates</li>
				</ul>
				</div>
				
				<div class="columna_adm"> Área de facturación: <br>
				<ul>
				  <li class="lista">Factura 1</li>
				  <li class="lista">Factura 2</li>
				  <li class="lista">Factura 3</li>
				</ul>
				</div>
				
			</div>
	  <%}
		//INSTRUCTOR
		else if((tipo = user.getTipo()).equals("instr")){%>
		
				<a href="/logout">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/modificar">
					<div class="btn">GESTIONAR RUTINAS</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
				<a href="/modificar">
					<div class="btn">MODIFICAR PERFIL</div>
				</a>
			</div>
			
			<div class="informacion">
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
					<img src="../img/instructor.jpg" alt="Always Gym" width="450">
				</div>
			</div>
			
			
		<%		}
		//SOCIO
		else{%>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
				<a href="/modificar">
					<div class="btn">MODIFICAR PERFIL</div>
				</a>
				<a href="/logout">
					<div class="btn">RESERVAS</div>
				</a>
				<a href="/modificar">
					<div class="btn">RUTINAS</div>
				</a>
			</div>
			
			<div class="informacion">
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
					<img src="../img/socio.jpg" alt="Always Gym" width="450">
				</div>
			</div>
			
			
<%
	  	}
	}%>
	
	<div class="footer">
		<div class="titulo"> NOVEDADES</div>
		<div class="columna-footer">
			<p>Hola Mundo</p>
		</div>
		<div class="columna-footer">
			<p>Hola Mundo</p>
		</div>
		<div class="columna-footer">
			<p>Hola Mundo</p>
		</div>
		
	</div>
	
  </body>
</html>
