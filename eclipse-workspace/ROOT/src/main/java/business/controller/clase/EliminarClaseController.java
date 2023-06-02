package business.controller.clase;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.dao.ClaseDAO;
import display.javabean.UserBean;
import business.clase.ClaseDTO;

/**
 * Servlet encargado de añadir un nuevo espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/eliminarClase")
public class EliminarClaseController extends HttpServlet {
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
		
		if(usuario != null && !usuario.getNombre().equals(""))
			redireccionar = "/modificarClase";
		
		else
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}

	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ClaseDAO dao = new ClaseDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/getClases";
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("admin") || usuario.getTipo().equals("instr")) {
				int id_clase = Integer.parseInt(request.getParameter("id_clase"));
								
				if(!dao.eliminarClase(id_clase)) {
					sesion.setAttribute("error", "Error al eliminar la clase seleccionada");
					redireccionar = "/error";
				}
			}
			else
				redireccionar = "/getClases";
		}
		else
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}
}
