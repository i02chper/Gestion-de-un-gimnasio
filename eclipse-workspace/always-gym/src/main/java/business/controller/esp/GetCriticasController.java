package business.controller.esp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.critica.CriticaDTO;
import display.javabean.CustomerBean;
import data.dao.CriticaDAO;

/**
 * Servlet encargado de obtener las críticas de un espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/getCriticas")
public class GetCriticasController extends HttpServlet {
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
		String redireccionar = "/practica3/criticas";
		CriticaDAO dao = new CriticaDAO(getServletContext());
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("espectador")) {
				ArrayList<CriticaDTO> criticas = new ArrayList<CriticaDTO>();
				
				if( (criticas = dao.getCriticas(id)) != null )
					sesion.setAttribute("criticas", criticas);
				
				else {
					sesion.setAttribute("error", "Error al obtener las cr&iacute;ticas del sistema");
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
