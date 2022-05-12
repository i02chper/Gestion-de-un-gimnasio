package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	
	public ArrayList<ClaseDTO> getClases(String usuario){
		ArrayList<ClaseDTO> clases = new ArrayList<ClaseDTO>();
		
		try {
			PreparedStatement get_clases;
			SimpleDateFormat formato_bd = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			
			if(usuario.equals("todos"))
				get_clases = con.prepareStatement(this._statements.getProperty("get_clases_todas"));
			else {
				get_clases = con.prepareStatement(this._statements.getProperty("get_clases_user"));
				get_clases.setString(1, usuario);
			}
			
			ResultSet clases_rs = get_clases.executeQuery();
			
			// Obtenemos las horas
			PreparedStatement get_horas = con.prepareStatement(this._statements.getProperty("get_horas"));
			ResultSet horas_rs = get_horas.executeQuery();
			
			//c.id, c.descripcion, c.ubicacion, c.titulo, c.categoria, c.duracion, c.capacidad
			while(clases_rs.next()) {
				ClaseDTO clase = new ClaseDTO();
				String dia = "";
				ArrayList<String> horas = new ArrayList<String>();
				
				clase.set_id(clases_rs.getInt(1));
				clase.set_descripcion(clases_rs.getString(2));
				clase.set_ubicacion(clases_rs.getString(3));
				clase.set_titulo(clases_rs.getString(4));
				clase.set_categoria(clases_rs.getString(5));
				clase.set_duracion(clases_rs.getInt(6));
				clase.set_capacidad(clases_rs.getInt(7));
				clase.set_instructor(clases_rs.getString(8));
				
				horas_rs.beforeFirst();
				while(horas_rs.next()) {
					
					if(horas_rs.getInt(3) == clase.get_id()) {
						Date fecha = formato_bd.parse(horas_rs.getString(2));
						
						if(!dia.equals(horas_rs.getString(1))) {
							dia = horas_rs.getString(1);
							clase.addDia(dia);
						}
						
						if(!horas.contains(horas_rs.getString(2))) {
							horas.add(horas_rs.getString(2));
							clase.addHora(clase.get_format().format(fecha));
						}
					}
				}
				
				clases.add(clase);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return clases;
	}
	
	public ClaseDTO getClase(int id_clase) {
		ClaseDTO clase = new ClaseDTO();
		SimpleDateFormat formato_bd = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		
		try {
			
			PreparedStatement get_clase = con.prepareStatement(this._statements.getProperty("get_clase"));
			PreparedStatement get_horas = con.prepareStatement(this._statements.getProperty("get_horas_clase"));
			
			get_clase.setInt(1, id_clase);
			get_horas.setInt(1, id_clase);
			
			ResultSet clase_rs = get_clase.executeQuery();
			ResultSet horas = get_horas.executeQuery();
			
			clase_rs.next();
			
			clase.set_id(clase_rs.getInt(1));
			clase.set_descripcion(clase_rs.getString(2));
			clase.set_ubicacion(clase_rs.getString(3));
			clase.set_titulo(clase_rs.getString(4));
			clase.set_categoria(clase_rs.getString(5));
			clase.set_duracion(clase_rs.getInt(6));
			clase.set_capacidad(clase_rs.getInt(7));
			clase.set_instructor(clase_rs.getString(8));
			
			String dia = "";
			ArrayList<String> horas_list = new ArrayList<String>();
			
			while(horas.next()) {
				
				Date fecha = formato_bd.parse(horas.getString(2));
				
				if(!dia.equals(horas.getString(1))) {
					dia = horas.getString(1);
					clase.addDia(dia);
				}
				
				if(!horas_list.contains(horas.getString(2))) {
					horas_list.add(horas.getString(2));
					clase.addHora(clase.get_format().format(fecha));
				}
			}
			
			/*System.out.println("TITULO: " + clase.get_titulo() + "\nDESCRIPCION: " + clase.get_descripcion() + "\nINSTRUCTOR: " + clase.get_instructor());
			System.out.print("DIAS: ");
			for(String day: clase.get_dias())
				System.out.print(day + ",");
			System.out.print("\nHORAS: ");
			for(Date hour: clase.get_horas())
				System.out.print(clase.get_format().format(hour) + ",");*/
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return clase;
	}
	
}
