package business.controller.novedad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.novedad.NovedadDTO;
import data.dao.ClaseDAO;
import data.dao.NovedadDAO;
import display.javabean.UserBean;

/**
 * Servlet implementation class AddNovedadController
 */
@WebServlet("/modNovedad")
public class ModNovedadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/";
		
		if(usuario == null|| usuario.getNombre().equals(""))
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		NovedadDAO dao = new NovedadDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		
		String titulo = request.getParameter("titulo"),
			   cuerpo = request.getParameter("cuerpo"),
			   redireccionar = "/";
		int id_novedad = Integer.parseInt(request.getParameter("id_novedad"));
		
		if(usuario.getTipo().equals("admin")) {
			NovedadDTO novedad = new NovedadDTO();
			
			novedad.set_id(id_novedad);
			novedad.set_titulo(titulo);
			novedad.set_cuerpo(cuerpo);
			novedad.set_autor(usuario.getCorreo());
			
			if(!dao.modNovedad(novedad)) {
				sesion.setAttribute("error", "Error al actualizar la novedad");
				redireccionar = "/error";
			}
		}
		response.sendRedirect(redireccionar);
	}

}
