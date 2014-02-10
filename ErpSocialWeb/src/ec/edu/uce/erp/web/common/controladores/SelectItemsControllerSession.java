/**
 * 
 */
package ec.edu.uce.erp.web.common.controladores;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;

/**
 * 
 * Clase para cargar select items con alcance de session de usuario
 * 
 * @author 
 *
 */
@SessionScoped
@ManagedBean (name = "selectItemsControllerSession")
public class SelectItemsControllerSession extends BaseController {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SelectItemsControllerSession.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	
	
	
	
}
