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

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.ConstantesReporte;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.VistaHistoricoTransaccionDataManager;

/**
 * @author 
 *
 */
@ViewScoped
@ManagedBean (name = "historicoTransaccionController")
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
			
			this.historicoTransaccionDataManager
					.getHistoricoTransaccionBuscar().setEmrPk(this.historicoTransaccionDataManager.getUsuarioSession().getEmpresaTbl().getEmrPk());
			
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
	
	public void generarPdfLista (String tableId) {
		
		try {
			
			Map<String, String> mapValuesPDF = new HashMap<String, String>();
			mapValuesPDF.put(ConstantesReporte.TITULO, "Reporte de transacciones");
			mapValuesPDF.put(ConstantesReporte.NOMBRE_ARCHIVO, "ReporteTransacciones");
			this.exportPDF(tableId, mapValuesPDF);
			
		} catch (IOException e) {
			slf4jLogger.info("error al  buscarVistaUsuario {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al generar el reporte");
		}
	}
	
	public void generarReporteUsuario (String formatoReporte) {
		slf4jLogger.info("generarReporteUsuario...");
		if (CollectionUtils.isEmpty(this.historicoTransaccionDataManager.getListaVistaHistoricoTransaccion())) {
			MensajesWebController.aniadirMensajeAdvertencia("No hay datos para generar el reporte");
		} else {
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("nombreEmpresa", this.historicoTransaccionDataManager.getUsuarioSession().getEmpresaTbl().getEmrNombre());
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
			JasperPrint jasperPrint = 
					ReporteUtil.jasperPrint(getFacesContext(), this.historicoTransaccionDataManager.getListaVistaHistoricoTransaccion(), "reporteTransaccionesUsuarios", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, formatoReporte, "reporteTransaccionesUsuarios");
		}
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
