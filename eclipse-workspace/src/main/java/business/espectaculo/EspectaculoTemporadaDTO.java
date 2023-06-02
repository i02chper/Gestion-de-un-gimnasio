package business.espectaculo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Producto concreto de la clase Espectaculo que representa un espectáculo que se repite periódicamente
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 */
public class EspectaculoTemporadaDTO extends EspectaculoDTO{

	private String _dia; //
	private LocalDateTime _inicio;
	private LocalDateTime _fin;
	
	/**
 	 * Constructor de la clase (por defecto)
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */
	public EspectaculoTemporadaDTO() {
		super();
		this._inicio = null;
		this._fin = null;
		this._dia = "";
	}
	
	/**
 	 * Método encargado de modificar la fecha de inicio del espectáculo
	 * @param inicio Nueva fecha de inicio para el espectáculo (tipo String)
     */
	public void set_inicio(String inicio) {
		this._inicio = LocalDateTime.parse(inicio, this._formato);
	}
	
	/**
 	 * Método encargado de modificar la fecha de inicio del espectáculo
	 * @param inicio Nueva fecha de inicio para el espectáculo (tipo LocalDateTime)
     */
	public void set_inicio(LocalDateTime inicio) {
		this._inicio = inicio;
	}
	
	/**
 	 * Método encargado de modificar la fecha de fin del espectáculo
	 * @param fin Nueva fecha de inicio para el espectáculo (tipo String)
     */
	public void set_fin(String fin) {
		this._fin = LocalDateTime.parse(fin, this._formato);
	}
	
	/**
 	 * Método encargado de modificar la fecha de fin del espectáculo
	 * @param fin Nueva fecha de inicio para el espectáculo (tipo LocalDateTime)
     */
	public void set_fin(LocalDateTime fin) {
		this._fin = fin;
	}
	
	/**
 	 * Método encargado de modificar el día de la semana en que se realiza el espectáculo
	 * @param día Nueva fecha de inicio para el espectáculo (tipo String)
     */
	public void set_dia(String dia) {
		this._dia = dia;
	}
	
	/**
 	 * Método encargado de devolver la fecha de inicio del espectáculo
	 * @return Cadena con la fecha de inicio
     */
	public String get_inicio() {
		return this._inicio.format(this._formato);
	}
	
	/**
 	 * Método encargado de devolver la fecha de fin del espectáculo
	 * @return Cadena con la fecha de fin
     */
	public String get_fin() {
		return this._fin.format(this._formato);
	}
	
	/**
 	 * Método encargado de devolver el día de la semana en que se realiza el espectáculo
	 * @return Cadena con el día de la semana
     */
	public String get_dia() {
		return this._dia;
	}
}
