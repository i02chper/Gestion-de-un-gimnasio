<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.Date,business.clase.ClaseDTO,business.user.UserInfo,data.dao.UserDAO" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Clases</title>
	<link rel="stylesheet" href="/css/fuentes.css">
	<link rel="stylesheet" href="/css/general.css">
	<script type="text/javascript" src="/js/funciones.js"></script>
  </head>
  <body>
	<body onload="set_size()">
    <%!String redireccionar;
       ArrayList<ClaseDTO> clases;
       UserDAO dao;
       int duracion, cont = 1;
    %><%
	clases =  (ArrayList<ClaseDTO>) session.getAttribute("clases");%>
	
		<div class="header">
			<a href="/">
				<div class="home"></div>
			</a><%
			
		if(user != null && !user.getNombre().equals("")) { %>
			<a href="/perfil">
				<div class="btn"><%=user.getNombre()%></div>
			</a><%
		}
		// ADMINISTRADOR o INSTRUCTOR	
		if(user != null && (user.getTipo().equals("instr") || user.getTipo().equals("admin"))){%>
			<a href="/nuevoEspectaculo">
				<div class="btn">GESTIONAR NOVEDADES</div>
			</a>
			<a href="/logout">
				<div class="btn">CERRAR SESI&Oacute;N</div>
			</a><%
	    }
		//SOCIO
		else if(user != null && user.getTipo().equals("socio")){%>
			<a href="/logout">
				<div class="btn">CERRAR SESI&Oacute;N</div>
			</a>
			<a href="/logout">
				<div class="btn">RESERVAS</div>
			</a>
			<a href="/modificar">
				<div class="btn">RUTINAS</div>
			</a><%
	  	}
	  	else {%>
	  		<a href="/login">
				<div class="btn">
					INICIAR SESI&Oacute;N
				</div>
			</a>
		
			<a href="/signup">
				<div class="btn">
					REGISTRARSE
				</div>
			</a><%
	  	}%>
	  	</div> <!-- HEADER --><%
	  	
	  	if(user != null && (user.getTipo().equals("instr") || user.getTipo().equals("admin"))) {%>
		  	<a href="/nuevaClase">
				<div class="aniadir_clase">A&Ntilde;ADIR CLASE</div>
			</a><% 
	  	}%>
	  	
  		<div class="resultados_container" style="top: 250px"><%
	  	
	  	// Si existen espectáculos puntuales coincidentes los mostramos
		if(clases != null && !clases.isEmpty()){
			
			for(ClaseDTO clase: clases) {%>
				<div class="resultado">
					<form method="post" id="clase<%=cont%>" action="/getClase">
						<input type="hidden" name="id_clase" value="<%=clase.get_id()%>"></input>
					</form>
					<h3 class="titulo"><%=clase.get_titulo().toUpperCase()%></h3>
					<p><%=clase.get_descripcion()%></p>
					<label><strong>Instructor: </strong></label><%=clase.get_instructor() %><br>
					<label><strong>Duraci&oacute;n: </strong></label>
					<%
						duracion = clase.get_duracion();
					
						if(duracion >= 60){
							if(duracion%60 == 0) {%>
								<%=(int) (duracion / 60)%> h<%
							}
							else {%>
								<%=(int) (duracion / 60)%> h <%=duracion%60%> m<%
							}
						}
						else {%>
							<%=duracion%60%> m<%
						}%>
					<br>
					<label><strong>Ubicaci&oacute;n: </strong></label><%=clase.get_ubicacion()%><br>
					<label><strong>Categor&iacute;a: </strong></label><%=clase.get_categoria()%><br>
					<label><strong>Capacidad: </strong></label><%=clase.get_capacidad()%><br>
					<label><strong>Horario</strong></label><br>
					<div class="horario"><%
						for(String dia: clase.get_dias()){%>
							<div class="dia">
								<%=dia%>
								<p><%
								for(Date hora: clase.get_horas()){%>
									<%=clase.get_format().format(hora)%><br><%
								}%>
								</p>
							</div><%
						}%>
					</div><%
					if(user != null && (user.getTipo().equals("instr") || user.getTipo().equals("admin"))) {%>
					<span class="control">
						<button type="submit" form="clase<%=cont%>">Modificar clase</button>
					</span><%
					}%>
				</div><%
				cont++;
			}
		}%>
		</div>
  </body>
</html>