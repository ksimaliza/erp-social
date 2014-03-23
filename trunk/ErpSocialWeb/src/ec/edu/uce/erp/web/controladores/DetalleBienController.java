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

import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.web.common.controladores.BaseController;
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
	private InventarioFactory inventarioFactory;
	
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
	}
	
	public void buscarDetalleBien () {
		slf4jLogger.info("buscarDetalleBien");
		int a = 0;
		slf4jLogger.info("buscarDetalleBien {}", a);
	}

}
