<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cerrar sesi&oacute;n</title>
		<link rel="stylesheet" href="css/fuentes.css">
		<link rel="stylesheet" href="css/general.css">
		<script type="text/javascript" src="/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
	<%
		//Comprobamos que estamos logueados, en caso contrario redirigiremos al login
		if(user != null && !user.getNombre().equals("")){
			
			%>	<div class="header">
					<a href="/">
						<div class="home"></div>
					</a>
					<a href="/perfil">
						<div class="btn"><%=user.getNombre()%></div>
					</a><%
					
			//ADMINISTRADOR
			if(user.getTipo().equals("admin")){%>
			
					<a href="/getSesiones">
						<div class="btn">GESTIONAR CLASES</div>
					</a>
					<a href="/nuevoEspectaculo">
						<div class="btn">GESTIONAR NOVEDADES</div>
					</a>
				</div>
		  <%}
			//INSTRUCTOR
			else if(user.getTipo().equals("instr")){%>

					<a href="/logout">
						<div class="btn">GESTIONAR CLASES</div>
					</a>
					<a href="/modificar">
						<div class="btn">GESTIONAR RUTINAS</div>
					</a>
				</div><%
			}
			//SOCIO
			else{%>
					<a href="/logout">
						<div class="btn">RESERVAS</div>
					</a>
					<a href="/modificar">
						<div class="btn">RUTINAS</div>
					</a>
					<a href="/getClases">
						<div class="btn">CLASES DISPONIBLES</div>
					</a>
				</div><%
		  	}%>
			
			<div class="opciones">
			
				<div class="cerrar_sesion">
					<a href="/logingout"><button>Cerrar sesi&oacute;n</button></a>
					<p>Pulsa aqu&iacute; para cerrar la sesi&oacute;n.</p>
				</div>
				
				<div class="eliminar">
					<a href="/eliminarUsuario">
						<button onclick="return confirm('¿Seguro que deseas eliminar tu cuenta de forma PERMANENTE?')">Eliminar cuenta</button>
					</a>
					
					<p>Pulsa aqu&iacute; para eliminar tu cuenta de forma permanente.</p>
					<div class="advertencia">
						<p>¡ADVERTENCIA!</p>
						<p>TODAS TUS RESERVAS, RUTINAS Y PAGOS SER&Aacute;N ELIMINADOS PARA SIEMPRE</p>
					</div>
				</div>
			</div><%
		}
		else
			response.sendRedirect("/login");%>
	</body>
</html>