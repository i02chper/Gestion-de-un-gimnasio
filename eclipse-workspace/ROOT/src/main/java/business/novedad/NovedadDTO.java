package business.novedad;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NovedadDTO {
	
	private int _id;
	private String _titulo;
	private String _cuerpo;
	private String _autor;
	private Date _fecha_publicacion;
	private SimpleDateFormat _formato;
	
	public NovedadDTO() {
		this._titulo = "";
		this._cuerpo = "";
		this._autor = "";
		this._fecha_publicacion = null;
		this._formato = new SimpleDateFormat("dd/MM/YYYY");
	}
	
	public NovedadDTO(String titulo, String cuerpo, String autor, Date fecha) {
		this._titulo = titulo;
		this._cuerpo = cuerpo;
		this._autor = autor;
		this._fecha_publicacion = fecha;
		this._formato = new SimpleDateFormat("dd/MM/YYYY");
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

	public String get_cuerpo() {
		return this._cuerpo;
	}

	public void set_cuerpo(String  cuerpo) {
		this._cuerpo =  cuerpo;
	}

	public String get_autor() {
		return this._autor;
	}

	public void set_autor(String autor) {
		this._autor = autor;
	}

	public Date get_fecha_publicacion() {
		return this._fecha_publicacion;
	}

	public void set_fecha_publicacion(Date fecha_publicacion) {
		this._fecha_publicacion = fecha_publicacion;
	}

	public SimpleDateFormat get_formato() {
		return this._formato;
	}

	public void set_formato(SimpleDateFormat formato) {
		this._formato = formato;
	}
}
