<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" class="display.javabean.CustomerBean" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
		<meta charset="UTF-8">
		<title>Dar de alta espect&aacute;culo</title>
		<link rel="stylesheet" href="css/fuentes.css">
		<link rel="stylesheet" href="css/general.css">
        <script type="text/javascript" src="/practica3/js/funciones.js"></script>
	</head>
	<body onload="set_size(),show_selected()">
		<%!String redireccionar;%>
		<%
		// Comprobamos que estamos logueados
		if(customerBean != null && !customerBean.getNombre().equals("")) {
			
			// Comprobamos que el usuario es administrador
			if(customerBean.getTipo().equals("administrador")){%>
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
				
			<div class="nuevo_espectaculo">
			  <form method="post" action="addEspectaculo" id="espectaculo">
			  	<div style="display:block">
			  	
			  		<%// Elementos comunes a todos los espectáculos%>
			  		<div style="display: block">
					  	<div id="labels">
					  		<label for="tipo">TIPO DE ESPECT&Aacute;CULO </label>
					  		<label for="titulo">T&Iacute;TULO</label>
					  		<label for="descripcion" style="height: 215px;">DESCRIPCI&Oacute;N</label>
					  		<label for="localidades">LOCALIDADES</label>
					  		<label for="categoria">CATEGOR&Iacute;A</label>
					  	</div>
						
						<div>
							<span>
								<input name="tipo" type="radio" value="puntual" onclick="show_puntual()" checked>Puntual</input>
								<input name="tipo" type="radio" value="temporada" onclick="show_temporada()">Temporada</input>
								<input name="tipo" type="radio" value="multiple" onclick="show_multiple()">M&uacute;ltiple</input>
							</span>
						
							<input name="titulo" type="text" required oninvalid="this.setCustomValidity('Introduce el t&iacute;tulo del espect&aacute;culo')" 
							oninput="this.setCustomValidity('')"></input>
							
							<textarea form="espectaculo" name="descripcion" required oninvalid="this.setCustomValidity('Introduce una descripci&oacute;n para el espect&aacute;culo')" 
							oninput="this.setCustomValidity('')" ></textarea>
							
							
							<input name="localidades" type="number" required oninvalid="this.setCustomValidity('Introduce el n&uacute;mero de localidades')" 
							oninput="this.setCustomValidity('')" style="width: 100px;"></input>
							
							
							<input name="categoria" type="text" required oninvalid="this.setCustomValidity('Introduce la categor&iacute;a del espect&aacute;culo')" 
							oninput="this.setCustomValidity('')"></input>
						</div>
					</div>
					<div id="tipo_elementos">
						<div class="puntual">
							<label for="fecha_p">FECHA</label>
							<input name="fecha_p" type="datetime-local" required oninvalid="this.setCustomValidity('Introduce la fecha del espect&aacute;culo')" 
							   oninput="this.setCustomValidity('')"></input>
						</div>
						<div class="temporada">
							<label for="dia_t">D&Iacute;A DE LA SEMANA</label>
							<select name="dia_t" form="espectaculo">
								<option value="Lunes">Lunes</option>
								<option value="Martes">Martes</option>
								<option value="Miercoles">Mi&eacute;rcoles</option>
								<option value="Jueves">Jueves</option>
								<option value="Viernes">Viernes</option>
								<option value="Sabado">S&aacute;bado</option>
								<option value="Domingo">Domingo</option>
							</select><br>
							
							<label for="inicio_t">FECHA DE INICIO</label>
							<input name="inicio_t" type="date" required oninvalid="this.setCustomValidity('Introduce la fecha de inicio del espect&aacute;culo')" 
								   oninput="this.setCustomValidity('')"></input><br>
							
							<label for="final_t">FECHA FINAL</label>
							<input name="final_t" type="date" required oninvalid="this.setCustomValidity('Introduce la &uacute;ltima fecha del espect&aacute;culo')" 
								   oninput="this.setCustomValidity('')"></input>
						</div>
						
						<span class="multiple">
							<label for="fecha_m">FECHAS</label>
							<div id="fecha1">
								<input name="fecha_m" type="datetime-local" required></input>
							</div>
							<div><button type="button" onclick="addfecha(1)">A&Ntilde;ADIR FECHA</button></div>
						</span>
					</div>
				</div> 
			</form>
			<div class="control">
				<button type="submit" form="espectaculo">A&ntilde;adir espect&aacute;culo</input>
				<a href="/practica3/">
					<button type="button">Volver</button>
				</a>
			</div>
			
		  </div>
		  <%}
			else{
				session.setAttribute("error", "Acceso reservado a administradores");
				redireccionar = "/practica3/error";
				response.sendRedirect(redireccionar);
			}
		}
		//Si no estamos logueados
		else{
			redireccionar = "/practica3/login";
			response.sendRedirect(redireccionar);
		}
		%>
	</body>
</html>