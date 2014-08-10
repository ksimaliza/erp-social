/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.MarcaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name = "marcaBienController")
public class MarcaBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MarcaBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty (value="#{marcaBienDataManager}") 
	private MarcaBienDataManager marcaBienDataManager;
	
	/**
	 * @param marcaBienDataManager the marcaBienDataManager to set
	 */
	public void setMarcaBienDataManager(MarcaBienDataManager marcaBienDataManager) {
		this.marcaBienDataManager = marcaBienDataManager;
	}
	
	public MarcaBienController () {}
	
	public void registrarMarcaBien () {
		slf4jLogger.info("registrarMarcaBien");
		try {
			
			this.marcaBienDataManager.getMarcaBienInstancia().setUsuarioRegistro(this.marcaBienDataManager.getUsuarioSession());
			MarcaBien marcaBien = servicioInventario.registrarMarcaBien(this.marcaBienDataManager.getMarcaBienInstancia());
			if (marcaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
				this.marcaBienDataManager.getListMarcaBien().add(marcaBien);
				this.marcaBienDataManager.setMarcaBienInstancia(new MarcaBien());
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al registrarMarcaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void actualizarMarcaBien () {
		slf4jLogger.info("actualizarMarcaBien");
		try {
			this.marcaBienDataManager.getMarcaBienEditar().setUsuarioRegistro(this.marcaBienDataManager.getUsuarioSession());
			MarcaBien marcaBien = servicioInventario.actualizarMarcaBien(this.marcaBienDataManager.getMarcaBienEditar());
			if (marcaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al actualizarMarcaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void activarMarcaBien () {
		slf4jLogger.info("activarMarcaBien");
		try {
			this.marcaBienDataManager.getMarcaBienEditar().setUsuarioRegistro(this.marcaBienDataManager.getUsuarioSession());
			this.marcaBienDataManager.getMarcaBienEditar().setMarBienEstado(this.marcaBienDataManager.getEstadoActivo());
			MarcaBien marcaBien = servicioInventario.actualizarMarcaBien(this.marcaBienDataManager.getMarcaBienEditar());
			if (marcaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al activarMarcaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void desactivarMarcaBien () {
		slf4jLogger.info("desactivarMarcaBien");
		try {
			this.marcaBienDataManager.getMarcaBienEditar().setUsuarioRegistro(this.marcaBienDataManager.getUsuarioSession());
			this.marcaBienDataManager.getMarcaBienEditar().setMarBienEstado(this.marcaBienDataManager.getEstadoInactivo());
			MarcaBien marcaBien = servicioInventario.actualizarMarcaBien(this.marcaBienDataManager.getMarcaBienEditar());
			if (marcaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al desactivarMarcaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void buscarMarcaBien () {
		slf4jLogger.info("buscarMarcaBien");
		
		try {
			
			List<MarcaBien> listMarcaBien = servicioInventario.buscarMarcaBienCriterios(this.marcaBienDataManager.getMarcaBienBuscar());
			
			if (CollectionUtils.isEmpty(listMarcaBien)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.marcaBienDataManager.getListMarcaBien().clear();
			} else {
				this.marcaBienDataManager.setListMarcaBien(listMarcaBien);
			}
			
		} catch (Exception e) {
			slf4jLogger.info("error al buscarMarcaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
		
	}
	
	public void limpiarFiltrosBusqueda () {
		this.marcaBienDataManager.setMarcaBienBuscar(new MarcaBien());
	}

	@Override
	public void refrescarFormulario() {
		this.buscarMarcaBien();
	}

}
