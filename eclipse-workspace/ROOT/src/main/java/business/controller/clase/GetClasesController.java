package business.controller.clase;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import display.javabean.UserBean;
import data.dao.ClaseDAO;
import business.clase.ClaseDTO;

/**
 * Servlet encargado de obtener las clases visibles por un usuario
 * @author Javier Molina
 * @version 11/05/2022
 */
@WebServlet("/getClases")
public class GetClasesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/clases", user;
		ClaseDAO dao = new ClaseDAO(getServletContext());
		ArrayList<ClaseDTO> clases = new ArrayList<ClaseDTO>();
		
		if(usuario != null && usuario.getTipo().equals("instr"))
			user = usuario.getCorreo();
		else
			user = "todos";
		
		clases = dao.getClases(user);
		
		if(clases != null)
			sesion.setAttribute("clases", clases);
		else {
			redireccionar = "/error";
			sesion.setAttribute("error", "No se han podido extraer las clases");
		}
		
		response.sendRedirect(redireccionar);
	}
}