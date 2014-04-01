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

import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
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
	
	private Integer idLineaBienSeleccionado;
	
	/**
	 * @param bienDataManager the bienDataManager to set
	 */
	public void setBienDataManager(BienDataManager bienDataManager) {
		this.bienDataManager = bienDataManager;
	}
	
	public BienController () {}
	
	public void registrarBien () {
		slf4jLogger.info("registrarBien");
	}
	
	public void editarBien () {
		slf4jLogger.info("editarBien");
	}
	
	public void buscarBienes () {
		slf4jLogger.info("registrarBien");
	}
	
	/**
	 * @return the idLineaBienSeleccionado
	 */
	public Integer getIdLineaBienSeleccionado() {
		return idLineaBienSeleccionado;
	}

	/**
	 * @param idLineaBienSeleccionado the idLineaBienSeleccionado to set
	 */
	public void setIdLineaBienSeleccionado(Integer idLineaBienSeleccionado) {
		this.idLineaBienSeleccionado = idLineaBienSeleccionado;
	}

}
