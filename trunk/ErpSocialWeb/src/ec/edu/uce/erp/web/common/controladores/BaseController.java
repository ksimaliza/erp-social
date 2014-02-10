/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_ACTIVO;
import static ec.edu.uce.erp.common.util.ConstantesApplication.ESTADO_INACTIVO;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
/**
 * @author 
 *
 */
public abstract class BaseController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Usuario getUsuarioSession(){
		return (Usuario)getExternalContext().getSessionMap().get("usuario");
	}
	
	/**
	 * Recupera el valor asociado a un parametro de la p&aacute;gina JSF
	 * 
	 * @param parametro
	 * @return
	 */
	protected String recuperarParametro(String parametro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		return map.get(parametro);
	}
	
	/**
	 * 
	 * @param parametro
	 * @return
	 */
	protected Object recuperarParametroObject(String parametro) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		return map.get(parametro);
	}
	
	/**
	 * Obtener un parametro se sesion
	 * @param parametro
	 * @return
	 */
	protected Object getSessionParameter(String parametro) {
		Map<String, Object> map = getExternalContext().getSessionMap();
		return map.get(parametro);
	}
	
	/**
	 * 
	 * @return
	 */
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
	
	/**
	 * 
	 * @return
	 */
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	
	
	public String getEstadoActivo() {
		return ESTADO_ACTIVO;
	}
	
	public String getEstadoInactivo() {
		return ESTADO_INACTIVO;
	}

}
