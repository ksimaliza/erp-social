/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesReporte;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaUsuario;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.VistaUsuarioDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "vistaUsuarioController")
public class VistaUsuarioController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaUsuarioController.class);
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{vistaUsuarioDataManager}")
	private VistaUsuarioDataManager vistaUsuarioDataManager;

	/**
	 * @param vistaUsuarioDataManager the vistaUsuarioDataManager to set
	 */
	public void setVistaUsuarioDataManager(VistaUsuarioDataManager vistaUsuarioDataManager) {
		this.vistaUsuarioDataManager = vistaUsuarioDataManager;
	}
	
	public VistaUsuarioController () {}
	
	public void buscarVistaUsuario () {
		slf4jLogger.info("buscarVistaUsuario");
		
		try {
			
			this.vistaUsuarioDataManager.getVistaUsuarioBuscar().setIdUsuario((this.vistaUsuarioDataManager.getUsuarioSession().getIdUsuario()));
			List<VistaUsuario> listVistaUsuario = servicioAdministracion.obtenerVistaUsuario(this.vistaUsuarioDataManager.getVistaUsuarioBuscar());
			this.vistaUsuarioDataManager.getListVistaUsuario().clear();
			
			if (CollectionUtils.isEmpty(listVistaUsuario)) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.vistaUsuarioDataManager.setListVistaUsuario(listVistaUsuario);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("error al  buscarUsuario {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo obtener los usuario de la base de datos");
		}
	}
	
	public void generarPdfLista (String tableId) {
		
		try {
			
			Map<String, String> mapValuesPDF = new HashMap<String, String>();
			mapValuesPDF.put(ConstantesReporte.TITULO, "Reporte de usuarios");
			mapValuesPDF.put(ConstantesReporte.NOMBRE_ARCHIVO, "ReporteUsuarios");
			this.exportPDF(tableId, mapValuesPDF);
			
		} catch (IOException e) {
			slf4jLogger.info("error al  buscarVistaUsuario {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("No se pudo generar el reporte");
		}
	}

}
