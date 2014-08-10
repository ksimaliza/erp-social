/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import static ec.edu.uce.erp.common.util.CatalogoCabeceraConstantes.ID_CAB_CATALOGO_CUIDAD_ECUADOR;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Proveedor;
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
		
		try {
			List<Proveedor> listaProveedor = servicioInventario.buscarProveedorCriterios(this.proveedorDataManager.getProveedorBuscar());
			this.proveedorDataManager.getListaProveedor().clear();
			
			if (listaProveedor.isEmpty()) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.proveedorDataManager.setListaProveedor(listaProveedor);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarProveedor {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void registrarProveedor () {
		
		slf4jLogger.info("registrarProveedor");
		
		try {
			
			this.proveedorDataManager.getProveedorInstancia().setUsuarioRegistro(this.proveedorDataManager.getUsuarioSession());
			this.proveedorDataManager.getProveedorInstancia().setCabCatalogoPaisCiudad(ID_CAB_CATALOGO_CUIDAD_ECUADOR);
			Proveedor proveedorNuevo = servicioInventario.registrarProveedor(this.proveedorDataManager.getProveedorInstancia());
			
			if (proveedorNuevo != null) {
				this.proveedorDataManager.getListaProveedor().add(proveedorNuevo);
				MensajesWebController.aniadirMensajeInformacion("erp.proveedor.informacion.registar");
				this.proveedorDataManager.setProveedorInstancia(new Proveedor());
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarProveedor {}", e.toString());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", e);
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void actualizarProveedor () {
		
		slf4jLogger.info("actualizarProveedor");
		
		try {
			this.proveedorDataManager.getProveedorInstancia().setCabCatalogoPaisCiudad(ID_CAB_CATALOGO_CUIDAD_ECUADOR);
			this.proveedorDataManager.getProveedorEditar().setUsuarioRegistro(this.proveedorDataManager.getUsuarioSession());
			Proveedor proveedorActualizado = servicioInventario.actualizarProveedor(this.proveedorDataManager.getProveedorEditar());
			
			if (proveedorActualizado != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.proveedor.informacion.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarProveedor {}", e.toString());
			MensajesWebController.aniadirMensajeInformacion(e.getMessage());
		}
		
	}
	
	public void desactivarProveedor () {
		
		slf4jLogger.info("desactivarUsuario");
		
		try {
			
			this.proveedorDataManager.getProveedorEditar().setUsuarioRegistro(this.proveedorDataManager.getUsuarioSession());
			this.proveedorDataManager.getProveedorEditar().setProvEstado(proveedorDataManager.getEstadoInactivo());
			Proveedor proveedorActualizado = servicioInventario.actualizarProveedor(this.proveedorDataManager.getProveedorEditar());
			
			if (proveedorActualizado != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.proveedor.informacion.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarProveedor {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void activarProveedor () {
		
		slf4jLogger.info("activarProveedor");
		
		try {
			
			this.proveedorDataManager.getProveedorEditar().setUsuarioRegistro(this.proveedorDataManager.getUsuarioSession());
			this.proveedorDataManager.getProveedorEditar().setProvEstado(proveedorDataManager.getEstadoActivo());
			Proveedor proveedorActualizado = servicioInventario.actualizarProveedor(this.proveedorDataManager.getProveedorEditar());
			
			if (proveedorActualizado != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.proveedor.informacion.actualizar");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al activarProveedor {}", e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.proveedorDataManager.setProveedorBuscar(new Proveedor());
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
