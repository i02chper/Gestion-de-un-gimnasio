<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.text.SimpleDateFormat,java.util.Date,business.clase.ClaseDTO,business.user.UserInfo,data.dao.UserDAO" %>
<jsp:useBean id="user" class="display.javabean.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="UTF-8">
	<title>Modificar clase</title>
	<link rel="stylesheet" href="/css/fuentes.css">
	<link rel="stylesheet" href="/css/general.css">
   	<script type="text/javascript" src="/js/funciones.js"></script>
  </head>
  <body onload="set_size()">
  	<%!
  	String redireccionar;
  	ClaseDTO clase;
  	Boolean lunes = false, martes = false, miercoles = false, jueves = false, viernes = false, sabado = false;
  	ArrayList<Date> horas;
  	SimpleDateFormat formato;
  	int cont;
  	%><%
  	clase = (ClaseDTO) session.getAttribute("clase");
  	horas = clase.get_horas();
  	formato = clase.get_format();
  	
  	for(String dia: clase.get_dias()){
  		if(dia.equals("lunes"))
  			lunes = true;
  		else if(dia.equals("martes"))
  			martes = true;
  		else if(dia.equals("miercoles"))
  			miercoles = true;
  		else if(dia.equals("jueves"))
  			jueves = true;
  		else if(dia.equals("viernes"))
  			viernes = true;
  		else
  			sabado = true;
  	}
  	
 	// Comprobamos que estamos logueados
	if(user != null && !user.getNombre().equals("")) {
		// Comprobamos que el usuario es un instructor/administrador y que se viene de seleccionar una clase a modificar
		if(user.getTipo().equals("socio") || clase == null){
			response.sendRedirect("/getClases");
		}
		// Todo okay
		else {%>
			<div class="header">
				<a href="/">
					<div class="home"></div>
				</a>
				<a href="/perfil">
					<div class="btn"><%=user.getNombre()%></div>
				</a><%
			//ADMINISTRADOR
			if(user.getTipo().equals("admin")){%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/gestionarNovedades">
					<div class="btn">GESTIONAR NOVEDADES</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div><%
			}
			//INSTRUCTOR
			else {%>
				<a href="/getClases">
					<div class="btn">GESTIONAR CLASES</div>
				</a>
				<a href="/modificar">
					<div class="btn">GESTIONAR RUTINAS</div>
				</a>
				<a href="/logout">
					<div class="btn">CERRAR SESI&Oacute;N</div>
				</a>
			</div><%
			}%>
			<div class="clase">
			  
			  <form method="post" action="/eliminarClase" id="eliminar">
			  	<input type="hidden" name="id_clase" value="<%=clase.get_id()%>">
			  </form>
			  <form method="post" action="/modClase" id="clase">
			  	<input type="hidden" name="id_clase" value="<%=clase.get_id()%>">
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
							oninput="this.setCustomValidity('')" value="<%=clase.get_titulo()%>"></input>
							
							<textarea form="clase" name="descripcion" required oninvalid="this.setCustomValidity('Introduce una descripci&oacute;n para la clase')" 
							oninput="this.setCustomValidity('')"><%=clase.get_descripcion()%></textarea>
							
							<label><input name="duracion" type="number" required oninvalid="this.setCustomValidity('Introduce duraci&oacute;n de la clase')" 
							oninput="this.setCustomValidity('')" style="width: 100px;" value="<%=clase.get_duracion()%>"></input>&nbsp;MINUTOS</label>
							
							<input name="capacidad" type="number" required oninvalid="this.setCustomValidity('Introduce la capacidad m&aacute;xima')" 
							oninput="this.setCustomValidity('')" style="width: 100px;" value="<%=clase.get_capacidad()%>"></input>
							
							<input name="categoria" type="text" required oninvalid="this.setCustomValidity('Introduce la categor&iacute;a la clase')" 
							oninput="this.setCustomValidity('')" value="<%=clase.get_categoria()%>"></input>
							
							<%
					  		if(!user.getTipo().equals("instr")){%>
					  			<label><input name="instructor" type="text" required oninvalid="this.setCustomValidity('Introduce al instructor de la clase')" 
								oninput="this.setCustomValidity('')" style="width: 300px" value="<%=clase.get_instructor()%>"> (CORREO)</label></input><%
					  		}
					  		else {%>
					  			<input type="hidden" name="instructor" value="<%=user.getCorreo()%>"><%
					  		}%>
					  		<input name="ubicacion" type="text" required oninvalid="this.setCustomValidity('Introduce la ubicaci&oacute;n la clase')" 
							oninput="this.setCustomValidity('')" value="<%=clase.get_ubicacion()%>"></input>
						</div>
					</div>
					<div id="tipo_elementos">
						
						<label for="dia_t">D&Iacute;A(S) DE LA SEMANA</label>
						<div class="dias">
							<div>
								<label><input type="checkbox" name ="dia" value="lunes" <%if(lunes == true) {%>checked<%}%>>Lunes</label>
								<label><input type="checkbox" name ="dia" value="martes" <%if(martes == true) {%>checked<%}%>>Martes</label>
							</div>
							<div>
								<label><input type="checkbox" name ="dia" value="miercoles" <%if(miercoles == true) {%>checked<%}%>>Mi&eacute;rcoles</label>
								<label><input type="checkbox" name ="dia" value="jueves" <%if(jueves == true) {%>checked<%}%>>Jueves</label>
							</div>
							<div>
								<label><input type="checkbox" name ="dia" value="viernes" <%if(viernes == true) {%>checked<%}%>>Viernes</label>
								<label><input type="checkbox" name ="dia" value="sabado" <%if(sabado == true) {%>checked<%}%>>S&aacute;bado</label>
							</div>
						</div>
							<span class="horas">
								<label for="hora">HORA(S)</label><%
							if(!horas.isEmpty()) {%>
								<div id="hora1">
									<input name="hora" type="time" value="<%=formato.format(horas.get(0))%>" required></input>
								</div><%
								
								cont = 1;
								for(Date hora: horas){
									if(hora != horas.get(0)){
										cont++;%>
										<span id="hora<%=cont%>">
											<input type="time" name="hora" value="<%=formato.format(hora)%>" required><button type="button" style="vertical-align: text-bottom;" onclick="eliminar_hora('hora<%=cont%>')">Eliminar</button>
										</span><%
									}
								}
							}
							else {%>
								<span id="hora<%=cont%>">
									<input type="time" name="hora" required>
								</span><%
							}%>
								<div><button type="button" onclick="addhora(<%=cont%>)">A&Ntilde;ADIR HORA</button></div>
							</span>
					</div>
				</div> 
			</form>
			<div class="control">
				<button type="submit" form="clase">Modificar clase</input>
				<button type="submit" form="eliminar">Eliminar clase</button>
				<a href="/">
					<button type="button">Volver</button>
				</a>
			</div>
			
		  </div><%	
		}
	}
	else {
		response.sendRedirect("/login");
	}%>
  </body>
</html>