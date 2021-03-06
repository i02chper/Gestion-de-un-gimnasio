package business.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.UserDAO;
import display.javabean.UserBean;

/**
 * Servlet encargado de dar de baja a un usuario del sistema
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/eliminarUsuario")
public class EliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/";
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			UserDAO dao = new UserDAO(getServletContext());
			
			if(dao.eliminarUsuario(usuario.getCorreo())) {
				sesion.setAttribute("user", null);
			}
			else {
				redireccionar = "/error";
				sesion.setAttribute("error", "NO SE HA PODIDO ELIMINAR LA INFORMACI&Oacute;N DE TU CUENTA DEL SISTEMA");
			}
		}
		else
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}

}
