package business.espectaculo;

/**
 * Clase abstracta del patrón de diseño factory para la creación de objetos Espectaculo
 * @author Javier Molina
 * @author Fernando Lucena
 * @author Diego Gómez
 * @version 16/11/2021
 */

public abstract class CreadorEspectaculo {

	/**
	 * Creador de un objeto concreto de tipo EspectaculoTemporada
	 * @return Devuelve el objeto creado de tipo EspectaculoTemporada
	 */
	public abstract EspectaculoTemporadaDTO crearEspectaculoTemporada();
	
	/**
	 * Creador de un objeto concreto de tipo EspectaculoPuntual
	 * @return Devuelve el objeto creado de tipo EspectaculoPuntual
	 */
	public abstract EspectaculoPuntualDTO crearEspectaculoPuntual();
	
	
	/**
	 * Creador de un objeto concreto de tipo EspectaculoMultiple
	 * @return Devuelve el objeto creado de tipo EspectaculoMultiple
	 */
	public abstract EspectaculoMultipleDTO crearEspectaculoMultiple();
	
}
