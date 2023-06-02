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
    %>
	<%
	bool_nov = (String) session.getAttribute("novedades_set");
	
	if(bool_nov == null || bool_nov.equals("false")) {
		response.sendRedirect("/getNovedades");
	}
	else
		session.setAttribute("novedades_set", "false");
	
	novedades = (ArrayList<NovedadDTO>) session.getAttribute("novedades");
	
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
	
<<<<<<< HEAD
	<div class="contenido"><%
	
	if(user != null && user.getTipo().equals("admin")) {%>
	  	<a href="/crearNovedad">
			<div class="aniadir_clase">A&Ntilde;ADIR NOVEDAD</div>
		</a><% 
  	}%>
	
		<div class="resultados_container" style="top: 250px"><%
			if(novedades != null && !novedades.isEmpty()) {
				
				cont = 1;
				for(int n_novedades = novedades.size() - 1; n_novedades >= 0; n_novedades--) {
					NovedadDTO novedad = novedades.get(n_novedades);%>
					<div class="novedad">
						<form method="post" id="novedad<%=cont%>" action="/getNovedad">
							<input type="hidden" name="id_novedad" value="<%=novedad.get_id()%>"></input>
						</form>
						<h3 class="titulo"><%=novedad.get_titulo().toUpperCase()%></h3>
						<p><%=novedad.get_cuerpo()%></p>
						<label><strong>Autor: </strong></label><%=novedad.get_autor() %><br>
						<label><strong>Fecha: </strong></label><%=novedad.get_formato().format(novedad.get_fecha_publicacion())%><br><%
						
						if(user != null && user.getTipo().equals("admin")) {%>
							<span class="control" style="padding: 0">
								<button type="submit" form="novedad<%=cont%>">Modificar novedad</button>
							</span><%
						}%>
					</div><%
					cont++;
				}
			}%>
		</div> 
	</div><!-- contenido -->
=======
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
	
>>>>>>> 21e2cd1c279f5a4b9ea197b9369f5b9f5ef97ed2
  </body>
</html>
