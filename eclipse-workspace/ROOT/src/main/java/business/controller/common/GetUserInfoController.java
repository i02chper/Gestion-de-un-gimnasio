package business.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import business.user.User;
import data.dao.UserDAO;
import display.javabean.UserBean;

/**
 * Servlet implementation class ModClaseController
 */
@WebServlet("/getInfo")
public class GetUserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		User info = new User();
		String redireccionar = "/perfil";
		
		if(usuario == null || usuario.getNombre().equals("")) {
			redireccionar = "/login";
		}
		else {
			if((info = dao.get_user_info(usuario.getCorreo())) != null) {
				sesion.setAttribute("user_info", info);
			}
			else {
				sesion.setAttribute("error", "No se ha podido obtener informaci&oacute;n del usuario");
				redireccionar = "/error";
			}
		}
		
		response.sendRedirect(redireccionar);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
