package ec.edu.uce.erp.common.util;

/**
 * Clase para administrar exepciones en la aplicaci&oacute;n
 * @author 
 *
 */
public class SeguridadesException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor vacio
	 */
	public SeguridadesException(){
		super();
	}
	
	/**
	 * 
	 * @param mensaje
	 */
	public SeguridadesException(String mensaje){
		super(mensaje);
	}
	
	/**
	 * 
	 * @param causa
	 */
	public SeguridadesException(Throwable causa){
		super(causa);
	}
	
	/**
	 * 
	 * @param mensaje
	 * @param causa
	 */
	public SeguridadesException(String mensaje, Throwable causa){
		super(mensaje, causa);
	}

}
