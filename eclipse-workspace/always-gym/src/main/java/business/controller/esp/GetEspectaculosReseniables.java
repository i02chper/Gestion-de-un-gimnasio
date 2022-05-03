package business.controller.esp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.espectaculo.EspectaculoMultipleDTO;
import business.espectaculo.EspectaculoPuntualDTO;
import business.espectaculo.EspectaculoTemporadaDTO;
import data.dao.CriticaDAO;
import data.dao.EspectaculoDAO;
import display.javabean.CustomerBean;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet encargado de obtener los espectáculos que puede ser criticados
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 */
@WebServlet("/espectaculos")
public class GetEspectaculosReseniables extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TODOS = 1;
	private static final int RESENIABLES = 2;
	
	/**
	 * Método encargado de controlar la petición GET del servlet
	 * @param request Parámetro que contiene información sobre la petición
	 * @param response Parámetro que contendrá elementos de la respuesta
	 * @throws IOException Si se produce un error en la entrada/salida
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		CustomerBean usuario = (CustomerBean) sesion.getAttribute("customerBean");
		EspectaculoDAO dao = new EspectaculoDAO(getServletContext());
		CriticaDAO dao_criticas = new CriticaDAO(getServletContext());
		PrintWriter out = response.getWriter();
		
		//Comprobamos que estamos logueados
		if(usuario != null && !usuario.getNombre().equals("")) {
			
			if(usuario.getTipo().equals("espectador")) {
				ArrayList<EspectaculoPuntualDTO> esp_puntuales = dao.getPuntuales(RESENIABLES);
				ArrayList<EspectaculoTemporadaDTO> esp_temporada = dao.getTemporada(RESENIABLES);
				ArrayList<EspectaculoMultipleDTO> esp_multiples = dao.getMultiples(RESENIABLES);
				
				ArrayList<Integer> ids = new ArrayList<Integer>();
				
				for(EspectaculoPuntualDTO espectaculo: esp_puntuales)
					ids.add(espectaculo.get_id());
				
				for(EspectaculoTemporadaDTO espectaculo: esp_temporada)
					ids.add(espectaculo.get_id());
				
				for(EspectaculoMultipleDTO espectaculo: esp_multiples)
					ids.add(espectaculo.get_id());
				
				Hashtable<Integer, Integer> ncriticas = dao_criticas.getNumeroCriticas(ids);
				
				String aniadido = (String) sesion.getAttribute("aniadido");
				
				out.print(""
						+ "<!DOCTYPE html>\n"
						+ "<html>\n"
						+ "	<head>\n"
						+ "		<meta charset=\"UTF-8\">\n"
						+ "		<title>Espect&aacute;culos rese&ntilde;ables</title>\n"
						+ "		<link rel=\"stylesheet\" href=\"css/fuentes.css\">"
						+ "		<link rel=\"stylesheet\" href=\"css/general.css\">"
						+ "		<script type=\"text/javascript\" src=\"/practica3/js/funciones.js\"></script>"
						+ "	</head>\n");
				
			  //Comprobamos si ha habido alguna modificación previa
			  if( aniadido != null && aniadido.equals("true")) {
				out.print("<body onload=\"set_size(), exito_critica()\">");
			    sesion.setAttribute("aniadido", "false");
			  }
			  else
				out.print("<body onload=\"set_size()\">");

				out.print("			<div class=\"header\">\n"
						+ "				<a href=\"/practica3\">\n"
						+ "					<div class=\"home\"></div>\n"
						+ "				</a>\n"
						+ "				<a href=\"/practica3/logout\">\n"
						+ "					<div class=\"btn\">CERRAR SESI&Oacute;N</div>\n"
						+ "				</a>\n"
						+ "				<a href=\"/practica3/modificar\">\n"
						+ "					<div class=\"btn\">MODIFICAR PERFIL</div>\n"
						+ "				</a>\n"
						+ "				<a href=\"/practica3/buscar\">\n"
						+ "					<div class=\"btn\">BUSCAR</div>\n"
						+ "			  	</a>\n"
						+ "			  	<a href=\"/practica3/espectaculos\">\n"
						+ "					<div class=\"btn\">ESPECT&Aacute;CULOS</div>\n"
						+ "			  	</a>\n"
						+ "			</div>"
						+ "   <h3>Espect&aacute;culos rese&ntilde;ables</h3>\n"
						+ "	  <p>Selecciona el espect&aacute;culo al que desea a&ntilde;adir una cr&iacute;tica</p>"
						+ "	  <span class=\"reseniable_container\">"
						);
				
				if(esp_puntuales != null) {
					Integer cont = 1;
					
					for(EspectaculoPuntualDTO espectaculo: esp_puntuales) {
						out.print("<div class=\"reseniable\">" +
									 "<h3 class=\"titulo\">" + espectaculo.get_titulo().toUpperCase() + "</h3>" +
								     "<p>" + espectaculo.get_descripcion() + "</p>" +
								     "<label>Categor&iacute;a: </label>" + espectaculo.get_categoria() + "<br>" +
								     "<label>Localidades disponibles: </label>" + ( espectaculo.get_localidades() - espectaculo.get_vendidas() ) + "<br>" +
								     "<label>Fecha: </label>" + espectaculo.getFechaHora() + "<br>" +
								     "<form method=\"post\" action=\"/practica3/critica\" id=\"puntual" + cont + "\">" +
									 	"<input type=\"hidden\" value=\"" + espectaculo.get_id() + "\" name=\"espectaculo\">" +
									 "</form>" +
									 "<span class=\"control\">" +
									 "	<button type=\"submit\" form=\"puntual" + cont + "\">Redactar cr&iacute;tica</button>");
									 
						
						if(ncriticas.get(espectaculo.get_id()) != 0)
							out.print("<form method=\"post\" action=\"/practica3/getCriticas\" id=\"consultar_punt" + cont + "\"><input type=\"hidden\" name=\"id\" value=\"" + espectaculo.get_id() + "\"/></form>"
									+ "<button type=\"submit\" form=\"consultar_punt" + cont + "\">Consultar cr&iacute;ticas (" + ncriticas.get(espectaculo.get_id()) + ")</button>");
						
						else
							out.print("<button type=\"button\" class=\"disabled\">Consultar cr&iacute;ticas (0)</button>");
									 
						out.print("</span></div>");
						
						cont++;
					}
				}
				
				if(esp_temporada != null) {
					Integer cont = 1;
					
					for(EspectaculoTemporadaDTO espectaculo: esp_temporada) {
						out.print("<div class=\"reseniable\">" +
							     "<h3 class=\"titulo\">" + espectaculo.get_titulo().toUpperCase() + "</h3>" +
							     "<p>" + espectaculo.get_descripcion() + "</p>" +
							     "<label>Categor&iacute;a: </label>" + espectaculo.get_categoria() + "<br>" + 
							     "<label>Localidades disponibles: </label>" + ( espectaculo.get_localidades() - espectaculo.get_vendidas() ) + "<br>" +
							     "<label>D&iacute;a de la semana: </label>" + espectaculo.get_dia() + "<br>" +
							     "<label>Fecha de inicio: </label>" + espectaculo.get_inicio().substring(0, espectaculo.get_inicio().indexOf(' ')) + "<br>" +
							     "<label>&Uacute;ltima fecha: </label>" + espectaculo.get_fin().substring(0, espectaculo.get_fin().indexOf(' ')) + "<br>" +
							     "<form method=\"post\" action=\"/practica3/critica\" id=\"temporal" + cont + "\">" +
								 	"<input type=\"hidden\" value=\"" + espectaculo.get_id() + "\" name=\"espectaculo\">" +
								 "</form>" +
								 "<span class=\"control\">" +
								 "	<button type=\"submit\" form=\"temporal" + cont + "\">Redactar cr&iacute;tica</button>");
						
						if(ncriticas.get(espectaculo.get_id()) != 0)
							out.print("<form method=\"post\" action=\"/practica3/getCriticas\" id=\"consultar_temp" + cont + "\"><input type=\"hidden\" name=\"id\" value=\"" + espectaculo.get_id() + "\"/></form>"
									+ "<button type=\"submit\" form=\"consultar_temp" + cont + "\">Consultar cr&iacute;ticas (" + ncriticas.get(espectaculo.get_id()) + ")</button>");
						
						else
							out.print("<button type=\"button\" class=\"disabled\">Consultar cr&iacute;ticas (0)</button>");
									 
						out.print("</span></div>");
						
						cont++;
					}
				}
				if(esp_multiples != null) {
					Integer cont = 1;
					
					for(EspectaculoMultipleDTO espectaculo: esp_multiples) {
						out.print("<div class=\"reseniable\">" +
									 "<h3 class=\"titulo\">" + espectaculo.get_titulo().toUpperCase() + "</h3>" +
								     "<p>" + espectaculo.get_descripcion() + "</p>" +
								     "<label>Categor&iacute;a: </label>" + espectaculo.get_categoria() + "<br>" +
								     "<label>Localidades disponibles: </label>" + ( espectaculo.get_localidades() - espectaculo.get_vendidas() ) + "<br>" +
									 "<p>" + 
									 "<label>Fechas: </label>");
										for(LocalDateTime fecha: espectaculo.getFechasHoras()){
											out.print("&nbsp;&nbsp;<br>" + fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
										}
						out.print("</p>" +
								 "<form method=\"post\" action=\"/practica3/critica\" id=\"multiple" + cont + "\">" +
								 	"<input type=\"hidden\" value=\"" + espectaculo.get_id() + "\" name=\"espectaculo\">" +
								 "</form>" +
								 "<span class=\"control\">" +
								 "	<button type=\"submit\" form=\"multiple" + cont + "\">Redactar cr&iacute;tica</button>");
						
						if(ncriticas.get(espectaculo.get_id()) != 0)
							out.print("<form method=\"post\" action=\"/practica3/getCriticas\" id=\"consultar_multi" + cont + "\"><input type=\"hidden\" name=\"id\" value=\"" + espectaculo.get_id() + "\"/></form>"
									+ "<button type=\"submit\" form=\"consultar_multi" + cont + "\">Consultar cr&iacute;ticas (" + ncriticas.get(espectaculo.get_id()) + ")</button>");
						
						else
							out.print("<button type=\"button\" class=\"disabled\">Consultar cr&iacute;ticas (0)</button>");
									 
						out.print("</span></div>");
						
						cont++;
					}
				}
				else {
					sesion.setAttribute("error", "Error al obtener los espect&aacute;culos rese&ntilde;ables");
					response.sendRedirect("/practica3/error");
				}
			}
			else {
				sesion.setAttribute("error", "Funci&oacute;n exclusiva para usuarios espectadores");
				response.sendRedirect("/practica3/error");
			}
			out.print("</span></body></html>");
		}
	}
}
