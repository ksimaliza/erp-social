/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.UtilSelectItems;
import ec.edu.uce.erp.web.datamanager.LineaBienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="lineaBienController")
public class LineaBienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LineaBienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{lineaBienDataManager}")
	private LineaBienDataManager lineaBienDataManager;
	
	private List<SelectItem> dcCategoriaBien;
	
	/**
	 * @param lineaBienDataManager the lineaBienDataManager to set
	 */
	public void setLineaBienDataManager(LineaBienDataManager lineaBienDataManager) {
		this.lineaBienDataManager = lineaBienDataManager;
	}
	
	public LineaBienController () {}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		
		try {
			this.setDcCategoriaBien(UtilSelectItems.getInstancia().cargarSelectItemCategoriaBien(servicioInventario));
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
			slf4jLogger.info("Error al cargar la los datos de la pantalla Linea bien {}", e.toString());
		}
	}
	
	public void registrarLineaBien () {
		slf4jLogger.info("registrarLineaBien");
		try {
			this.lineaBienDataManager.getLineaBienInstancia().setUsuarioRegistro(this.lineaBienDataManager.getUsuarioSession());
			LineaBien lineaBien = servicioInventario.registrarLineaBien(this.lineaBienDataManager.getLineaBienInstancia());
			if (lineaBien != null) {
				this.lineaBienDataManager.setLineaBienInstancia(new LineaBien());
				this.lineaBienDataManager.getListLineaBien().add(lineaBien);
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
			}
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.toString());
			slf4jLogger.info("Error al registrarLineaBien {}", e.toString());
		}
	}
	
	public void actualizarLineaBien () {
		slf4jLogger.info("actualizarLineaBien");
		try {
			this.lineaBienDataManager.getLineaBienEditar().setUsuarioRegistro(this.lineaBienDataManager.getUsuarioSession());
			LineaBien lineaBien = servicioInventario.actualizarLineaBien(this.lineaBienDataManager.getLineaBienEditar());
			if (lineaBien != null) {
				this.lineaBienDataManager.setLineaBienEditar(new LineaBien());
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.toString());
			slf4jLogger.info("Error al actualizarLineaBien {}", e.toString());
		}
	}
	
	public void buscarLineaBien () {
		slf4jLogger.info("buscarLineaBien");
		
		try {
			List<LineaBien> listLineaBien = servicioInventario.buscarLineaBienCriterios(this.lineaBienDataManager.getLineaBienBuscar());
			if (CollectionUtils.isEmpty(listLineaBien)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				this.lineaBienDataManager.getListLineaBien().clear();
			} else {
				this.lineaBienDataManager.setListLineaBien(listLineaBien);
			}
			
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void desactivarLineaBien () {
		slf4jLogger.info("desactivarLineaBien");
		
		try {
			this.lineaBienDataManager.getLineaBienEditar().setUsuarioRegistro(this.lineaBienDataManager.getUsuarioSession());
			this.lineaBienDataManager.getLineaBienEditar().setLinBienEstado(this.lineaBienDataManager.getEstadoInactivo());
			LineaBien lineaBien = this.servicioInventario.actualizarLineaBien(this.lineaBienDataManager.getLineaBienEditar());
			if (lineaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void activarLineaBien () {
		slf4jLogger.info("aactivarLineaBien");
		
		try {
			this.lineaBienDataManager.getLineaBienEditar().setUsuarioRegistro(this.lineaBienDataManager.getUsuarioSession());
			this.lineaBienDataManager.getLineaBienEditar().setLinBienEstado(this.lineaBienDataManager.getEstadoActivo());
			LineaBien lineaBien = this.servicioInventario.actualizarLineaBien(this.lineaBienDataManager.getLineaBienEditar());
			if (lineaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.update.exito");
			}
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void limpiarFiltrosBusqueda () {
		this.lineaBienDataManager.setLineaBienBuscar(new LineaBien());
	}
	
	/**
	 * @return the dcCategoriaBien
	 * @throws SeguridadesException 
	 */
	public List<SelectItem> getDcCategoriaBien() throws SeguridadesException {
		
//		if (dcCategoriaBien == null) {
//			slf4jLogger.info("getDcCategoriaBien");
//			dcCategoriaBien = UtilSelectItems.getInstancia().cargarSelectItemCategoriaBien(servicioInventario);
//		}
		
		return dcCategoriaBien;
	}

	/**
	 * @param dcCategoriaBien the dcCategoriaBien to set
	 */
	public void setDcCategoriaBien(List<SelectItem> dcCategoriaBien) {
		this.dcCategoriaBien = dcCategoriaBien;
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
