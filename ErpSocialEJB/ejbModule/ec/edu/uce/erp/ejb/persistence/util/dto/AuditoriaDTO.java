/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;

/**
 * @author
 *
 */
public class AuditoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String clase;
	private String accion;
	private String idTipoTransaccion;
	
	public AuditoriaDTO () {}
	
	public AuditoriaDTO (Integer idUsuario, String clase, String accion, String idTipoTransaccion) {
		this.idUsuario = idUsuario;
		this.clase = clase;
		this.accion = accion;
		this.idTipoTransaccion = idTipoTransaccion;
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * @param clase the clase to set
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}
	
	public String getAccionClase () {
		return new StringBuilder().append(getClase() == null ? " " : getClase())
				.append(" ").append(getAccion() == null ? "" : getAccion()).toString();
	}

	/**
	 * @return the idTipoTransaccion
	 */
	public String getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	/**
	 * @param idTipoTransaccion the idTipoTransaccion to set
	 */
	public void setIdTipoTransaccion(String idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}
	
}
