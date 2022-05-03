package business.controller.esp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.CriticaDAO;
import display.javabean.CustomerBean;

/**
 * Servlet encargado de añadir una nueva crítica
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/crearCritica")
public class AddCriticaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		CustomerBean usuario = (CustomerBean) sesion.getAttribute("customerBean");
		String redireccionar = "/practica3/espectaculos";
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("espectador")) {
				Integer id = Integer.parseInt( request.getParameter("id") ),
						puntuacion = Integer.parseInt( request.getParameter("puntuacion") );
				String titulo = request.getParameter("titulo"),
					   resenia = request.getParameter("resenia");
				
				CriticaDAO dao = new CriticaDAO(getServletContext());
				
				if(!dao.addCritica(id, titulo, resenia, puntuacion, usuario.getUsuario())) {
					sesion.setAttribute("error", "Error al a&ntilde;adir la cr&iacute;tica al sistema");
					redireccionar = "/practica3/error";
				}
				else
					sesion.setAttribute("aniadido", "true");
			}
			else {
				sesion.setAttribute("error", "Funci&oacute;n exclusiva para usuarios espectadores");
				redireccionar = "/practica3/error";
			}
		}
		else
			redireccionar = "/practica3/login";
		
		response.sendRedirect(redireccionar);
	}

}
