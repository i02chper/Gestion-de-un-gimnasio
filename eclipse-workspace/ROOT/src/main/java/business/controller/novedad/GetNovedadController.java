package business.controller.novedad;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.novedad.NovedadDTO;
import data.dao.NovedadDAO;

/**
 * Servlet implementation class GetNovedadesController
 */
@WebServlet("/getNovedad")
public class GetNovedadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		NovedadDAO dao = new NovedadDAO(getServletContext());
		NovedadDTO novedad = new NovedadDTO();
		int id_novedad = Integer.parseInt(request.getParameter("id_novedad"));
		String redireccionar = "/modificarNovedad";
		
		if( (novedad = dao.getNovedad(id_novedad)) == null ) {
			sesion.setAttribute("error", "Error al extraer informaci&oacute;n de la novedad");
			redireccionar = "/error";
		}
		else
			sesion.setAttribute("novedad", novedad);
		
		response.sendRedirect(redireccionar);
	}
	
}
