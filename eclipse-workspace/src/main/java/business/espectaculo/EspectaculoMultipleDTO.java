package business.espectaculo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Producto concreto de la clase Espectaculo que representa un espectáculo con varias fechas
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 * */
public class EspectaculoMultipleDTO extends EspectaculoDTO {

	private ArrayList<LocalDateTime> _fechasHoras;
	
	/**
 	 * Constructor de la clase (por defecto)
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */
	public EspectaculoMultipleDTO() {
		super();
		this._fechasHoras = new ArrayList<LocalDateTime>();
	}
	
	/**
 	 * Método encargado de añadir una nueva fecha (de tipo String) a la lista de fechas del espectáculo
     * @param nueva_fecha Fecha que vamos a aniadir
     */
	public void addFechaHora(String nueva_fecha) {
		LocalDateTime fecha = LocalDateTime.parse(nueva_fecha, this._formato);
		
		this._fechasHoras.add(fecha);
	}
	
	/**
 	 * Método encargado de añadir una nueva fecha (de tipo String) a la lista de fechas del espectáculo
     * @param nueva_fecha Fecha que vamos a aniadir
     */
	public void addFechaHora(LocalDateTime nueva_fecha) {		
		this._fechasHoras.add(nueva_fecha);
	}
	
	/**
 	 * Método encargado de devolver la lista con todas las fechas y horas del espectáculo
	 * @return Lista con todas las fechas y horas
     */
	public ArrayList<LocalDateTime> getFechasHoras(){
		return this._fechasHoras;
	}
	
}
