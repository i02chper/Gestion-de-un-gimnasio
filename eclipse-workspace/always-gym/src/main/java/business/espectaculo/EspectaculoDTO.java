package business.espectaculo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Clase de objeto abstracta Espectaculo, donde se definen los métodos y variables miembros comunes
 * a todos los objetos concretos de Espectaculo.
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 */

public abstract class EspectaculoDTO {
	
	protected int _id;
	protected String _titulo;
	protected String _descripcion;
	protected String _categoria;
	protected int _localidades;
	protected int _vendidas;
	protected DateTimeFormatter _formato;
	
	/**
	 * Constructor vacío de la clase Espectáculo
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public EspectaculoDTO() {
		this._titulo = "";
		this._descripcion = "";
		this._categoria = "";
		this._vendidas = 0;
		this._formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	}

	/**
	 * Método encargado de devolver el ID del espectáculo
	 * @return ID del espectáculo
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * Método encargado de modificar el ID del espectáculo
	 * @param _id Nuevo ID del espectáculo
	 */
	public void set_id(int nuevo_id) {
		this._id = nuevo_id;
	}

	/**
	 * Método encargado de devolver el título del espectáculo
	 * @return Título del espectáculo
	 */
	public String get_titulo() {
		return _titulo;
	}

	/**
	 * Método encargado de modificar el título del espectáculo
	 * @param nuevo_titulo Nuevo título del espectáculo
	 */
	public void set_titulo(String nuevo_titulo) {
		this._titulo = nuevo_titulo;
	}

	/**
	 * Método encargado de devolver el título del espectáculo
	 * @return Descripción del espectáculo
	 */
	public String get_descripcion() {
		return _descripcion;
	}

	/**
	 * Método encargado de modificar la descripción del espectáculo
	 * @param nueva_descripcion Nueva descripción del espectáculo
	 */
	public void set_descripcion(String nueva_descripcion) {
		this._descripcion = nueva_descripcion;
	}

	/**
	 * Método encargado de devolver la categoría del espectáculo
	 * @return Categoría del espectáculo
	 */
	public String get_categoria() {
		return _categoria;
	}

	/**
	 * Método encargado de modificar la categoría del espectáculo
	 * @param nueva_categoria Nueva categoría del espectáculo
	 */
	public void set_categoria(String nueva_categoria) {
		this._categoria = nueva_categoria;
	}
	
	/**
	 * Método encargado de modificar el número de localidades asignado al espectáculo
	 * @param nuevas_localidades Nuevo número de localidades asignado al espectáculo
	 */
	public void set_localidades(int nuevas_localidades) {
		this._localidades = nuevas_localidades;
	}

	/**
	 * Método encargado de devolver el número de localidades asignado al espectáculo
	 * @return Número de localidades asignado a un espectáculo
	 */
	public int get_localidades() {
		return this._localidades;
	}
	
	/**
	 * Método encargado de devolver el formato de entrada de texto de una fecha + hora
	 * @return Formato de entrada de texto de una fecha + hora
	 */
	public DateTimeFormatter getFormato() {
		return this._formato;
	}
	
	/**
	 * Método encargado de devolver el número de entradas vendidas correspondientes al espectáculo
	 * @return Número de entradas vendidas correspondientes al espectáculo
	 */
	public int get_vendidas() {
		return _vendidas;
	}

	/**
	 * Método encargado de modificar el número de entradas vendidas correspondientes al espectáculo
	 * @param _vendidas Cantidad actualizada del número de entradas vendidas para un espectáculo
	 */
	public void set_vendidas(int nuevas_vendidas) {
		this._vendidas = nuevas_vendidas;
	}
}


