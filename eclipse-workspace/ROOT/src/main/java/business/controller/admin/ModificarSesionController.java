package business.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.EspectaculoDAO;
import display.javabean.UserBean;

/**
 * Servlet encargado de dar de modificar las sesiones de un espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/modificarSesion")
public class ModificarSesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		EspectaculoDAO dao = new EspectaculoDAO(getServletContext());
		String tipo = request.getParameter("tipo"),
			   redireccionar = "/practica3/getSesiones";
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		// Comprobamos que la petición proviene de un usuario logueado, en caso contrario se redireccionará al login
		if(usuario != null && !usuario.getNombre().equals("")) {
		
			if(tipo.equals("puntual")) {
				String nueva_fecha = request.getParameter("fecha_p");
				
				if(!dao.modificarSesionPuntual(id, nueva_fecha)) {
					redireccionar = "/practica3/error";
					sesion.setAttribute("error", "No se ha podido modificar la sesi&oacute;n");
				}
			}
			else if(tipo.equals("temporada")) {
				String nuevo_inicio = request.getParameter("inicio_t"),
					   nuevo_fin = request.getParameter("fin_t"),
					   nuevo_dia = request.getParameter("dia_temp");
				
				if(!dao.modificarTemporada(id, nuevo_dia, nuevo_inicio, nuevo_fin)) {
					redireccionar = "/practica3/error";
					sesion.setAttribute("error", "No se han podido modificar los datos");
				}
			}
			else {
				String[] fechas = request.getParameterValues("fecha_m");
				
				if(!dao.modificarMultiples(id, fechas)) {
					redireccionar = "/practica3/error";
					sesion.setAttribute("error", "No se han podido modificar las sesiones");
				}
			}
			
			if(redireccionar.equals("/practica3/getSesiones"))
				sesion.setAttribute("modificado", "true");
		}
		else
			redireccionar = "/practica3/login";
		
		response.sendRedirect(redireccionar);
	}
}
