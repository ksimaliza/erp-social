/**
 * 
 */
package ec.edu.uce.erp.web.common.datamanager;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author 
 *
 */
public abstract class BaseDataManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioSession;

	public BaseDataManager () {
		this.usuarioSession = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}

	/**
	 * @return the usuarioSession
	 */
	public Usuario getUsuarioSession() {
		return usuarioSession;
	}

	/**
	 * @param usuarioSession the usuarioSession to set
	 */
	public void setUsuarioSession(Usuario usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

}
