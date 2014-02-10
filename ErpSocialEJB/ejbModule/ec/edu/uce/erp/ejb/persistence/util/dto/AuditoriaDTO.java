/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 *
 */
public class AuditoriaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUsuario;
	private String clase;
	private String accion;
	private Timestamp fechaRegistro;
	
	public AuditoriaDTO () {}
	
	public AuditoriaDTO (Integer idUsuario, String clase, String accion, Timestamp fechaRegistro) {
		this.idUsuario = idUsuario;
		this.clase = clase;
		this.accion = accion;
		this.fechaRegistro = fechaRegistro;
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
	
	/**
	 * @return the fechaRegistro
	 */
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public String getAccionClase () {
		return new StringBuilder().append(getClase() == null ? " " : getClase())
				.append(" ").append(getAccion() == null ? "" : getAccion()).toString();
	}
}
