/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ProveedorDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean (name = "proveedorController")
public class ProveedorController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ProveedorController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{proveedorDataManager}")
	private ProveedorDataManager proveedorDataManager;

	/**
	 * @param proveedorDataManager the proveedorDataManager to set
	 */
	public void setProveedorDataManager(ProveedorDataManager proveedorDataManager) {
		this.proveedorDataManager = proveedorDataManager;
	}
	
	public ProveedorController () {}
	
	public void buscarProveedor () {
		slf4jLogger.info("buscarProveedor");
	}
	
	public void registrarProveedor () {
		
		slf4jLogger.info("registrarProveedor");
		
		try {
			servicioInventario.registrarProveedor(this.proveedorDataManager.getProveedorInstancia());
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarProveedor {}", e.toString());
			MensajesWebController.aniadirMensajeInformacion(e.getMessage());
		}
		
	}

}
