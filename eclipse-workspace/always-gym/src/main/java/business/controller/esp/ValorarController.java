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
 * Servlet encargado de valorar una determinada crítica
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/valorar")
public class ValorarController extends HttpServlet {
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
		Integer id_critica, valoracion;
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			CriticaDAO dao = new CriticaDAO(getServletContext());
			id_critica = Integer.parseInt(request.getParameter("id"));
			valoracion = Integer.parseInt(request.getParameter("valoracion"));
			
			if(usuario.getTipo().equals("espectador")) {
				if(dao.valorar(id_critica, valoracion))
					sesion.setAttribute("valorado", "true");
				
				else {
					sesion.setAttribute("error", "No se ha podido a&ntilde;adir la valoraci&oacute;n");
					redireccionar = "/practica3/error";
				}
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
