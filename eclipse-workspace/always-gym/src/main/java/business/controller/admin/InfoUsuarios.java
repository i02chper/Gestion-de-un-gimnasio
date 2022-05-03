package business.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.dao.*;
import display.javabean.CustomerBean;
import business.user.UserInfo;

/**
 * Servlet encargado de recoger la información de los usuarios del sistema
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/infoUsuarios")
public class InfoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redireccionar;
		ArrayList<UserInfo>info_admin;
		UserDAO dao;
		HttpSession sesion = request.getSession();
		CustomerBean usuario = (CustomerBean) sesion.getAttribute("customerBean");
		PrintWriter out = response.getWriter();
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("administrador")) {
				dao = new UserDAO(getServletContext());
				info_admin = dao.getInfoAdmin();
				
				out.print(""
					+ "<!DOCTYPE html>\n"
					+ "<html>\n"
					+ "	<head>\n"
					+ "		<meta charset=\"UTF-8\">\n"
					+ "		<title>Informaci&oacute;n Usuarios</title>\n"
					+ "		<link rel=\"stylesheet\" href=\"css/fuentes.css\">\n"
					+ "		<link rel=\"stylesheet\" href=\"css/general.css\">\n"
					+ "		<script type=\"text/javascript\" src=\"/practica3/js/funciones.js\"></script>\n"
					+ "	</head>\n"
					+ "	<body onload=\"set_size()\">"
					+ "			<div class=\"header\">\n"
					+ "				<a href=\"/practica3\">\n"
					+ "					<div class=\"home\"></div>\n"
					+ "				</a>\n"
					+ "				<a href=\"/practica3/logout\">\n"
					+ "					<div class=\"btn\">CERRAR SESI&Oacute;N</div>\n"
					+ "				</a>\n"
					+ "				<a href=\"/practica3/modificar\">\n"
					+ "					<div class=\"btn\">MODIFICAR PERFIL</div>\n"
					+ "				</a>\n"
					+ "				<a href=\"/practica3/getSesiones\">\n"
					+ "					<div class=\"btn\">GESTIONAR SESIONES</div>\n"
					+ "				</a>\n"
					+ "				<a href=\"/practica3/nuevoEspectaculo\">\n"
					+ "					<div class=\"btn\">A&Ntilde;ADIR ESPECT&Aacute;CULO</div>\n"
					+ "				</a>\n"
					+ "				<a href=\"/practica3/infoUsuarios\">\n"
					+ "					<div class=\"btn\">INFORMACI&Oacute;N USUARIOS</div>\n"
					+ "				</a>\n"
					+ "			</div>"
					+ "   <table class=\"info\">"
					+ "		<tr id=\"apartados\">\n"
					+ "			<td>USUARIO</td>\n"
					+ "			<td>REGISTRO</td>\n"
					+ "			<td>&Uacute;LTIMO ACCESO</td>\n"
					+ "		</tr>"
					);
				
				for(UserInfo user: info_admin){
					out.println(""
						+ "<tr>\n"
						+ "   <td>" + user.getUsuario() + "</td>\n"
						+ "   <td>" + user.getRegistro() + "</td>\n"
						+ "   <td>" + user.getLog() + "</td>\n"
						+ "</tr>");
				}
				out.print("</table>\n"
						+ "</body>\n"
						+ "</html>\n");
			}
			else {
				sesion.setAttribute("error", "Acceso reservado a administradores");
				redireccionar = "/practica3/error";
				response.sendRedirect(redireccionar);
			}
		}
		else {
			redireccionar = "/practica3/login";
			response.sendRedirect(redireccionar);
		}
	}
}
