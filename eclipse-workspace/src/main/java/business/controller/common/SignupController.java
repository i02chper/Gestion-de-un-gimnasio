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
		
		if(usuario == null || usuario.getNombre().equals(""))
			redireccionar = "/signup";
		else
			redireccionar = "/";
		
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
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/";
		
		// Si el usuario se encuentra logueado
		if(usuario != null && !usuario.getNombre().equals("")) {
			redireccionar = "/error";
			sesion.setAttribute("error", "NO PUEDE REGISTRARSE ESTANDO LOGUEADO");
		}
		
		// En caso contrario
		else {
			UserDAO dao = new UserDAO(getServletContext());
			Integer error;
			String correo = request.getParameter("correo"),
				   pass = request.getParameter("pass"),
				   nombre = request.getParameter("nombre"),
				   apellidos = request.getParameter("apellidos"),
				   dni = request.getParameter("dni"),
				   telefono = request.getParameter("telefono"),
				   tipo = request.getParameter("tipo");
			
			if(tipo.isEmpty())
				tipo = "socio";
			else if(tipo.equals("123456")) // Código de prueba para un administrador
				tipo = "admin";
			else if(tipo.equals("654321")) // Código de prueba para un instructor
				tipo = "instr";
			else
				tipo = "error";
			
			error = dao.addUsuario(correo, pass, nombre, apellidos, dni, telefono, tipo);
			
			switch(error) {
				//CORRECTO
				case 0:
					usuario.setNombre(nombre);
					usuario.setTipo(tipo);
					usuario.setCorreo(correo);
					break;
				
				//ALGÚN CAMPO OBLIGATORIO VACÍO
				case -1:
					redireccionar = "/error";
					sesion.setAttribute("error", "EL CÓDIGO INTRODUCIDO NO SE ENCUENTRA REGISTRADO");
					break;
					
				//USUARIO YA RESGISTRADO
				case -2:
					redireccionar = "/error";
					sesion.setAttribute("error", "EL USUARIO INTRODUCIDO YA SE ENCUENTRA REGISTRADO");
					break;				
					
				//CUALQUIER OTRO ERROR
				default:
					redireccionar = "/error";
					sesion.setAttribute("error", "ERROR: EL REGISTRO NO SE HA PODIDO LLEVAR A CABO");
					break;
			}
		}
  		response.sendRedirect(redireccionar);
	}
}
