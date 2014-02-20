/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;

import ec.edu.uce.erp.common.enums.EnumTipoTransaccion;

/**
 * @author
 *
 */
public final class AuditoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String clase;
	private String accion;
	private EnumTipoTransaccion enumTipoTransaccion;
	
	public AuditoriaDTO () {}
	
	public AuditoriaDTO (Integer idUsuario, String clase, String accion, EnumTipoTransaccion enumTipoTransaccion) {
		this.idUsuario = idUsuario;
		this.clase = clase;
		this.accion = accion;
		this.enumTipoTransaccion = enumTipoTransaccion;
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
	 * @return the enumTipoTransaccion
	 */
	public EnumTipoTransaccion getEnumTipoTransaccion() {
		return enumTipoTransaccion;
	}

	/**
	 * @param enumTipoTransaccion the enumTipoTransaccion to set
	 */
	public void setEnumTipoTransaccion(EnumTipoTransaccion enumTipoTransaccion) {
		this.enumTipoTransaccion = enumTipoTransaccion;
	}
	
}
