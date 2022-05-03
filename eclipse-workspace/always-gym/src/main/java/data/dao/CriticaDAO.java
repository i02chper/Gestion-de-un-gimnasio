package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletContext;


import business.critica.CriticaDTO;

/**
 * Clase que representa el acceso a los datos de una crítica
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 *
 */
public class CriticaDAO {		
		private Connection con = null;
		private Properties _statements = null;
		
		/**
		 * Constructor parametrizado
		 * @param aplicacion Servlet para poder llamarlo desde el DAO
		 */
		public CriticaDAO(ServletContext aplicacion) {
			
			/* El fichero 'properties' pasado aqui es el fichero 'RutaConsultas' del loginController */
			DBConnection db = new DBConnection();
			con = db.conectar();
			
			try {
				this._statements = new Properties();
				this._statements.load(aplicacion.getResourceAsStream(aplicacion.getInitParameter("RutaConsultas")));
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		/**
		 * Método encargado de añadir una nueva crítica
		 * @return Devuelve true si se ha añadido correctamente o false en caso contrario
		 */
		public Boolean addCritica(Integer id_espectaculo, String titulo, String resenia, Integer puntuacion, String autor) {
			
			try {				
				//titulo, puntuacion, resenia, autor, espectaculo
				PreparedStatement add_critica = con.prepareStatement(this._statements.getProperty("add_critica"));
				add_critica.setString(1, titulo);
				add_critica.setInt(2, puntuacion);
				add_critica.setString(3, resenia);
				add_critica.setString(4, autor);
				add_critica.setInt(5, id_espectaculo);
				
				add_critica.executeUpdate();
				
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		/**
		 * Metodo encargado de devolver las críticas de un determinado espectáculo
		 * @param espectaculo ID del espectáculo que queremos extraer sus críticas
		 * @return Devuelve un ArrayList que contiene las críticas
		 */
		public ArrayList<CriticaDTO> getCriticas(Integer espectaculo){
			ArrayList<CriticaDTO> criticas = new ArrayList<CriticaDTO>();
			String[] valoraciones;
			
			try {
				PreparedStatement get_criticas = con.prepareStatement(this._statements.getProperty("get_criticas"));
				get_criticas.setInt(1, espectaculo);
				
				ResultSet criticas_rs = get_criticas.executeQuery();
				
				//espectaculo, autor, titulo, resenia, puntuacion, valoraciones
				while(criticas_rs.next()) {
					CriticaDTO critica = new CriticaDTO();
					valoraciones = criticas_rs.getString(6).split(",");
					
					critica.set_espectaculo(criticas_rs.getString(1));
					critica.set_autor(criticas_rs.getString(2));
					critica.set_titulo(criticas_rs.getString(3));
					critica.set_resenia(criticas_rs.getString(4));
					critica.set_puntuacion(criticas_rs.getInt(5));
					critica.set_id(criticas_rs.getInt(7));
					
					for(String valoracion: valoraciones)
						if(!valoracion.isEmpty()) critica.add_valoracion(Integer.parseInt(valoracion));
					
					criticas.add(critica);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			return criticas;
		}
		
		/**
		 * Método encargado eliminar una crítica
		 * @param id_critica ID de la crítica que queremos eliminar
		 * @return Devuelve true si se ha eliminado correctamente o false en caso contrario
		 */
		public Boolean eliminarCritica(Integer id_critica) {
			
			try {
				PreparedStatement eliminar = con.prepareStatement(this._statements.getProperty("eliminar_critica"));
				eliminar.setInt(1, id_critica);
				
				eliminar.executeUpdate();
				
				eliminar.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		/**
		 * Método encargado de valorar una determinada crítica
		 * @param id_critica ID de la crítica que queremos valorar
		 * param valoracion Valoración que deseamos dar a esa crítica
		 * @return Devuelve true si se ha valorado correctamente o false en caso contrario
		 */
		public Boolean valorar(Integer id_critica, Integer valoracion) {
			
			try {
				PreparedStatement get_valoraciones = con.prepareStatement(this._statements.getProperty("get_valoraciones"));
				get_valoraciones.setInt(1, id_critica);
				ResultSet valoraciones = get_valoraciones.executeQuery();
				String valoraciones_str = "";
				
				PreparedStatement valorar = con.prepareStatement(this._statements.getProperty("valorar_vacio"));
				valoraciones.next();
				
				if(valoraciones.getString(1).isEmpty())
					valoraciones_str = "" + valoracion; 
					
				else
					valoraciones_str = "" + valoraciones.getString(1) + "," + valoracion;
				
				valorar.setString(1, valoraciones_str);
				valorar.setInt(2, id_critica);
				
				valorar.executeUpdate();
				
				valorar.close();
				
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		/**
		 * Método encargado de obtener el número total de críticas de unos espectáculos dados
		 * @param espectaculo Espectáculos de los que queremos obtener el número de críticas
		 * @return Devuelve una HashTable que contiene como clave la ID del espectáculo y como valor el número de críticas que posee
		 */
		public Hashtable<Integer, Integer> getNumeroCriticas(ArrayList<Integer> espectaculos){
			Hashtable<Integer, Integer> criticas = new Hashtable<Integer, Integer>(); 
			
			try {
				//Inicializamos el hashtable a 0
				for(Integer id: espectaculos)
					criticas.put(id, 0);
				
				Statement get_criticas = con.createStatement();
				ResultSet numero_criticas = get_criticas.executeQuery(this._statements.getProperty("numero_criticas"));
				
				//Actualizamos el número de críticas en el hashtable
				while(numero_criticas.next())
					if(criticas.containsKey(numero_criticas.getInt(1)))
						criticas.put(numero_criticas.getInt(1), numero_criticas.getInt(2));
				
				get_criticas.close();
			}
			catch(Exception e){
				e.printStackTrace();
				return null;
			}
			
			return criticas;
		}
}
