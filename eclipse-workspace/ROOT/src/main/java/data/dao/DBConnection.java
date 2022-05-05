package data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Clase encargada de la conexión a la base de datos
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego Gómez
 * @version 16/11/2021
 */
public class DBConnection {
	protected Connection _con = null;
	
	/**
	 * Método encargado de conectar con la base de datos
	 * @return Conexión con la base de datos
	 */
	public Connection conectar() {
		Connection con=null;
		try {
			
			Context ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			String url=(String)env.lookup("url");
			String user=(String)env.lookup("user");
			String pass=(String)env.lookup("pass");
			
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection)DriverManager.getConnection(url,user,pass);
			
		} catch (Exception e) {
			return null;
		}
		
		return con;
	}
	
	/**
	* Metodo encargado de cerrar conexión con la base de datos
	* @return Devuelve true si se ha cerrado la conexión correctamente o false en caso contrario
	*/
	public boolean closeConnection() {
		try {
			if(this._con != null && !this._con.isClosed()) {
				this._con.close();
			}
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
}
