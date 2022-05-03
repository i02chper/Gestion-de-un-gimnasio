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
public class CustomerBean implements java.io.Serializable{
	
	private String nombre;
	private String usuario;
	private String tipo;
	
	/**
	 * Constructor vacío (por defecto)
	 */
	public CustomerBean() {
		this.nombre = "";
		this.tipo = "";
	}

	/**
	 * Método encargado de devolver el nick del usuario
	 * @return Nick del usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Método encargado de modificar el nick del usuario
	 * @param usuario Nuevo nick del usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método encargado de devolver el nombre del usuario
	 * @return Nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método encargado de modificar el nombre del usuario
	 * @param nombre Nuevo nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método encargado de devolver el tipo del usuario
	 * @return Tipo del usuario
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método encargado de modificar el tipo del usuario
	 * @param nombre Nuevo tipo del usuario
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
