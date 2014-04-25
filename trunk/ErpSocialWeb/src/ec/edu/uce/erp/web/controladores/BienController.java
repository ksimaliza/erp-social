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
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;
import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.BienDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean(name="bienController")
public class BienController extends BaseController{

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(BienController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	@ManagedProperty(value="#{bienDataManager}")
	private BienDataManager bienDataManager;
	
	/**
	 * @param bienDataManager the bienDataManager to set
	 */
	public void setBienDataManager(BienDataManager bienDataManager) {
		this.bienDataManager = bienDataManager;
	}
	
	public BienController () {}
	
	public void registrarBien () {
		
		slf4jLogger.info("registrarBien");
		
		try {
			
			this.bienDataManager.getBienInstancia().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			this.bienDataManager.getBienInstancia().setCatBienPk(this.bienDataManager.getIdCategoriaBienSeleccionado());
			this.bienDataManager.getBienInstancia().setLinBienPk(this.bienDataManager.getIdLineaBienSeleccionado());
			VistaBien vistaBien = servicioInventario.registrarBien(this.bienDataManager.getBienInstancia());
			
			if (vistaBien != null) {
//				this.bienDataManager.getListBien().add(nuevoBien);
				this.bienDataManager.getListVistaBien().add(vistaBien);
				this.bienDataManager.setBienInstancia(new Bien());
				MensajesWebController.aniadirMensajeInformacion("erp.mensaje.registro.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al registrarBien {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}
	
	public void editarBien () {
		slf4jLogger.info("editarBien");
	}
	
	public void buscarBienes () {
		
		slf4jLogger.info("buscarBienes");
		
		try {
			
			this.bienDataManager.getVistaBienBuscar().setEmrPk(this.bienDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			List<VistaBien> listVistaBien = servicioInventario.buscarVistaBienCriterios(this.bienDataManager.getVistaBienBuscar());
			
			this.bienDataManager.getListVistaBien().clear();
			if (CollectionUtils.isNotEmpty(listVistaBien)) {
				this.bienDataManager.setListVistaBien(listVistaBien);
			} else {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al buscarBienes {}", e.getCause().getMessage());
			MensajesWebController.aniadirMensajeError(e.getCause().getMessage());
		}
	}

	
}
