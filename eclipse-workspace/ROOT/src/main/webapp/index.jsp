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
	
	<div><hr style="width:75%;"></div>
	
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
		
	</footer>
	
	
	
	


	
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
			<footer>
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
			</footer><%
		}
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
			<footer>
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
			</footer><%
	  	}
	}%>
	
  </body>
</html>
