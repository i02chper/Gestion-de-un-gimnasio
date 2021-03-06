package business.controller.common;

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
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar;
		
		if(usuario == null || usuario.getNombre().equals(""))
			redireccionar = "/login";
		else
			redireccionar = "/modificar";
		
		response.sendRedirect(redireccionar);
	}
	
	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		
		String pass = request.getParameter("pass"),
			   nombre = request.getParameter("nombre"),
			   apellidos = request.getParameter("apellidos"),
			   telefono = request.getParameter("telefono"),
			   lesion = request.getParameter("lesion"),
			   redireccionar = "/";
  		
		//SI EL USUARIO NO HA MODIFICADO NADA
		if(nombre.equals("") && apellidos.equals("") && pass.equals("") && telefono.equals("") && lesion.equals("")){
			redireccionar = "/error";
  			sesion.setAttribute("error", "DEBE RELLENAR AL MENOS UN CAMPO");
		}
		
  		//ERROR AL MODIFICAR
		// pass, nombre, apellidos, telefono, lesion
  		if(!dao.modificarDatos(pass, nombre, apellidos, telefono, lesion, usuario.getCorreo())){
  			redireccionar = "/error";
  			sesion.setAttribute("error", "EL USUARIO INTRODUCIDO NO SE ENCUENTRA REGISTRADO");
  		}
  		
  		//SI HA MODIFICADO SU NOMBRE
  		if(!nombre.equals(""))
  			usuario.setNombre(nombre);
  		
  		response.sendRedirect(redireccionar);
	}
}
