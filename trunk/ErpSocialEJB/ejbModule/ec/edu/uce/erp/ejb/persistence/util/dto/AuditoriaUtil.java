/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import javax.persistence.Transient;

import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author 
 *
 */
public abstract class AuditoriaUtil {
	
	@Transient
	private Usuario usuarioRegistro;
	
	
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
	
}
