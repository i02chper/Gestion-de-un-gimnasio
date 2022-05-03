package business.espectaculo;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase concreta que patrón de diseño factory para la creación de objetos Espectaculo
 * @author Javier Molina
 * @author Fernando Lucena
 * @author Diego Gómez
 * @version 16/11/2021
 */
public class EspectaculoConcreto extends CreadorEspectaculo{

	/**
	 * Sobrecarga del creador de un objeto concreto de tipo EspectaculoTemporada
	 * @return Devuelve el objeto creado de tipo EspectaculoTemporada
	 */
	@Override
	public EspectaculoTemporadaDTO crearEspectaculoTemporada() {
		EspectaculoTemporadaDTO espectaculo = new EspectaculoTemporadaDTO();
		
		return espectaculo;
	}
	
	/**
	 * Sobrecarga del creador de un objeto concreto de tipo EspectaculoPuntual
	 * @return Devuelve el objeto creado de tipo EspectaculoPuntual
	 */
	@Override
	public EspectaculoPuntualDTO crearEspectaculoPuntual() {
		EspectaculoPuntualDTO espectaculo = new EspectaculoPuntualDTO();
		
		return espectaculo;
	}
	
	/**
	 * Sobrecarga del creador de un objeto concreto de tipo EspectaculoMultiple
	 * @return Devuelve el objeto creado de tipo EspectaculoMultiple
	 */
	@Override
	public EspectaculoMultipleDTO crearEspectaculoMultiple() {
		EspectaculoMultipleDTO espectaculo = new EspectaculoMultipleDTO();
		
		return espectaculo;
	}
	
}
