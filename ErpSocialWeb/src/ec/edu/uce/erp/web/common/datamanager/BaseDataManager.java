/**
 * 
 */
package ec.edu.uce.erp.web.common.datamanager;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INACTIVO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INVALIDO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.REPORTE_EXCEL;
import static ec.edu.uce.erp.common.util.ConstantesApplication.REPORTE_PDF;

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
	
	public String getEstadoActivo() {
		return ESTADO_ACTIVO;
	}
	
	public String getEstadoInactivo() {
		return ESTADO_INACTIVO;
	}
	
	public String getEstadoInvalido() {
		return ESTADO_INVALIDO;
	}
	
	public String getFormatoExcel() {
		return REPORTE_EXCEL;
	}
	
	public String getFormatoPdf() {
		return REPORTE_PDF;
	}

}
