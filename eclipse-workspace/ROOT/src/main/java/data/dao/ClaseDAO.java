package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import business.clase.ClaseDTO;

public class ClaseDAO {
	private Connection con = null;
	private Properties _statements = null;
	
	/**
	 * Constructor parametrizado
	 * @param aplicacion Servlet para poder llamarlo desde el DAO
	 */
	public ClaseDAO(ServletContext aplicacion) {
		
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
	
	public int addClase(ClaseDTO clase) {
		try {
			//Comprobamos que el instructor indicado es correcto
			PreparedStatement get_instructor = con.prepareStatement(this._statements.getProperty("get_instructor"));
			get_instructor.setString(1, clase.get_instructor());
			ResultSet instructor = get_instructor.executeQuery();
			SimpleDateFormat hora_formato = new SimpleDateFormat("HH:mm");
			
			if(!instructor.next())
				return -1;
			
			else if(!instructor.getString(2).equals("instr"))
				return -2;
			
			PreparedStatement add = con.prepareStatement(this._statements.getProperty("aniadir_clase"));
			
			//titulo, descripcion, categoria, capacidad, duracion, ubicacion, instructor
			add.setString(1, clase.get_titulo());
			add.setString(2, clase.get_descripcion());
			add.setString(3, clase.get_categoria());
			add.setInt(4, clase.get_capacidad());
			add.setInt(5, clase.get_duracion());
			add.setString(6, clase.get_ubicacion());
			add.setString(7, instructor.getString(1));
			
			add.executeUpdate();
			
			// Incluimos los horarios
			
			PreparedStatement add_hora = con.prepareStatement(this._statements.getProperty("aniadir_hora"));
			
			for(String dia: clase.get_dias()) {
				
				for(Date hora: clase.get_horas()) {
					String hora_fecha = hora_formato.format(hora);
					String fecha = "01,01,0001 " + hora_fecha;
					
					add_hora.setString(1, dia);
					add_hora.setString(2, fecha);
					add_hora.addBatch();
				}
			}
			
			add_hora.executeBatch();
			
			get_instructor.close();
			add.close();
			add_hora.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -3;
		}
		
		return 0;
	}
	
}
