package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;

import business.user.User;
import business.user.UserInfo;

/**
 * Clase que representa el acceso a los datos del usuario
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 *
 */
public class UserDAO {
	private Connection con = null;
	private Properties statements = null;
	
	/**
	 * Constructor parametrizado
	 * @param aplicacion Servlet para poder llamarlo desde el DAO
	 */
	public UserDAO(ServletContext aplicacion) {
		
		/* El fichero 'properties' pasado aqui es el fichero 'RutaConsultas' del loginController */
		DBConnection db = new DBConnection();
		con = db.conectar();
		
		try {
			statements = new Properties();
			statements.load(aplicacion.getResourceAsStream(aplicacion.getInitParameter("SQL_statements")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * MÃ©todo encargado de cerrar conexión
	 * @return Devuelve true si se ha cerrado correctamente o false en caso contrario
	 */
	public boolean cerrarConexion() {
		try {
			this.con.close();
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Metodo encargado de obtener un usuario
	 * @param usuario Nick del usuario que queremos obtener
	 * @param pass Contraseña del usuario
	 * @return Devuelve un usuario con todos los datos del mismo o null en caso de fallo
	 */
	public User getUsuario(String correo, String pass) {
		User user = new User();
		
		try {
			PreparedStatement get_usuario = con.prepareStatement(this.statements.getProperty("get_usuario"));
			//get_usuario=SELECT nombre, apellidos, dni, tipo FROM Usuario WHERE correo = ? AND pass = ?
			
			get_usuario.setString(1, correo);
			get_usuario.setString(2, pass);
			
			ResultSet user_info = get_usuario.executeQuery();
			user_info.next();
			
			//nombre, apellidos, nick, correo, tipo, log
			user.set_nombre(user_info.getString(1));
			user.set_apellidos(user_info.getString(2));
			user.set_dni(user_info.getString(3));
			user.set_correo(correo);
			user.set_tipo(user_info.getString(4));
			user.set_lesion(user_info.getString(5));
			
			get_usuario.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			user = null;
		}
		
		return user;
	}
	
	/** TODO: DEPRECATED
	 * Método encargado de devolver todos los logs existente
	 * @return Cadena con todos los logs
	 *
	public String getLogs(){
		String logs = "";
		
		Statement get_logs;
		
		try {
			get_logs = con.createStatement();

			ResultSet logs_db = get_logs.executeQuery(this.statements.getProperty("get_logs"));
			
			while(logs_db.next()) {
				logs += "Usuario: " + logs_db.getString(1) + " -> &Uacute;ltima conexi&oacute;n: " + logs_db.getString(2) + "<br>";
			}
				
			get_logs.close();
			
		} catch (SQLException e) {
			logs = "";
		}
		
		return logs;
	}*/
	
	/**
	 * Método encargado de devolver el registro de un usuario
	 * @param usuario Nick del usuario
	 * @return Cadena con el registro del usuario
	 */
	public String getRegistro(String usuario) {
		String registro = "";
		
		try {
			PreparedStatement get_registro = con.prepareStatement(this.statements.getProperty("get_registro"));
			get_registro.setString(1, usuario);
			
			ResultSet reg = get_registro.executeQuery();
			reg.next();
			
			registro = reg.getString(1);
			
			get_registro.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return registro;
	}
	
	/**
	 * Método encargado de actualizar el log de un usuario
	 * @param usuario Nick del usuario
	 * @return Devuelve true si se ha actualizado con éxito o false en caso contrario
	 *
	 * TODO: DEPRECATED
	public boolean actualizarLog(String usuario) {
		
		PreparedStatement actualizar;
		try {
			actualizar = con.prepareStatement(this.statements.getProperty("actualizar"));
			actualizar.setString(1, usuario);
			actualizar.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}*/
	
	/**
	 * Método encargado de modificar los datos de un usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param correo Correo asociado al usuario
	 * @param pass Contraseña del usuario
	 * @param usuario Nick del usuario
	 */
	public boolean modificarDatos(String pass, String nombre, String apellidos, String telefono, String lesion, String correo) {
		// pass, nombre, apellidos, telefono, lesion
		try {
			PreparedStatement get_datos = con.prepareStatement(this.statements.getProperty("get_datos"));
			get_datos.setString(1, correo);
			
			ResultSet datos = get_datos.executeQuery();
			datos.next();
			
			PreparedStatement modificar = con.prepareStatement(this.statements.getProperty("modificar"));
			
			if(!pass.equals(""))
				modificar.setString(1, pass);
			else
				modificar.setString(1, datos.getString(1));
			
			if(!nombre.equals(""))
				modificar.setString(2, nombre);
			else
				modificar.setString(2, datos.getString(2));
			
			if(!apellidos.equals(""))
				modificar.setString(3, apellidos);
			else
				modificar.setString(3, datos.getString(3));
			
			if(!telefono.equals(""))
				modificar.setString(4, telefono);
			else
				modificar.setString(4, datos.getString(4));
			
			if(!lesion.equals(""))
				modificar.setString(5, lesion);
			else
				modificar.setString(5, datos.getString(5));
			
			modificar.setString(6, correo);
			modificar.executeUpdate();
			
			get_datos.close();
			modificar.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método encargado de añadir un nuevo usuario
	 * @param usuario Nick del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param correo Correo asociado al usuario
	 * @param pass Contraseña del usuario
	 * @param tipo Tipo del usuario
	 * @return Devuelve 0 si se ha añadido correctamente o un entero que representa el tipo de error ocurrido
	 */
	//addUsuario(correo, pass, nombre, apellidos, dni, telefono, tipo);
	public Integer addUsuario(String correo, String pass, String nombre, String apellidos, String dni, String telefono, String tipo) {
		// Comprobamos que el código introducido, de haber introducido alguno, es correcto
		if(tipo.equals("error"))
			return -1;
		
		try {
			Statement get_correos_usuarios = con.createStatement();
			
			ResultSet correos = get_correos_usuarios.executeQuery(this.statements.getProperty("get_usuarios"));
			
			// Comprobamos que el correo introducido no se encuentra ya registrado
			while(correos.next())
				if(correos.getString(1).equals(correo))
					return -2;
			
			PreparedStatement aniadir = con.prepareStatement(this.statements.getProperty("aniadir_usuario"));
			
			//correo, pass, nombre, apellidos, dni, telefono, tipo
			aniadir.setString(1, correo);
			aniadir.setString(2, pass);
			aniadir.setString(3, nombre);
			aniadir.setString(4, apellidos);
			aniadir.setString(5, dni);
			aniadir.setString(6, telefono);
			aniadir.setString(7, tipo);
			
			aniadir.executeUpdate();
			
			get_correos_usuarios.close();
			aniadir.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			return -3;
		}
		return 0;
	}
	
	/**
	 * Método encargado de obtener la información de los usuarios del sistema
	 * @return Devuelve un ArrayList con la información de los usuarios
	 */
	public ArrayList<UserInfo> getInfoAdmin(){
		ArrayList<UserInfo> info = new ArrayList<UserInfo>();
		
		try {
			Statement get_info_usuarios = con.createStatement();
			ResultSet info_usuarios = get_info_usuarios.executeQuery(this.statements.getProperty("get_info_usuarios"));
			
			while(info_usuarios.next())
				info.add(new UserInfo(info_usuarios.getString(1), info_usuarios.getString(2), info_usuarios.getString(3)));
			
			info_usuarios.close();
			get_info_usuarios.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return info;
	}
	
	/**
	 * Método encargado de eliminar un usuario del sistema
	 * @param nombre_usuario Nick del usuario
	 * @return Devuelve true si se ha eliminado correctamente o false en caso contrario
	 */
	public Boolean eliminarUsuario(String correo_usuario) {
		
		try {
			//Eliminamos todas las reservas de RESERVA_SLOT asociadas al usuario
			PreparedStatement eliminar_reservas_slots = con.prepareStatement(this.statements.getProperty("eliminar_reservas_slots"));
			eliminar_reservas_slots.setString(1, correo_usuario);
			eliminar_reservas_slots.executeUpdate();
			eliminar_reservas_slots.close();
			
			//Eliminamos todas las reservas de RESERVA_CLASE asociadas al usuario
			PreparedStatement eliminar_reservas_clases = con.prepareStatement(this.statements.getProperty("eliminar_reservas_clases"));
			eliminar_reservas_clases.setString(1, correo_usuario);
			eliminar_reservas_clases.executeUpdate();
			eliminar_reservas_clases.close();
			
			//Eliminamos todas las reservas de RESERVA asociadas al usuario
			PreparedStatement eliminar_reservas = con.prepareStatement(this.statements.getProperty("eliminar_reservas"));
			eliminar_reservas.setString(1, correo_usuario);
			eliminar_reservas.executeUpdate();
			eliminar_reservas.close();
			
			//Eliminamos todas las rutinas de AUTORIA asociadas al usuario
			PreparedStatement eliminar_rutinas_autor = con.prepareStatement(this.statements.getProperty("eliminar_rutinas_autor"));
			eliminar_rutinas_autor.setString(1, correo_usuario);
			eliminar_rutinas_autor.executeUpdate();
			eliminar_rutinas_autor.close();
			
			//Eliminamos todas las rutinas de RUTINA asociadas al usuario
			PreparedStatement eliminar_rutinas = con.prepareStatement(this.statements.getProperty("eliminar_rutinas"));
			eliminar_rutinas.setString(1, correo_usuario);
			eliminar_rutinas.executeUpdate();
			eliminar_rutinas.close();
			
			//Eliminamos todos los pagos de PAGO asociados al usuario
			PreparedStatement eliminar_pagos = con.prepareStatement(this.statements.getProperty("eliminar_pagos"));
			eliminar_pagos.setString(1, correo_usuario);
			eliminar_pagos.executeUpdate();
			eliminar_pagos.close();
			
			//Eliminamos al usuario de USUARIO
			PreparedStatement eliminar_usuario = con.prepareStatement(this.statements.getProperty("eliminar_usuario"));
			eliminar_usuario.setString(1, correo_usuario);
			eliminar_usuario.executeUpdate();
			eliminar_usuario.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}

