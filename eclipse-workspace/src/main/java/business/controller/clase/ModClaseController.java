package business.controller.clase;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.dao.ClaseDAO;
import display.javabean.UserBean;
import business.clase.ClaseDTO;

/**
 * Servlet encargado de añadir un nuevo espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/modClase")
public class ModClaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/";
		
		if(usuario != null && !usuario.getNombre().equals(""))
			redireccionar = "/modificarClase";
		
		else
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}

	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ClaseDAO dao = new ClaseDAO(getServletContext());
		HttpSession sesion = request.getSession();
		UserBean usuario = (UserBean) sesion.getAttribute("user");
		String redireccionar = "/getClases";
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("admin") || usuario.getTipo().equals("instr")) {
				int id = Integer.parseInt(request.getParameter("id_clase")), error;
				String titulo = request.getParameter("titulo"),
					   descripcion = request.getParameter("descripcion"),
					   duracion = request.getParameter("duracion"),
					   capacidad = request.getParameter("capacidad"),
					   categoria = request.getParameter("categoria"),
					   ubicacion = request.getParameter("ubicacion"),
					   instructor;
				
				String[] horas = request.getParameterValues("hora"),
						 dias = request.getParameterValues("dia");
				
				if(usuario.getTipo().equals("instr"))
					instructor = usuario.getCorreo();
				else
					instructor = request.getParameter("instructor");
				
				// Creamos la clase
				ClaseDTO clase_mod = new ClaseDTO(titulo, descripcion, categoria, Integer.parseInt(capacidad), Integer.parseInt(duracion), dias, horas, ubicacion, instructor);
				ClaseDTO clase_original = (ClaseDTO) sesion.getAttribute("clase");
				
				clase_mod.set_id(id);
				clase_original.set_id(id);
				
				// Comprobamos que se haya hecho alguna modificacion
				if(!clase_original.es_igual(clase_mod)) {
					System.out.println("DISTINTAS");
					
					error = dao.modificarClase(clase_original, clase_mod);
					
					switch(error) {
						case -1:
							sesion.setAttribute("error", "El instructor indicado no se encuentra registrado en el sistema");
							redireccionar = "/error";
							break;
							
						case -2:
							sesion.setAttribute("error", "El usuario indicado no es un instructor");
							redireccionar = "/error";
							break;
						
						case -3:
							sesion.setAttribute("error", "Error al a&ntilde;adir la clase al sistema. Es probable que se hay intentado añadir una clase en un horario ya ocupado");
							redireccionar = "/error";
					}
				}
				
				/*System.out.println("CLASE ORIGINAL\nTITULO: " + clase_original.get_titulo() + "\nDESCRIPCION: " + clase_original.get_descripcion() + "\nINSTRUCTOR: " + clase_original.get_instructor() +
						"CAPACIDAD: " + clase_original.get_capacidad() + "DURACION: " + clase_original.get_duracion() + "UBICACION: " + clase_original.get_ubicacion() + 
						"CATEGORIA: " + clase_original.get_categoria());
				System.out.print("DIAS: ");
				for(String day: clase_original.get_dias())
					System.out.print(day + ",");
				System.out.print("\nHORAS: ");
				for(Date hour: clase_original.get_horas())
				System.out.print(clase_original.get_format().format(hour) + ",");*/
			}
			else
				redireccionar = "/getClases";
		}
		else
			redireccionar = "/login";
		
		response.sendRedirect(redireccionar);
	}
}
