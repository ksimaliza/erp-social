/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

/**
 * @author
 *
 */
public class ActaBienDTO {
	
	private String tituloActa;
	private String contenido;
	
	public ActaBienDTO () {
		
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * @return the tituloActa
	 */
	public String getTituloActa() {
		return tituloActa;
	}

	/**
	 * @param tituloActa the tituloActa to set
	 */
	public void setTituloActa(String tituloActa) {
		this.tituloActa = tituloActa;
	}

}
