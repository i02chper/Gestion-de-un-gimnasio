package business.espectaculo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Producto concreto de la clase Espectaculo que representa un espectáculo con una única fecha
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 */
public class EspectaculoPuntualDTO extends EspectaculoDTO{
	private LocalDateTime _fechaHora;
	
	/**
 	 * Constructor de la clase (por defecto)
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */
	public EspectaculoPuntualDTO() {
		super();
		this._fechaHora = null;
	}
	
	/**
 	 * Constructor de la clase a partir de una fecha
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */
	public EspectaculoPuntualDTO(String fecha) throws FileNotFoundException, IOException {
		super();
		this._fechaHora = LocalDateTime.parse(fecha, this._formato);
	}

	/**
 	 * Método encargado de devolver la fecha y hora del espectáculo
	 * @return Cadena con la fecha y hora
     */
	public String getFechaHora() {
		return _fechaHora.format(this._formato); // dd/MM/yyyy HH:mm
	}

	/**
 	 * Método encargado de modificar la fecha y hora del espectáculo
	 * @param fecha Nueva fecha para el espectáculo (tipo String)
     */
	public void setFechaHora(String fecha) {
		this._fechaHora = LocalDateTime.parse(fecha, this._formato);
	}
	
	/**
 	 * Método encargado de modificar la fecha y hora del espectáculo
	 * @param fecha Nueva fecha para el espectáculo (tipo LocalDateTime)
     */
	public void setFechaHora(LocalDateTime fecha) {
		this._fechaHora = fecha;
	}
	
}
