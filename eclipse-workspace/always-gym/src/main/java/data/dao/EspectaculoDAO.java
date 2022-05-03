package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import javax.servlet.ServletContext;

import java.sql.Statement;

import business.espectaculo.*;

/**
 * Clase que representa el acceso a los datos de un espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 *
 */
public class EspectaculoDAO {
	
	//Defines
	private static final int ANIADIR = 1;
	private static final int MODIFICAR = 2;
	private static final int TODOS = 1;
	private static final int RESENIABLES = 2;
	
	private Connection con = null;
	private Properties _statements = null;
	
	/**
	 * Constructor parametrizado
	 * @param aplicacion Servlet para poder llamarlo desde el DAO
	 */
	public EspectaculoDAO(ServletContext aplicacion) {
		
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
	 * Metodo que se encarga de aniadir y modificar los espectaculos puntuales
	 * @param con Conexion con la base de datos
	 * @param EspectaculoPuntualDTO Espectaculo puntual que vamos a aniadir o modificar
	 * @param accion Permite elegir si quieres aniadir o modificar (ANIADIR, MODIFICAR)
	 * @return Devuelve true si se aniade o modifica con exito o false si falla
	 */
	public boolean AddEspectaculoPuntual(EspectaculoPuntualDTO espectaculo, Integer accion) {
		
		try {
			PreparedStatement aniadir = null;
			PreparedStatement aniadir_puntual = null;
			
			if(accion == ANIADIR) {
				aniadir = con.prepareStatement(this._statements.getProperty("aniadir"));
				aniadir_puntual = con.prepareStatement(this._statements.getProperty("aniadir_puntual"));
			}
			else if(accion == MODIFICAR) {
				aniadir = con.prepareStatement(this._statements.getProperty("modificar"));
				aniadir_puntual = con.prepareStatement(this._statements.getProperty("modificar_puntual"));
			}
						
			// titulo, descripcion, localidades, vendidas, categoria
			aniadir.setString(1, espectaculo.get_titulo());
			aniadir.setString(2, espectaculo.get_descripcion());
			aniadir.setInt(3, espectaculo.get_localidades());
			aniadir.setInt(4, espectaculo.get_vendidas());
			aniadir.setString(5, espectaculo.get_categoria());
			aniadir.setString(6, "puntual");
			aniadir_puntual.setString(1, espectaculo.getFechaHora());
			
			if(accion == MODIFICAR) {
				aniadir.setInt(6, espectaculo.get_id());
				aniadir_puntual.setInt(2, espectaculo.get_id());
			}
			
			if(aniadir.executeUpdate() == 0)
				return false;
			
			if(aniadir_puntual.executeUpdate() == 0)
				return false;
			
			return true;
			
		} catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Metodo que se encarga de aniadir y modificar los espectaculos temporada
	 * @param con Conexion con la base de datos
	 * @param EspectaculoTemporadaDTO Espectaculo de temporada que vamos a aniadir o modificar
	 * @param accion Permite elegir si quieres aniadir o modificar (ANIADIR, MODIFICAR)
	 * @return Devuelve true si se aniade o modifica con exito o false si falla
	 */
	public boolean AddEspectaculoTemporada(EspectaculoTemporadaDTO espectaculo, Integer accion) {
		
		try {
			PreparedStatement aniadir = null;
			PreparedStatement aniadir_temporada = null;
			
			if(accion == ANIADIR) {
				aniadir = con.prepareStatement(this._statements.getProperty("aniadir"));
				aniadir_temporada = con.prepareStatement(this._statements.getProperty("aniadir_temporada"));
			}
			else if(accion == MODIFICAR) {
				aniadir = con.prepareStatement(this._statements.getProperty("modificar"));
				aniadir_temporada = con.prepareStatement(this._statements.getProperty("modificar_temporada"));
			}
						
			// titulo, descripcion, localidades, vendidas, categoria
			aniadir.setString(1, espectaculo.get_titulo());
			aniadir.setString(2, espectaculo.get_descripcion());
			aniadir.setInt(3, espectaculo.get_localidades());
			aniadir.setInt(4, espectaculo.get_vendidas());
			aniadir.setString(5, espectaculo.get_categoria());
			aniadir.setString(6, "temporada");
			aniadir_temporada.setString(1, espectaculo.get_inicio());
			aniadir_temporada.setString(2, espectaculo.get_fin());
			aniadir_temporada.setString(3, espectaculo.get_dia());
			
			if(accion == MODIFICAR) {
				aniadir.setInt(6, espectaculo.get_id());
				aniadir_temporada.setInt(4, espectaculo.get_id());
			}
			
			if(aniadir.executeUpdate() == 0)
				return false;
			
			if(aniadir_temporada.executeUpdate() == 0)
				return false;
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo que se encarga de aniadir y modificar los espectaculos multiples
	 * @param con Conexion con la base de datos
	 * @param EspectaculoTemporadaDTO Espectaculo multiple que vamos a aniadir o modificar
	 * @param accion Permite elegir si quieres aniadir o modificar (ANIADIR, MODIFICAR)
	 * @return Devuelve true si se aniade o modifica con exito o false si falla
	 */
	public boolean AddEspectaculoMultiple(EspectaculoMultipleDTO espectaculo, Integer accion) {
		try {
			PreparedStatement aniadir = null;
			PreparedStatement aniadir_multiple = null;
			
			if(accion == ANIADIR) {
				aniadir = con.prepareStatement(this._statements.getProperty("aniadir"));
				aniadir_multiple = con.prepareStatement(this._statements.getProperty("aniadir_multiple"));
			}
			else if(accion == MODIFICAR) {
				aniadir = con.prepareStatement(this._statements.getProperty("modificar"));
				aniadir_multiple = con.prepareStatement(this._statements.getProperty("modificar_multiple"));
			}
			
			String fechas = "";
						
			// titulo, descripcion, localidades, vendidas, categoria
			aniadir.setString(1, espectaculo.get_titulo());
			aniadir.setString(2, espectaculo.get_descripcion());
			aniadir.setInt(3, espectaculo.get_localidades());
			aniadir.setInt(4, espectaculo.get_vendidas());
			aniadir.setString(5, espectaculo.get_categoria());
			aniadir.setString(6, "multiple");
			
			ArrayList<LocalDateTime> fechas_ldt = espectaculo.getFechasHoras();
			
			for(LocalDateTime fecha: fechas_ldt) {
				fechas += fecha.format(espectaculo.getFormato()) + ",";
			}
			fechas = fechas.substring(0, fechas.length() - 1);
			
			aniadir_multiple.setString(1, fechas);
			
			if(accion == MODIFICAR) {
				aniadir.setInt(6, espectaculo.get_id());
				aniadir_multiple.setInt(2, espectaculo.get_id());
			}
			
			if(aniadir.executeUpdate() == 0)
				return false;
			
			if(aniadir_multiple.executeUpdate() == 0)
				return false;
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos puntuales
	 * @param accion Permite elegir si queremos obtener todos los espectáculos puntuales o solo los reseñables
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoPuntualDTO> getPuntuales(Integer accion){
		ArrayList<EspectaculoPuntualDTO> espectaculos = new ArrayList<EspectaculoPuntualDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			Statement get_espectaculos = con.createStatement();
			ResultSet puntuales = get_espectaculos.executeQuery(this._statements.getProperty("get_puntuales"));
			
			while(puntuales.next()) {
				EspectaculoPuntualDTO espectaculo = ec.crearEspectaculoPuntual();
				
				if(accion == TODOS || ( accion == RESENIABLES && puntuales.getTimestamp(6).toLocalDateTime().isBefore(LocalDateTime.now() ) )) {
				
					// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, p.fecha 
					espectaculo.set_titulo(puntuales.getString(1));
					espectaculo.set_descripcion(puntuales.getString(2));
					espectaculo.set_localidades(puntuales.getInt(3));
					espectaculo.set_vendidas(puntuales.getInt(4));
					espectaculo.set_categoria(puntuales.getString(5));
					espectaculo.setFechaHora(puntuales.getTimestamp(6).toLocalDateTime());
					espectaculo.set_id(puntuales.getInt(7));
					
					espectaculos.add(espectaculo);
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos puntuales
	 * @param ids ArrayList con los IDs de los espectáculos que queremos obtener
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoPuntualDTO> getPuntuales(ArrayList<Integer> ids){
		ArrayList<EspectaculoPuntualDTO> espectaculos = new ArrayList<EspectaculoPuntualDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			String sentencia_sql = "SELECT e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, p.fecha FROM Espectaculo e, Puntual p WHERE e.id = p.id AND e.id IN (";
			
			for(Integer id: ids) 
				sentencia_sql += id + ", ";
			
			sentencia_sql += ids.get(0) + ")";
			
			Statement buscar = con.createStatement();
			ResultSet puntuales = buscar.executeQuery(sentencia_sql);
			
			while(puntuales.next()) {
				EspectaculoPuntualDTO espectaculo = ec.crearEspectaculoPuntual();
				
				// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, p.fecha 
				espectaculo.set_titulo(puntuales.getString(1));
				espectaculo.set_descripcion(puntuales.getString(2));
				espectaculo.set_localidades(puntuales.getInt(3));
				espectaculo.set_vendidas(puntuales.getInt(4));
				espectaculo.set_categoria(puntuales.getString(5));
				espectaculo.setFechaHora(puntuales.getTimestamp(6).toLocalDateTime());
				
				espectaculos.add(espectaculo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos de temporada
	 * @param accion Permite elegir si queremos obtener todos los espectáculos de temporada o solo los reseñables
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoTemporadaDTO> getTemporada(Integer accion){
		ArrayList<EspectaculoTemporadaDTO> espectaculos = new ArrayList<EspectaculoTemporadaDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			Statement get_espectaculos = con.createStatement();
			ResultSet temporadas = get_espectaculos.executeQuery(this._statements.getProperty("get_temporada"));
			
			while(temporadas.next()) {
				EspectaculoTemporadaDTO espectaculo = ec.crearEspectaculoTemporada();
				
				if(accion == TODOS || ( accion == RESENIABLES && temporadas.getTimestamp(7).toLocalDateTime().isBefore(LocalDateTime.now()) ) ) {
					// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, t.dia, t.inicio, t.final, e.id
					espectaculo.set_titulo(temporadas.getString(1));
					espectaculo.set_descripcion(temporadas.getString(2));
					espectaculo.set_localidades(temporadas.getInt(3));
					espectaculo.set_vendidas(temporadas.getInt(4));
					espectaculo.set_categoria(temporadas.getString(5));
					espectaculo.set_dia(temporadas.getString(6));
					espectaculo.set_inicio(temporadas.getTimestamp(7).toLocalDateTime());
					espectaculo.set_fin(temporadas.getTimestamp(8).toLocalDateTime());
					espectaculo.set_id(temporadas.getInt(9));
						
					espectaculos.add(espectaculo);
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos de temporada
	 * @param ids ArrayList con los IDs de los espectáculos que queremos obtener
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoTemporadaDTO> getTemporada(ArrayList<Integer> ids){
		ArrayList<EspectaculoTemporadaDTO> espectaculos = new ArrayList<EspectaculoTemporadaDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			String sentencia_sql = "SELECT e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, t.dia, t.inicio, t.final FROM Espectaculo e, Temporada t WHERE e.id = t.id AND e.id IN (";
			
			for(Integer id: ids)
				sentencia_sql += id + ", ";
			
			sentencia_sql += ids.get(0) + ")";
			
			Statement buscar = con.createStatement();
			ResultSet temporadas = buscar.executeQuery(sentencia_sql);
			
			while(temporadas.next()) {
				EspectaculoTemporadaDTO espectaculo = ec.crearEspectaculoTemporada();
				
				// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, t.dia, t.inicio, t.final
				espectaculo.set_titulo(temporadas.getString(1));
				espectaculo.set_descripcion(temporadas.getString(2));
				espectaculo.set_localidades(temporadas.getInt(3));
				espectaculo.set_vendidas(temporadas.getInt(4));
				espectaculo.set_categoria(temporadas.getString(5));
				espectaculo.set_dia(temporadas.getString(6));
				espectaculo.set_inicio(temporadas.getTimestamp(7).toLocalDateTime());
				espectaculo.set_fin(temporadas.getTimestamp(8).toLocalDateTime());
				
				espectaculos.add(espectaculo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos múltiples
	 * @param accion Permite elegir si queremos obtener todos los espectáculos múltiples o solo los reseñables
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoMultipleDTO> getMultiples(Integer accion){
		ArrayList<EspectaculoMultipleDTO> espectaculos = new ArrayList<EspectaculoMultipleDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			Statement get_espectaculos = con.createStatement();
			ResultSet multiples = get_espectaculos.executeQuery(this._statements.getProperty("get_multiples"));
			Boolean reseniable = false;
			
			while(multiples.next()) {
				EspectaculoMultipleDTO espectaculo = ec.crearEspectaculoMultiple();
				String fechas[] = multiples.getString(6).split(",");
				
				// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, m.fechas, e.id
				espectaculo.set_titulo(multiples.getString(1));
				espectaculo.set_descripcion(multiples.getString(2));
				espectaculo.set_localidades(multiples.getInt(3));
				espectaculo.set_vendidas(multiples.getInt(4));
				espectaculo.set_categoria(multiples.getString(5));
				espectaculo.set_id(multiples.getInt(7));
				
				for(String fecha: fechas) {
					//Comprobamos si la sesión ya ha pasado
					if(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).isBefore(LocalDateTime.now()))
						reseniable = true;
					
					espectaculo.addFechaHora(fecha);
				}
				
				if(accion == RESENIABLES) {
					if(reseniable)
						espectaculos.add(espectaculo);
				}
				else
					espectaculos.add(espectaculo);
				
				reseniable = false;
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de obtener los espectaculos múltiples
	 * @param ids ArrayList con los IDs de los espectáculos que queremos obtener
	 * @return Devuelve un ArrayList con los espectáculos pedidos
	 */
	public ArrayList<EspectaculoMultipleDTO> getMultiples(ArrayList<Integer> ids){
		ArrayList<EspectaculoMultipleDTO> espectaculos = new ArrayList<EspectaculoMultipleDTO>();
		
		try {
			EspectaculoConcreto ec = new EspectaculoConcreto();
			String sentencia_sql = "SELECT e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, m.fechas FROM Espectaculo e, Multiple m WHERE e.id = m.id AND e.id IN (";
			
			for(Integer id: ids) 
				sentencia_sql += id + ", ";
			
			sentencia_sql += ids.get(0) + ")";
			
			Statement buscar = con.createStatement();
			ResultSet multiples = buscar.executeQuery(sentencia_sql);
			
			while(multiples.next()) {
				EspectaculoMultipleDTO espectaculo = ec.crearEspectaculoMultiple();
				String fechas[] = multiples.getString(6).split(",");
				
				// e.titulo, e.descripcion, e.localidades, e. vendidas, e.categoria, m.fechas
				espectaculo.set_titulo(multiples.getString(1));
				espectaculo.set_descripcion(multiples.getString(2));
				espectaculo.set_localidades(multiples.getInt(3));
				espectaculo.set_vendidas(multiples.getInt(4));
				espectaculo.set_categoria(multiples.getString(5));
				
				for(String fecha: fechas)
					espectaculo.addFechaHora(fecha);
				
				espectaculos.add(espectaculo);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return espectaculos;
	}
	
	/**
	 * Método que se encarga de modificar la sesión de un espectáculo puntual
	 * @param id ID del espectáculo que queremos modificar su sesión
	 * @param nueva_fecha Nueva fecha que queremos asignar al espectáculo
	 * @return Devuelve true si se ha modificado correctamente o false en caso contrario
	 */
	public boolean modificarSesionPuntual(Integer id, String nueva_fecha) {
		
		try {			
			PreparedStatement set_fecha = con.prepareStatement(this._statements.getProperty("set_fecha_puntual"));
			set_fecha.setString(1, nueva_fecha.replace('T', ' '));
			set_fecha.setInt(2, id);
			
			set_fecha.executeUpdate();
			
			set_fecha.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método que se encarga de modificar el intervalo de un espectáculo de temporada
	 * @param id ID del espectáculo que queremos modificar su sesión
	 * @param dia Dia de la semana que queremos asignar al espectáculo
	 * @param inicio Nueva fecha de inicio que queremos asignar al espectáculo
	 * @param fin Nueva fecha de fin que queremos asignar al espectáculo
	 * @return Devuelve true si se ha modificado correctamente o false en caso contrario
	 */
	public boolean modificarTemporada(Integer id, String dia, String inicio, String fin) {
		
		try {			
			PreparedStatement set_fecha = con.prepareStatement(this._statements.getProperty("set_temporada"));
			
			set_fecha.setString(1, dia);
			set_fecha.setString(2, inicio.concat(" 00:00"));
			set_fecha.setString(3, fin.concat(" 00:00"));
			set_fecha.setInt(4, id);
			
			if(set_fecha.executeUpdate() == 0)
				return false;
			
			set_fecha.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * Método que se encarga de modificar la sesión de un espectáculo múltiple
	 * @param id ID del espectáculo que queremos modificar su sesión
	 * @param fechas_array Array con las fechas nuevas para ese espectáculo
	 * @return Devuelve true si se ha modificado correctamente o false en caso contrario
	 */
	public boolean modificarMultiples(Integer id, String[] fechas_array) {
		String fechas = "";
		
		try {
			PreparedStatement set_fecha = con.prepareStatement(this._statements.getProperty("set_multiples"));
					
			for(String fecha: fechas_array)
				fechas += fecha.replace('T', ' ') + ",";
			
			set_fecha.setString(1, fechas.substring(0, fechas.length()-1));
			set_fecha.setInt(2, id);
			
			set_fecha.executeUpdate();
			
			set_fecha.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método que se encarga de buscar un espectáculo por su título o categoría
	 * @param título Título del espectáculo que deseamos buscar
	 * @param categoría Categoría del espectáculo que deseamos buscar
	 * @return Devuelve un HashTable que tiene como clave la ID del espectáculo y como valor el tipo
	 */
	public Hashtable<Integer, String> buscarEspectaculos(String titulo, String categoria){
		Hashtable<Integer, String> coincidentes = new Hashtable<Integer, String>();
		
		try {
			PreparedStatement buscar = con.prepareStatement(this._statements.getProperty("buscar"));
			buscar.setString(1, titulo);
			buscar.setString(2, categoria);
			
			ResultSet espectaculos = buscar.executeQuery();
			
			while(espectaculos.next())
				coincidentes.put(espectaculos.getInt(1), espectaculos.getString(2));
			
			buscar.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return coincidentes;
	}
}









