<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,business.user.User" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<%
	//Si no estamos logueados
	if(user == null || user.getNombre().equals("")) {
		response.sendRedirect("/login");
	}
	else {%>
		<%!User info; %>
		<%info = (User) session.getAttribute("user_info");
		
		  if(info == null)
		  	response.sendRedirect("/getInfo");%>
		<!DOCTYPE html>
		<html>
			<head>
				<meta charset="UTF-8">
				<title>Perfil de <%=user.getNombre()%></title>
				<link rel="stylesheet" href="/css/fuentes.css">
				<link rel="stylesheet" href="/css/general.css">
				<script type="text/javascript" src="/js/funciones.js"></script>
			</head>
			
			<body onload="set_size()">
				<div class="header">
						<a href="/">
							<div class="home"></div>
						</a>
						<a href="/getInfo">
							<div class="btn"><%=user.getNombre()%></div>
						</a><%
				//ADMINISTRADOR
				if(user.getTipo().equals("admin")){%>
						<a href="/getClases">
							<div class="btn">GESTIONAR CLASES</div>
						</a>
						<a href="/logout">
							<div class="btn">CERRAR SESI&Oacute;N</div>
						</a>
					</div><%
			   	}
				//INSTRUCTOR
				else if(user.getTipo().equals("instr")){%>
				
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
			  	}%>
				<div class="signup">
					<form method="post" action="signingup" id="form_signup">				
						<div>
							
							<label for="dni" class="label"><strong>DNI/NIF</strong></label>
							<label for="correo" class="label"><strong>CORREO</strong></label>
							<label for="nombre" class="label"><strong>NOMBRE</strong></label>
							<label for="apellidos" class="label"><strong>APELLIDOS</strong></label>
							<label for="telefono" class="label"><strong>N&Uacute;MERO DE TEL&Eacute;FONO</strong></label>
							<label for="lesion" class="label"><strong>LESI&Oacute;N</strong></label>
						</div>
						<div>
							<label><%=info.get_dni()%></label>
							<label><%=info.get_correo()%></label>
							<label><%=info.get_nombre()%></label>
							<label><%=info.get_apellidos()%></label><%
							if(!info.get_telefono().isEmpty()) {%>
								<label><%=info.get_telefono()%></label><%
							}
							else {%>
								<label>(N/A)</label><%
							}
							if(!info.get_lesion().isEmpty()) {%>
								<label><%=info.get_lesion()%></label><%
							}
							else {%>
								<label>(N/A)</label><%
							}%>
						</div>
					</form>
					<span class="control">
						<a href="/modificar"><button>Modificar perfil</button></a>
						<a href="/"><button type="button">Volver</button></a>
					</span>
				</div>
			</body>
		</html><%
	}
	
	session.setAttribute("user_info", null);%>