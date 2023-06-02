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
}
