package business.clase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClaseDTO {
	
	private int _id;
	private String _titulo;
	private String _descripcion;
	private String _categoria;
	private int _capacidad;
	private int _duracion;
	private ArrayList<String> _dias;
	private ArrayList<Date> _horas;
	private SimpleDateFormat formato_horas = new SimpleDateFormat("HH:mm");
	private String _instructor;
	private String _ubicacion;
	
	public ClaseDTO(){
		this._id = -1;
		this._titulo = "";
		this._descripcion = "";
		this._categoria = "";
		this._capacidad = -1;
		this._duracion = -1;
		this._dias = new ArrayList<String>();;
		this._horas = new ArrayList<Date>();;
		this._ubicacion = "";
		this._instructor = "";
	}

	public ClaseDTO(String titulo, String descripcion, String categoria, int capacidad, int duracion, String[] dias, String[] horas, String ubicacion, String instructor) {
		this._titulo = titulo;
		this._descripcion = descripcion;
		this._categoria = categoria;
		this._capacidad = capacidad;
		this._duracion = duracion;
		this._dias = new ArrayList<String>();
		this.set_dias(dias);
		this._horas = new ArrayList<Date>();
		this.set_horas(horas);
		this._ubicacion = ubicacion;
		this._instructor = instructor;
	}

	public int get_id() {
		return this._id;
	}

	public void set_id(int id) {
		this._id = id;
	}
	
	public String get_titulo() {
		return this._titulo;
	}

	public void set_titulo(String titulo) {
		this._titulo = titulo;
	}

	public String get_descripcion() {
		return this._descripcion;
	}

	public void set_descripcion(String descripcion) {
		this._descripcion = descripcion;
	}

	public String get_categoria() {
		return this._categoria;
	}

	public void set_categoria(String categoria) {
		this._categoria = categoria;
	}

	public int get_capacidad() {
		return this._capacidad;
	}

	public void set_capacidad(int capacidad) {
		this._capacidad = capacidad;
	}

	public int get_duracion() {
		return this._duracion;
	}

	public void set_duracion(int duracion) {
		this._duracion = duracion;
	}

	public SimpleDateFormat get_format() {
		return this.formato_horas;
	}
	
	public ArrayList<String> get_dias() {
		return this._dias;
	}

	public void set_dias(String[] dias) {
		for(String dia: dias)
			this._dias.add(dia);
	}
	
	public ArrayList<Date> get_horas() {
		return this._horas;
	}

	public void set_horas(String[] horas) {
		try {
			for(String hora: horas) {
				Date nueva_hora = formato_horas.parse(hora);
				this._horas.add(nueva_hora);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public String get_instructor() {
		return this._instructor;
	}

	public void set_instructor(String instructor) {
		this._instructor = instructor;
	}

	public String get_ubicacion() {
		return this._ubicacion;
	}

	public void set_ubicacion(String ubicacion) {
		this._ubicacion = ubicacion;
	}
	
	public void addDia(String dia) {
		this._dias.add(dia);
	}
	
	public void addHora(String hora) {
		try {
			this._horas.add(this.formato_horas.parse(hora));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
