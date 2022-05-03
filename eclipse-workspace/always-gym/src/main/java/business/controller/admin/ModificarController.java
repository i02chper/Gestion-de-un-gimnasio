package business.controller.admin;

import display.javabean.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import data.dao.UserDAO;

/**
 * Servlet encargado de dar de modificar la información del usuario
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/modificando")
public class ModificarController extends HttpServlet {
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
			redireccionar = "/practica3/modificar";
		
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
		CustomerBean usuario = (CustomerBean)sesion.getAttribute("customerBean");
		
		String correo = request.getParameter("correo"),
			   pass = request.getParameter("pass"),
			   nombre = request.getParameter("nombre"),
			   apellidos = request.getParameter("apellidos"),
			   redireccionar = "/practica3";
  		
		//SI EL USUARIO NO HA MODIFICADO NADA
		if(nombre.equals("") && apellidos.equals("") && correo.equals("") && pass.equals("")){
			redireccionar = "/practica3/error";
  			sesion.setAttribute("error", "DEBE RELLENAR AL MENOS UN CAMPO");
		}
		
  		//ERROR AL MODIFICAR
  		if(!dao.modificarDatos(nombre, apellidos, correo, pass, usuario.getUsuario())){
  			redireccionar = "/practica3/error";
  			sesion.setAttribute("error", "EL USUARIO INTRODUCIDO NO SE ENCUENTRA REGISTRADO");
  		}
  		
  		//SI HA MODIFICADO SU NOMBRE
  		if(!nombre.equals(""))
  			usuario.setNombre(nombre);
  		
  		response.sendRedirect(redireccionar);
	}
}
