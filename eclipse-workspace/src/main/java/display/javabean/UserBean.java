package display.javabean;

import java.io.Serializable;

/**
 * Clase que representa la información del usuario en la página web
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 *
 */
public class UserBean implements java.io.Serializable{
	
	private String _nombre;
	private String _correo;
	private String _tipo;
	
	/**
	 * Constructor vacío (por defecto)
	 */
	public UserBean() {
		this._nombre = "";
		this._correo = "";
		this._tipo = "";
	}

	/**
	 * Método encargado de devolver el nick del usuario
	 * @return Nick del usuario
	 */
	public String getCorreo() {
		return this._correo;
	}

	/**
	 * Método encargado de modificar el nick del usuario
	 * @param usuario Nuevo nick del usuario
	 */
	public void setCorreo(String correo) {
		this._correo = correo;
	}

	/**
	 * Método encargado de devolver el nombre del usuario
	 * @return Nombre del usuario
	 */
	public String getNombre() {
		return this._nombre;
	}

	/**
	 * Método encargado de modificar el nombre del usuario
	 * @param nombre Nuevo nombre del usuario
	 */
	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	/**
	 * Método encargado de devolver el tipo del usuario
	 * @return Tipo del usuario
	 */
	public String getTipo() {
		return this._tipo;
	}

	/**
	 * Método encargado de modificar el tipo del usuario
	 * @param nombre Nuevo tipo del usuario
	 */
	public void setTipo(String tipo) {
		this._tipo = tipo;
	}
	
}
