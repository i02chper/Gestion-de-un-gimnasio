package business.user;

import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un usuario que accede al sistema
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 *
 */
public class User {
	
	private String _dni;
	private String _nombre;
	private String _apellidos;
	private String _correo;
	private String _telefono;
	private String _tipo;
	private String _lesion;
	DateTimeFormatter _formato;
	
	/**
	 * Constructor vacío (por defecto)
	 */
	public User(){
		this._dni = "";
		this._nombre = "";
		this._apellidos = "";
		this._correo = "";
		this._telefono = "";
		this._tipo = "";
		this._lesion = "";
		this._formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	}

	/**
	 * Método encargado de devolver el nick del usuario
	 * @return Nick del usuario en la aplicación
	 */
	public String get_dni() {
		return _dni;
	}

	/**
	 * Método encargado de modificar el nick del usuario
	 * @param _nick Nuevo nick del usuario
	 */
	public void set_dni(String _dni) {
		this._dni = _dni;
	}

	/**
	 * Método encargado de devolver el nombre del usuario
	 * @return Nombre del usuario
	 */
	public String get_nombre() {
		return _nombre;
	}

	/**
	 * Método encargado de modificar el nombre del usuario
	 * @param _nombre Nuevo nombre del usuario
	 */
	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	/**
	 * Método encargado de devolver los apellidos del usuario
	 * @return Apellidos del usuario
	 */
	public String get_apellidos() {
		return _apellidos;
	}

	/**
	 * Método encargado de modificar los apellidos del usuario
	 * @param _apellidos Nuevos apellidos del usuario
	 */
	public void set_apellidos(String _apellidos) {
		this._apellidos = _apellidos;
	}

	/**
	 * Método encargado de devolver el correo del usuario
	 * @return Correo asociado al usuario en la aplicación
	 */
	public String get_correo() {
		return _correo;
	}

	/**
	 * Método encargado de modificar el correo del usuario
	 * @param _correo Nuevo correo asociado al usuario en la aplicación
	 */
	public void set_correo(String _correo) {
		this._correo = _correo;
	}

	/**
	 * Método encargado de devolver el tipo del usuario
	 * @return Tipo del usuario
	 */
	public String get_tipo() {
		return _tipo;
	}

	/**
	 * Método encargado de modificar el tipo del usuario
	 * @param _tipo Nuevo tipo del usuario
	 */
	public void set_tipo(String _tipo) {
		this._tipo = _tipo;
	}

	/**
	 * Método encargado de devolver el log del usuario
	 * @return Log del usuario
	 */
	public String get_lesion() {
		return _lesion;
	}

	/**
	 * Método encargado de modificar el log del usuario
	 * @param _log Nuevo log del usuario
	 */
	public void set_lesion(String _lesion) {
		this._lesion = _lesion;
	}
	
	

	public String get_telefono() {
		return this._telefono;
	}

	public void set_telefono(String telefono) {
		this._telefono = telefono;
	}

	/**
	 * Método encargado de devolver el formato
	 * @return Formato de las fechas
	 */
	public DateTimeFormatter get_formato() {
		return _formato;
	}

	/**
	 * Método encargado de modificar el formato de las fechas
	 * @param _formato Nuevo formato de las fechas
	 */
	public void set_formato(DateTimeFormatter _formato) {
		this._formato = _formato;
	}
	
}
