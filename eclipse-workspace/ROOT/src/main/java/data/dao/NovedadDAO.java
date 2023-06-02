package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.servlet.ServletContext;
import business.novedad.NovedadDTO;

public class NovedadDAO {
	private Connection con = null;
	private Properties _statements = null;
	
	/**
	 * Constructor parametrizado
	 * @param aplicacion Servlet para poder llamarlo desde el DAO
	 */
	public NovedadDAO(ServletContext aplicacion) {
		
		/* El fichero 'properties' pasado aqui es el fichero 'RutaConsultas' del loginController */
		DBConnection db = new DBConnection();
		con = db.conectar();
		
		try {
			this._statements = new Properties();
			this._statements.load(aplicacion.getResourceAsStream(aplicacion.getInitParameter("SQL_statements")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<NovedadDTO> getNovedades(){
		ArrayList<NovedadDTO> novedades = new ArrayList<NovedadDTO>();
		
		try {
			
			PreparedStatement get_novedades = con.prepareStatement(this._statements.getProperty("get_novedades"));
			ResultSet novedades_rs = get_novedades.executeQuery();
			
			//titulo, cuerpo, autor, fecha FROM NOVEDAD
			while(novedades_rs.next()) {
				NovedadDTO novedad = new NovedadDTO(novedades_rs.getString(2), novedades_rs.getString(3), novedades_rs.getString(4), novedades_rs.getTimestamp(5));
				novedad.set_id(novedades_rs.getInt(1));
				novedades.add(novedad);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return novedades;
	}
	
	public boolean addNovedad(NovedadDTO novedad) {
		
		try {
			
			PreparedStatement add_novedad = con.prepareStatement(this._statements.getProperty("add_novedad"));
			
			add_novedad.setString(1, novedad.get_titulo());
			add_novedad.setString(2, novedad.get_cuerpo());
			add_novedad.setString(3, novedad.get_autor());
			
			add_novedad.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public NovedadDTO getNovedad(int id_novedad) {
		NovedadDTO novedad = new NovedadDTO();
		
		try {
			
			PreparedStatement get_novedad = con.prepareStatement(this._statements.getProperty("get_novedad"));
			get_novedad.setInt(1, id_novedad);
			ResultSet novedad_rs = get_novedad.executeQuery();
			
			novedad_rs.next();
			novedad.set_id(id_novedad);
			novedad.set_titulo(novedad_rs.getString(1));
			novedad.set_cuerpo(novedad_rs.getString(2));
			novedad.set_autor(novedad_rs.getString(3));
			novedad.set_fecha_publicacion(novedad_rs.getTimestamp(4));
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return novedad;
	}
	
	public boolean modNovedad(NovedadDTO novedad) {
		
		try {
			
			PreparedStatement mod_novedad = con.prepareStatement(this._statements.getProperty("mod_novedad"));
			
			mod_novedad.setString(1, novedad.get_titulo());
			mod_novedad.setString(2, novedad.get_cuerpo());
			mod_novedad.setInt(3, novedad.get_id());
			
			mod_novedad.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean eliminarNovedad(int id_novedad) {
		
		try {
			
			PreparedStatement mod_novedad = con.prepareStatement(this._statements.getProperty("eliminar_novedad"));
			mod_novedad.setInt(1, id_novedad);
			
			mod_novedad.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
