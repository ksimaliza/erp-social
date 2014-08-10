/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.DetalleBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="detalleBienController")
public class DetalleBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DetalleBienController.class);
	
	@EJB
	public ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{detalleBienDataManager}")
	private DetalleBienDataManager detalleBienDataManager;

	/**
	 * @param detalleBienDataManager the detalleBienDataManager to set
	 */
	public void setDetalleBienDataManager(DetalleBienDataManager detalleBienDataManager) {
		this.detalleBienDataManager = detalleBienDataManager;
	}
	
	public DetalleBienController () {}
	
	public void insertarDetalleBien () {
		slf4jLogger.info("insertarDetalleBien");
	}
	
	public void actualizarDetalleBien () {
		slf4jLogger.info("actualizarDetalleBien");
		
		this.detalleBienDataManager.getDetalleBienEditar().setUsuarioRegistro(this.detalleBienDataManager.getUsuarioSession());
		try {
			DetalleBien detalleBien = servicioInventario.actualizarDetalleBien(this.detalleBienDataManager.getDetalleBienEditar());
			if (detalleBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.inventario.administracion.catalogos.actualizar.exito");
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarDetalleBien {}", e.toString());
		}
	}
	
	public void buscarDetalleBien () {
		
		slf4jLogger.info("buscarDetalleBien");
		
		try {
			
			List<DetalleBien> listDetalleBien = servicioInventario.buscarDetalleBienCriterios(this.detalleBienDataManager.getDetalleBienBuscar());
			
			if (CollectionUtils.isEmpty(listDetalleBien)) {
				detalleBienDataManager.setListDetalleBien(new ArrayList<DetalleBien>());
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				detalleBienDataManager.setListDetalleBien(listDetalleBien);
			}
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void activarDetalleBien () {
		slf4jLogger.info("activarDetalleBien");
		this.detalleBienDataManager.getDetalleBienEditar().setUsuarioRegistro(this.detalleBienDataManager.getUsuarioSession());
		this.detalleBienDataManager.getDetalleBienEditar().setDetBienEstado(this.detalleBienDataManager.getEstadoActivo());
		try {
			DetalleBien detalleBien = servicioInventario.actualizarDetalleBien(this.detalleBienDataManager.getDetalleBienEditar());
			if (detalleBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.inventario.administracion.catalogos.actualizar.exito");
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarDetalleBien {}", e.toString());
		}
	}
	
	public void desactivarDetalleBien () {
		slf4jLogger.info("desactivarDetalleBien");
		this.detalleBienDataManager.getDetalleBienEditar().setUsuarioRegistro(this.detalleBienDataManager.getUsuarioSession());
		this.detalleBienDataManager.getDetalleBienEditar().setDetBienEstado(this.detalleBienDataManager.getEstadoInactivo());
		try {
			DetalleBien detalleBien = servicioInventario.actualizarDetalleBien(this.detalleBienDataManager.getDetalleBienEditar());
			if (detalleBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.inventario.administracion.catalogos.actualizar.exito");
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al desactivarDetalleBien {}", e.toString());
		}
		
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
