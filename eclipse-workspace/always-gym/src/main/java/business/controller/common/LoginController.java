package business.controller.common;

import display.javabean.*;
import business.user.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import data.dao.UserDAO;

/**
 * Servlet encargado de controlar el inicio de sesión del usuario
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/loging")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesion = request.getSession();
		CustomerBean usuario = (CustomerBean)sesion.getAttribute("customerBean");
		String redireccionar;
		
		if(usuario != null && usuario.getNombre().equals(""))
			redireccionar = "/practica3/login";
		else
			redireccionar = "/practica3";
		
		response.sendRedirect(redireccionar);
	}
	
	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		UserDAO dao = new UserDAO(getServletContext());
		HttpSession sesion = request.getSession();
		
		String usuario = request.getParameter("usuario"),
			   pass = request.getParameter("pass"),
			   redireccionar = "/practica3",
			   nombre, tipo, logs, registro;
		
		User user = dao.getUsuario(usuario, pass);
		CustomerBean bean = (CustomerBean) sesion.getAttribute("customerBean");
  		
  		//ERROR AL LOGUEARSE
  		if(user == null){
  			redireccionar = "/practica3/error";
  			sesion.setAttribute("error", "EL USUARIO INTRODUCIDO NO SE ENCUENTRA REGISTRADO");
  		}
  		
  		//LOGUEADO
  		else{
  			dao.actualizarLog(user.get_nick());
  			nombre = user.get_nombre();
  			tipo = user.get_tipo();
  			logs = "";
  			registro = "";
  			
  			if(tipo.equals("administrador")){
  				logs = dao.getLogs();
  				
  				if(logs.equals("")){
  					redireccionar = "/practica3/error";
  					sesion.setAttribute("error", "NO SE HA PODIDO OBTENER LA INFORMACI&Oacute;N DE LOGIN DE LOS USUARIOS");
  				}
  			}
  			else{
  				registro = dao.getRegistro(user.get_nick());
  				
  				if(registro.equals("")){
  					redireccionar = "/practica3/error";
  					sesion.setAttribute("error", "NO SE HA PODIDO OBTENER LA INFORMACI&Oacute;N DE REGISTRO");
  				}
  			}
  			
  			bean.setNombre(nombre);
  			bean.setUsuario(usuario);
  			bean.setTipo(tipo);
  		}
  		
  		response.sendRedirect(redireccionar);
	}
}
