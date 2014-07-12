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
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.VistaHistoricoTransaccionDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "vistaHistoricoTransaccionController")
public class VistaHistoricoTransaccionController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaHistoricoTransaccionController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{historicoTransaccionDataManager}")
	private VistaHistoricoTransaccionDataManager historicoTransaccionDataManager;

	/**
	 * @param historicoTransaccionDataManager the historicoTransaccionDataManager to set
	 */
	public void setHistoricoTransaccionDataManager(
			VistaHistoricoTransaccionDataManager historicoTransaccionDataManager) {
		this.historicoTransaccionDataManager = historicoTransaccionDataManager;
	}
	
	public VistaHistoricoTransaccionController () {}
	
	public void buscarHistoricoTransaccion () {
		
		slf4jLogger.info("buscarHistoricoTransaccion");
		
		try {
			
			List<VistaHistoricoTransaccion> listVistaHistoricoTransaccions = servicioAdministracion
					.obtenerVistaHistoricoTransaccion(this.historicoTransaccionDataManager.getHistoricoTransaccionBuscar());
			this.historicoTransaccionDataManager.getListaVistaHistoricoTransaccion().clear();
			
			if (CollectionUtils.isEmpty(listVistaHistoricoTransaccions)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.historicoTransaccionDataManager.setListaVistaHistoricoTransaccion(listVistaHistoricoTransaccions);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al  buscarHistoricoTransaccion {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo obtener los registros de la base de datos");
		}
		
	}

}
