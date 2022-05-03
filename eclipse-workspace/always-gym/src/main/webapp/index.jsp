<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter,java.util.ArrayList,business.user.UserInfo,data.dao.UserDAO" %>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Inicio</title>
	<link rel="stylesheet" href="css/fuentes.css">
	<link rel="stylesheet" href="css/general.css">
	<script type="text/javascript" src="js/funciones.js"></script>
  </head>
  <body onl
import org.eclipse.jdt.internal.compiler.ast.ThisReference;oad="set_size()">
    <%!String mensaje, tipo, hoy;
       UserDAO dao;
       ArrayList<UserInfo> info_admin;
    %>
	<%
	//Si NO estamos logueados
	if(customerBean == null || customerBean.getNombre().equals("")) { %>
	<div class="header">
		<a href="/practica3">
			<div class="home"></div>
		</a>	
		<a href="/always-gym/login">
			<div class="btn">
				ACCEDER
			</div>
		</a>
	
		<a href="/practica3/signup">
			<div class="btn">
				REGISTRARSE
			</div>
		</a>
	</div>
	
	<div class="informacion">
		<p class="titulo">¡BIENVENIDO A LA WEB DE GESTI&Oacute;N DE ESPECT&Aacute;CULOS!</p>
		
		<p>Inicia sesi&oacute;n pulsando sobre <span class="btn">ACCEDER</span> para acceder a las funcionalidades del sistema.</p>
		<ul>
			<li>Si inicia sesi&oacute;n como administrador, podr&aacute; gestionar los espect&aacute;culos del sistema.</li>
			<li>Si inicio sesi&oacute;n como espectador, podr&aacute; consultar los espect&aacute;culos disponibles, crear cr&iacute;ticas o valorar las cr&iacute;ticas de otros usuarios.</li>
			<li>Si no dispones de cuenta de usuario, puedes crear una de forma gratu&iacute;ta en la secci&oacute;n <span class="btn">REGISTRARSE</span>.</li>
			<li>Si en cualquier momento desea volver a esta p&aacute;gina, pulse <span class="btn_home">.</span></li>
		</ul>
	</div>
	<%}
	
	//En caso contrario
	else {%>
		<h1>Bienvenido <jsp:getProperty name="customerBean" property="nombre"/>!</h1>
		
	<%	//Distinguimos entre administrador y espectador
		if((tipo = customerBean.getTipo()).equals("administrador")){%>
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
			<div class="informacion">
				<p class="titulo">¡BIENVENIDO <%= customerBean.getNombre().toUpperCase() %>!</p>
				
				<p>Como administrador tienes acceso a las siguientes funcionalidades del sistema:</p>
				<ul>
					<li>Consultar la informaci&oacute;n de todos los usuarios registrados en <span class="btn">INFORMACI&Oacute;N USUARIOS</span></li>
					<li>A&ntilde;adir un nuevo espect&aacute;culo al sistema en <span class="btn">A&Ntilde;ADIR ESPECT&Aacute;CULO</span></li>
					<li>Modificar o cancelar distintas sesiones de los espect&aacute;culos a&ntilde;adidos en <span class="btn">GESTI&Oacute;N SESIONES</span></li>
					<li>Actualizar los datos de tu perfil en <span class="btn">MODIFICAR PERFIL</span></li>
					<li>Cerrar sesi&oacute;n o eliminar tu cuenta en <span class="btn">CERRAR SESI&Oacute;N</span></li>
				</ul>
			</div>
	  <%}
		//ESPECTADOR
		else {%>
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
			<div class="informacion">
				<p class="titulo">¡BIENVENIDO <%= customerBean.getNombre().toUpperCase() %>!</p>
				
				<p>Como espectador tienes acceso a las siguientes funcionalidades del sistema:</p>
				<ul>
					<li>Consultar todos los espect&aacute;culos rese&ntilde;ables en <span class="btn">ESPECT&Aacute;CULOS</span></li>
					<li>Consultar y valorar todas las cr&iacute;ticas de los espect&aacute;culos registradas en el sistema en <span class="btn">ESPECT&Aacute;CULOS</span></li>
					<li>Redactar una nueva cr&iacute;tica en <span class="btn">ESPECT&Aacute;CULOS</span></li>
					<li>Buscar un espect&aacute;culo por t&iacute;tulo o categor&iacute;a en <span class="btn">BUSCAR</span></li>
					<li>Actualizar los datos de tu perfil en <span class="btn">MODIFICAR PERFIL</span></li>
					<li>Cerrar sesi&oacute;n o eliminar tu cuenta en <span class="btn">CERRAR SESI&Oacute;N</span></li>
				</ul>
			</div><%
		}
	}%>
  </body>
</html>
