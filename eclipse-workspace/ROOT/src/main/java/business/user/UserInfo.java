package business.user;

/**
 * Clase que representa la información del usuario que obtiene el administrador
 * @author Fernando Lucena
 * @author Javier Molina
 * @author Diego José Gómez
 * @version 29/12/2021
 *
 */
public class UserInfo {

    private final String _usuario;
    private final String _registro;
    private final String _log;

    /**
	 * Constructor parametrizado
	 * @param usuario Nick del usuario
	 * @param registro Fecha de registro del usuario
	 * @param log Última vez que el usuario accedió al sistema
	 */
    public UserInfo(String usuario, String registro, String log) {
        this._usuario = usuario;
        this._registro = registro;
        this._log = log;
    }

    /**
	 * Método encargado de devolver el nick del usuario
	 * @return Nick del usuario
	 */
    public String getUsuario() { return this._usuario; }
    
    /**
	 * Método encargado de devolver el registro del usuario
	 * @return Registro del usuario
	 */
    public String getRegistro() { return this._registro; }
    
    /**
	 * Método encargado de devolver el log del usuario
	 * @return Último acceso del usuario
	 */
    public String getLog() { return this._log; }
}
