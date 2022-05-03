package business.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.dao.EspectaculoDAO;
import display.javabean.CustomerBean;
import business.espectaculo.*;

/**
 * Servlet encargado de añadir un nuevo espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/addEspectaculo")
public class AddEspectaculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int ANIADIR = 1;
	private static final int MODIFICAR = 2;
 
	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/practica3/nuevoEspectaculo");
	}

	/**
	 * Método encargado de controlar la petición POST del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EspectaculoDAO dao = new EspectaculoDAO(getServletContext());
		HttpSession sesion = request.getSession();
		CustomerBean usuario = (CustomerBean) sesion.getAttribute("customerBean");
		EspectaculoConcreto ec = new EspectaculoConcreto();
		
		String tipo = request.getParameter("tipo"),
			   titulo = request.getParameter("titulo"),
			   descripcion = request.getParameter("descripcion"),
			   localidades = request.getParameter("localidades"),
			   categoria = request.getParameter("categoria"),
			   redireccionar = "/practica3";
		
		if(tipo.equals("puntual")) {
			String fecha = request.getParameter("fecha_p").replace('T', ' ');
			EspectaculoPuntualDTO espectaculo = ec.crearEspectaculoPuntual();
			
			espectaculo.set_titulo(titulo);
			espectaculo.set_descripcion(descripcion);
			espectaculo.set_categoria(categoria);
			espectaculo.set_localidades(Integer.parseInt(localidades));
			espectaculo.setFechaHora(fecha);
			
			if(!dao.AddEspectaculoPuntual(espectaculo, ANIADIR)) {
				sesion.setAttribute("mensaje", "El espect&aacute;culo no ha podido ser a&ntilde;adido");
				redireccionar = "/practica3/error";
			}
		}
		else if(tipo.equals("temporada")) {
			String dia = request.getParameter("dia_t");
			String inicio = request.getParameter("inicio_t").concat(" 00:00");
			String fin = request.getParameter("final_t").concat(" 00:00");
			
			EspectaculoTemporadaDTO espectaculo = ec.crearEspectaculoTemporada();
			
			espectaculo.set_titulo(titulo);
			espectaculo.set_descripcion(descripcion);
			espectaculo.set_categoria(categoria);
			espectaculo.set_localidades(Integer.parseInt(localidades));
			espectaculo.set_dia(dia);
			espectaculo.set_inicio(inicio);
			espectaculo.set_fin(fin);
			
			if(!dao.AddEspectaculoTemporada(espectaculo, ANIADIR)) {
				sesion.setAttribute("mensaje", "El espect&aacute;culo no ha podido ser a&ntilde;adido");
				redireccionar = "/practica3/error";
			}
			
		}
		else {
			String[] fechas = request.getParameterValues("fecha_m");
			
			EspectaculoMultipleDTO espectaculo = ec.crearEspectaculoMultiple();
			
			espectaculo.set_titulo(titulo);
			espectaculo.set_descripcion(descripcion);
			espectaculo.set_categoria(categoria);
			espectaculo.set_localidades(Integer.parseInt(localidades));
			
			for(String fecha: fechas)
				espectaculo.addFechaHora(fecha.replace('T', ' '));
			
			if(!dao.AddEspectaculoMultiple(espectaculo, ANIADIR)) {
				sesion.setAttribute("mensaje", "El espect&aacute;culo no ha podido ser a&ntilde;adido");
				redireccionar = "/practica3/error";
			}
		}
		response.sendRedirect(redireccionar);
	}

}
