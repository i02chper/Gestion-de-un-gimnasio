package business.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.EspectaculoDAO;
import display.javabean.UserBean;
import business.espectaculo.*;

/**
 * Servlet encargado de obtener las sesiones de un espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/getSesiones")
public class SesionesEspectaculosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TODOS = 1;
	private static final int RESENIABLES = 2;

	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspectaculoDAO dao = new EspectaculoDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/practica3/sesiones";
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("administrador")) {
			
				ArrayList<EspectaculoPuntualDTO> esp_puntuales = dao.getPuntuales(TODOS);
				ArrayList<EspectaculoTemporadaDTO> esp_temporada = dao.getTemporada(TODOS);
				ArrayList<EspectaculoMultipleDTO> esp_multiples = dao.getMultiples(TODOS);
				
				sesion.setAttribute("puntuales", esp_puntuales);
				sesion.setAttribute("temporada", esp_temporada);
				sesion.setAttribute("multiples", esp_multiples);
			}
			else {
				sesion.setAttribute("error", "Funci&oacute;n solo disponible para administradores");
				redireccionar = "/practica3/error";
			}	
			
			response.sendRedirect(redireccionar);
		}
	}

}
