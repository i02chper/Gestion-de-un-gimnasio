package business.controller.esp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.espectaculo.EspectaculoMultipleDTO;
import business.espectaculo.EspectaculoPuntualDTO;
import business.espectaculo.EspectaculoTemporadaDTO;
import data.dao.EspectaculoDAO;
import display.javabean.CustomerBean;

/**
 * Servlet encargado de dar de buscar un determinado espectáculo
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/buscando")
public class BuscarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TODOS = 1;
	private static final int RESENIABLES = 2;

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
		String redireccionar = "/practica3/buscar";
		
		sesion.setAttribute("sin_resultados", true);
		
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("espectador")) {
				ArrayList<EspectaculoPuntualDTO> esp_puntuales = dao.getPuntuales(TODOS);
				ArrayList<EspectaculoTemporadaDTO> esp_temporada = dao.getTemporada(TODOS);
				ArrayList<EspectaculoMultipleDTO> esp_multiples = dao.getMultiples(TODOS);
				String titulo = request.getParameter("titulo"),
					   categoria = request.getParameter("categoria");
				
				Hashtable<Integer, String> coincidentes = dao.buscarEspectaculos(titulo, categoria);
				
				if(coincidentes == null) {
					sesion.setAttribute("error", "No se ha podido efectuar la b&uacute;squeda");
					redireccionar = "/practica3/error";
				}
				else {
					ArrayList<Integer> puntuales = new ArrayList<Integer>(),
									   temporada = new ArrayList<Integer>(),
									   multiples = new ArrayList<Integer>();
					
					Iterator it = coincidentes.keySet().iterator();
					
			        while(it.hasNext()){
			        	Integer id = (Integer) it.next();
			        	
			        	if(coincidentes.get(id).equals("puntual"))
			        		puntuales.add(id);
			        	
			        	else if(coincidentes.get(id).equals("temporada"))
			        		temporada.add(id);
			        	
			        	else
			        		multiples.add(id);
			        }
			        
			        if(!puntuales.isEmpty()) {
			        	esp_puntuales = dao.getPuntuales(puntuales);
			        
			        	if(esp_puntuales!= null) {
			        		sesion.setAttribute("puntuales_bus", esp_puntuales);
			        		sesion.setAttribute("sin_resultados", false);
			        	}
			        	
			        	else {
			        		sesion.setAttribute("error", "No se han podido obtener los resultados");
							redireccionar = "/practica3/error";
			        	}
			        }
			        
			        if(!temporada.isEmpty()) {
			        	esp_temporada = dao.getTemporada(temporada);
			        	
			        	if(esp_temporada!= null) {
			        		sesion.setAttribute("temporada_bus", esp_temporada);
			        		sesion.setAttribute("sin_resultados", false);
			        	}
			        	
			        	else {
			        		sesion.setAttribute("error", "No se han podido obtener los resultados");
							redireccionar = "/practica3/error";
			        	}
			        }
			        
			        if(!multiples.isEmpty()) {
			        	esp_multiples = dao.getMultiples(multiples);
			        	
			        	if(esp_multiples!= null) {
			        		sesion.setAttribute("multiples_bus", esp_multiples);
			        		sesion.setAttribute("sin_resultados", false);
			        	}
			        	
			        	else {
			        		sesion.setAttribute("error", "No se han podido obtener los resultados");
							redireccionar = "/practica3/error";
			        	}
			        }
				}
			}
			else {
				sesion.setAttribute("error", "Funci&oacute;n solo disponible para espectadores");
				redireccionar = "/practica3/error";
			}
		}
		else
			redireccionar = "/practica3/login";
		
		response.sendRedirect(redireccionar);
	}

}
