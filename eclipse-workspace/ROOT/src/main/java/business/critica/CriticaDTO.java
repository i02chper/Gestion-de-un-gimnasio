package business.critica;

import java.util.ArrayList;

/**
 * Clase que representa una cr�tica a un espect�culo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 */
public class CriticaDTO {
	
	private int _id; 
	private String _titulo;
	private int _puntuacion;
	private String _resenia;
	private ArrayList<Integer> _valoraciones;
	private String _autor;
	private int _espectaculo_id;
	private String _espectaculo_titulo;
	
	/**
	 * Constructor vac�o (por defecto)
	 */
	public CriticaDTO() {
		this._id = -1;
		this._titulo = "";
		this._resenia = "";
		this._valoraciones = new ArrayList<Integer>();
		this._espectaculo_titulo = "";
	}
	
	/**
	 * Constructor parametrizado
	 * @param titulo T�tulo de la cr�tica
	 * @param puntuacion Puntuaci�n asignada a la cr�tica
	 * @param resenia Rese�a de la cr�tica
	 */
	public CriticaDTO(Integer espectaculo, String titulo, Integer puntuacion, String resenia, String autor) {
		
		this._espectaculo_id = espectaculo;
		this._titulo = titulo;
		this._puntuacion = puntuacion;
		this._resenia = resenia;
		this._valoraciones = new ArrayList<Integer>();
		this._autor = autor;
	}

	/**
	 * M�todo encargado de devolver el ID de la cr�tica
	 * @return ID asociado a la crtica
	 */
	public Integer get_id() {
		return this._id;
	}
	
	/**
	 * M�todo encargado de modificar el ID de la cr�tica
	 * @param nuevo_id Nuevo ID de la cr�tica
	 */
	public void set_id(Integer nuevo_id) {
		this._id = nuevo_id;
	}
	
	/**
	 * M�todo encargado de devolver el t�tulo de la cr�tica
	 * @return T�tulo de la cr�tica
	 */
	public String get_titulo() {
		return _titulo;
	}

	/**
	 * M�todo encargado de modificar el t�tulo de la cr�tica
	 * @param _titulo Nuevo t�tulo de la cr�tica
	 */
	public void set_titulo(String _titulo) {
		this._titulo = _titulo;
	}

	/**
	 * M�todo encargado de devolver la puntuaci�n asignada a la cr�tica
	 * @return Puntuaci�n asignada a la cr�tica
	 */
	public int get_puntuacion() {
		return _puntuacion;
	}

	/**
	 * M�todo encargado de modificar la puntuaci�n de la cr�tica
	 * @param _puntuacion Nueva puntuaci�n asignada a la cr�tica
	 */
	public void set_puntuacion(int _puntuacion) {
		this._puntuacion = _puntuacion;
	}

	/**
	 * M�todo encargado de devolver la rese�a de la cr�tica
	 * @return Rese�a de la cr�tica
	 */
	public String get_resenia() {
		return _resenia;
	}
	
	/**
	 * M�todo encargado de devolver el autor de la cr�tica
	 * @return Autor de la cr�tica
	 */
	public String get_autor() {
		return _autor;
	}

	/**
	 * M�todo encargado de modificar la rese�a de la cr�tica
	 * @param resenia Nueva rese�a de la cr�tica
	 */
	public void set_resenia(String resenia) {
		this._resenia = resenia;
	}

	/**
	 * M�todo encargado de devolver las valoraciones asignadas a la cr�tica
	 * @return Lista con todas las valoraciones asignadas a la cr�tica
	 */
	public Float get_valoracion() {
		Float vals = (float) 0;
		
		for(Integer valoracion: this._valoraciones)
			vals += valoracion;
		
		if(this._valoraciones.isEmpty()) 
			return (float) -1;
		
		else
			return (Float) vals/ (float) this._valoraciones.size();
	}

	/**
	 * M�todo encargado de a�adir una nueva valoraci�n a la lista de valoraciones de la cr�tica
	 * @param valoracion Nueva valoraci�n asignada a la cr�tica
	 */
	public void add_valoracion(Integer valoracion) {
		this._valoraciones.add(valoracion);
	}
	
	/**
	 * M�todo encargado de modificar el autor de la cr�tica
	 * @param autor Nuevo autor de la cr�tica
	 */
	public void set_autor(String autor) {
		this._autor = autor;
	}
	
	/**
	 * Método encargado de modificar el espectáculo asociado a una crítica
	 * @param espectaculo
	 */
	public void set_espectaculo(int espectaculo) {
		this._espectaculo_id = espectaculo;
	}
	
	/**
	 * Método encargado de devolver el espectáculo asociado a una crítica
	 * @param espectaculo
	 */
	public int get_espectaculo() {
		return this._espectaculo_id;
	}
	
	/**
	 * Método encargado de modificar el espectáculo asociado a una crítica
	 * @param espectaculo
	 */
	public void set_espectaculo(String espectaculo) {
		this._espectaculo_titulo = espectaculo;
	}
	
	/**
	 * Método encargado de devolver el espectáculo asociado a una crítica
	 * @param espectaculo
	 */
	public String get_espectaculo_titulo() {
		return this._espectaculo_titulo;
	}
	
}