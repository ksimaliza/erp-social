/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author 
 *
 */
public abstract class AuditoriaUtil {
	
	@Transient
	private Usuario usuarioRegistro;
	
	@Transient
	private String npMensajeEditar;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date npFechaDesde;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date npFechaHasta;
	
	public AuditoriaUtil() {}

	/**
	 * @return the usuarioRegistro
	 */
	public Usuario getUsuarioRegistro() {
		return usuarioRegistro;
	}

	/**
	 * @param usuarioRegistro the usuarioRegistro to set
	 */
	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	/**
	 * @return the npMensajeEditar
	 */
	public String getNpMensajeEditar() {
		return npMensajeEditar;
	}

	/**
	 * @param npMensajeEditar the npMensajeEditar to set
	 */
	public void setNpMensajeEditar(String npMensajeEditar) {
		this.npMensajeEditar = npMensajeEditar;
	}

	/**
	 * @return the npFechaDesde
	 */
	public Date getNpFechaDesde() {
		return npFechaDesde;
	}

	/**
	 * @param npFechaDesde the npFechaDesde to set
	 */
	public void setNpFechaDesde(Date npFechaDesde) {
		this.npFechaDesde = npFechaDesde;
	}

	/**
	 * @return the npFechaHasta
	 */
	public Date getNpFechaHasta() {
		return npFechaHasta;
	}

	/**
	 * @param npFechaHasta the npFechaHasta to set
	 */
	public void setNpFechaHasta(Date npFechaHasta) {
		this.npFechaHasta = npFechaHasta;
	}
	
}
