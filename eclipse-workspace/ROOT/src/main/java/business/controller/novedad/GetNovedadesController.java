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
@WebServlet("/getNovedades")
public class GetNovedadesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		NovedadDAO dao = new NovedadDAO(getServletContext());
		ArrayList<NovedadDTO> novedades = new ArrayList<NovedadDTO>();
		String redireccionar = "/";
		
		if( (novedades = dao.getNovedades()) == null ) {
			sesion.setAttribute("error", "Error al extraer las novedades del sistema");
			redireccionar = "/error";
		}
		else {
			sesion.setAttribute("novedades", novedades);
			sesion.setAttribute("novedades_set", "true");
		}
		
		response.sendRedirect(redireccionar);
	}
	
}
