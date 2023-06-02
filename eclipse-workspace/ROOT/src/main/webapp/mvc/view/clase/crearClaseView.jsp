<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="UTF-8">
		<title>Crear clase</title>
		<link rel="stylesheet" href="/css/fuentes.css">
		<link rel="stylesheet" href="/css/general.css">
        <script type="text/javascript" src="/js/funciones.js"></script>
	</head>
	<body onload="set_size()">
		<%!String redireccionar;%>
		<%
		// Comprobamos que estamos logueados
		if(user != null && !user.getNombre().equals("")) {
		%>	<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/getInfo">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
			
		//Comprobamos que el usuario es ADMINISTRADOR o INSTRUCTOR
		if(!user.getTipo().equals("socio")){%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div>
				
			<div class="clase">
			  <form method="post" action="addClase" id="clase">
			  	<div style="display:block">
			  	
			  		<%// Elementos comunes a todos los espectáculos%>
			  		<div style="display: block">
					  	<div id="labels">
					  		<label for="titulo">T&Iacute;TULO DE LA CLASE </label>
					  		<label for="descripcion" style="height: 215px;">DESCRIPCI&Oacute;N</label>
					  		<label for="duracion">DURACI&Oacute;N</label>
					  		<label for="capacidad">CAPACIDAD</label>
					  		<label for="categoria">CATEGOR&Iacute;A</label>
					  		<%
					  		if(!user.getTipo().equals("instr")){%>
					  			<label for="instructor"> INSTRUCTOR</label><%
					  		}
					  		else {%>
					  			<input type="hidden" name="instructor" value="<%=user.getCorreo()%>"><%
					  		}%>
					  		<label for="categoria">UBICACI&Oacute;N</label>
					  	</div>
						
						<div>						
							<input name="titulo" type="text" required oninvalid="this.setCustomValidity('Introduce el t&iacute;tulo de la clase')" 
							oninput="this.setCustomValidity('')"></input>
							
							<textarea form="clase" name="descripcion" required oninvalid="this.setCustomValidity('Introduce una descripci&oacute;n para la clase')" 
							oninput="this.setCustomValidity('')" ></textarea>
							
							<label><input name="duracion" type="number" required oninvalid="this.setCustomValidity('Introduce duraci&oacute;n de la clase')" 
							oninput="this.setCustomValidity('')" style="width: 100px;"></input>&nbsp;MINUTOS</label>
							
							<input name="capacidad" type="number" required oninvalid="this.setCustomValidity('Introduce la capacidad m&aacute;xima')" 
							oninput="this.setCustomValidity('')" style="width: 100px;"></input>
							
							<input name="categoria" type="text" required oninvalid="this.setCustomValidity('Introduce la categor&iacute;a la clase')" 
							oninput="this.setCustomValidity('')"></input>
							
							<%
					  		if(!user.getTipo().equals("instr")){%>
					  			<input name="instructor" type="text" required oninvalid="this.setCustomValidity('Introduce al instructor de la clase')" 
								oninput="this.setCustomValidity('')"></input><%
					  		}
					  		else {%>
					  			<input type="hidden" name="instructor" value="<%=user.getCorreo()%>"><%
					  		}%>
					  		<input name="ubicacion" type="text" required oninvalid="this.setCustomValidity('Introduce la ubicaci&oacute;n la clase')" 
							oninput="this.setCustomValidity('')"></input>
						</div>
					</div>
					<div id="tipo_elementos">
						
						<label for="dia_t">D&Iacute;A(S) DE LA SEMANA</label>
						<div class="dias">
							<div>
								<label><input type="checkbox" name ="dia" value="lunes">Lunes</label>
								<label><input type="checkbox" name ="dia" value="martes">Martes</label>
							</div>
							<div>
								<label><input type="checkbox" name ="dia" value="miercoles">Mi&eacute;rcoles</label>
								<label><input type="checkbox" name ="dia" value="jueves">Jueves</label>
							</div>
							<div>
								<label><input type="checkbox" name ="dia" value="viernes">Viernes</label>
								<label><input type="checkbox" name ="dia" value="sabado">S&aacute;bado</label>
							</div>
						</div>
						<span class="horas">
							<label for="hora">HORA(S)</label>
							<div id="hora1">
								<input name="hora" type="time" required></input>
							</div>
							<div><button type="button" onclick="addhora(1)">A&Ntilde;ADIR HORA</button></div>
						</span>
					</div>
				</div> 
			</form>
			<div class="control">
				<button type="submit" form="clase">A&ntilde;adir clase</input>
				<a href="/">
					<button type="button">Volver</button>
				</a>
			</div>
			
		  </div>
		  <%}
			else{
				session.setAttribute("error", "Acceso reservado a instructores y administradores");
				redireccionar = "/error";
				response.sendRedirect(redireccionar);
			}
		}
		//Si no estamos logueados
		else{
			redireccionar = "/login";
			response.sendRedirect(redireccionar);
		}%>
	</body>
</html>