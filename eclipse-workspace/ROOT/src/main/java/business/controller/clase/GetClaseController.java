package business.controller.clase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.clase.ClaseDTO;
import data.dao.ClaseDAO;
import display.javabean.UserBean;

/**
 * Servlet implementation class ModClaseController
 */
@WebServlet("/getClase")
public class GetClaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/getClases";
		
		if(usuario == null || usuario.getNombre().equals(""))
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ClaseDAO dao = new ClaseDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/modificarClase";
		ClaseDTO clase;
		
		if(usuario == null || usuario.getNombre().equals("")) {
			redireccionar = "/getClases";
		}
		else {
			int id_clase = Integer.parseInt(request.getParameter("id_clase"));
			if((clase = dao.getClase(id_clase)) != null) {
				sesion.setAttribute("clase", clase);
			}
			else {
				sesion.setAttribute("error", "No se ha podido obtener informaci&oacute;n de la clase solicitada");
				redireccionar = "/error";
			}
		}
		
		response.sendRedirect(redireccionar);
	}

}
