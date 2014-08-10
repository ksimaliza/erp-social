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

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.CategoriaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name="categoriaBienController")
public class CategoriaBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LineaBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty (value = "#{categoriaBienDataManager}") 
	private CategoriaBienDataManager categoriaBienDataManager;
	
	/**
	 * @param categoriaBienDataManager the categoriaBienDataManager to set
	 */
	public void setCategoriaBienDataManager(
			CategoriaBienDataManager categoriaBienDataManager) {
		this.categoriaBienDataManager = categoriaBienDataManager;
	}
	
	public CategoriaBienController () {}
	
	public void registrarCategoriaBien() {
		slf4jLogger.info("registrarCategoriaBien");
		try {
			
			this.categoriaBienDataManager.getCategoriaBienInstancia().setUsuarioRegistro(this.categoriaBienDataManager.getUsuarioSession());
			CategoriaBien categoriaBien = this.servicioInventario.registrarCategoriaBien(this.categoriaBienDataManager.getCategoriaBienInstancia());
			
			if (categoriaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
				this.categoriaBienDataManager.getListCategoriaBien().add(categoriaBien);
				this.categoriaBienDataManager.setCategoriaBienInstancia(new CategoriaBien());
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void actualizarCategoriaBien() {
		
		slf4jLogger.info("actualizarCategoriaBien");
		try {
			
			this.categoriaBienDataManager.getCategoriaBienEditar().setUsuarioRegistro(this.categoriaBienDataManager.getUsuarioSession());
			CategoriaBien categoriaBien = this.servicioInventario.actualizarCategoriaBien(this.categoriaBienDataManager.getCategoriaBienEditar());
			
			if (categoriaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al actualizarCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void activarCategoriaBien() {
		
		slf4jLogger.info("activarCategoriaBien");
		try {
			
			this.categoriaBienDataManager.getCategoriaBienEditar().setUsuarioRegistro(this.categoriaBienDataManager.getUsuarioSession());
			this.categoriaBienDataManager.getCategoriaBienEditar().setCatBienEstado(this.categoriaBienDataManager.getEstadoActivo());
			CategoriaBien categoriaBien = this.servicioInventario.actualizarCategoriaBien(this.categoriaBienDataManager.getCategoriaBienEditar());
			
			if (categoriaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al activarCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void desactivarCategoriaBien() {
		
		slf4jLogger.info("desactivarCategoriaBien");
		try {
			
			this.categoriaBienDataManager.getCategoriaBienEditar().setUsuarioRegistro(this.categoriaBienDataManager.getUsuarioSession());
			this.categoriaBienDataManager.getCategoriaBienEditar().setCatBienEstado(this.categoriaBienDataManager.getEstadoInactivo());
			CategoriaBien categoriaBien = this.servicioInventario.actualizarCategoriaBien(this.categoriaBienDataManager.getCategoriaBienEditar());
			
			if (categoriaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al desactivarCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void buscarCategoriaBien() {
		slf4jLogger.info("buscarCategoriaBien");
		
		try {
			List<CategoriaBien> listCategoriaBien = servicioInventario.buscarCategoriaBienCriterios(this.categoriaBienDataManager.getCategoriaBienBuscar());
			
			if (CollectionUtils.isEmpty(listCategoriaBien)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.categoriaBienDataManager.getListCategoriaBien().clear();
			} else {
				this.categoriaBienDataManager.setListCategoriaBien(listCategoriaBien);
			}
			
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.categoriaBienDataManager.setCategoriaBienBuscar(new CategoriaBien());
	}

	@Override
	public void refrescarFormulario() {
		this.buscarCategoriaBien();
	}

}
