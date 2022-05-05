package business.controller.common;

import display.javabean.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import data.dao.UserDAO;

/**
 * Servlet encargado de controlar el registro de un nuevo usuario
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/signingup")
public class SignupController extends HttpServlet {
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
		
		if(usuario != null && usuario.getNombre().equals(""))
			redireccionar = "/practica3/signup";
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
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		
		String nombre_usuario = request.getParameter("usuario"),
			   tipo = request.getParameter("tipo"),
			   correo = request.getParameter("correo"),
			   pass = request.getParameter("pass"),
			   nombre = request.getParameter("nombre"),
			   apellidos = request.getParameter("apellidos"),
			   redireccionar = "/practica3";
		
		Integer error = dao.addUsuario(nombre_usuario, nombre, apellidos, correo, pass, tipo);
		
		switch(error) {
			//CORRECTO
			case 0:
				if(tipo.equals("admin"))
					tipo = "administrador";
				else
					tipo = "espectador";
				
				usuario.setNombre(nombre);
				usuario.setTipo(tipo);
				usuario.setCorreo(nombre_usuario);
				break;
			
			//ALGÚN CAMPO VACÍO
			case -1:
				redireccionar = "/practica3/error";
				sesion.setAttribute("error", "DEBE RELLENAR TODOS LOS CAMPOS DEL REGISTRO");
				break;
				
			//USUARIO YA RESGISTRADO
			case -2:
				redireccionar = "/practica3/error";
				sesion.setAttribute("error", "EL USUARIO INTRODUCIDO YA SE ENCUENTRA REGISTRADO");
				break;
			
			//CUALQUIER OTRO ERROR
			default:
				redireccionar = "/practica3/error";
				sesion.setAttribute("error", "ERROR: EL REGISTRO NO SE HA PODIDO LLEVAR A CABO");
				break;
		}
  		
  		response.sendRedirect(redireccionar);
	}
}
