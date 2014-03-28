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
			CategoriaBien categoriaBien = this.servicioInventario.registrarCategoriaBien(this.categoriaBienDataManager.getCategoriaBienInstancia());
			
			if (categoriaBien != null) {
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
				this.categoriaBienDataManager.getListCategoriaBien().add(categoriaBien);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarCategoriaBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeInformacion(e.getCause().getMessage());
		}
	}
	
	public void buscarCategoriaBien() {
		slf4jLogger.info("buscarCategoriaBien");
	}



}
