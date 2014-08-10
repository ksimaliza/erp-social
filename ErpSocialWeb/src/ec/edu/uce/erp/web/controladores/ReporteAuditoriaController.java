/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.datamanager.VistaHistoricoTransaccionDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean(name="reporteAuditoriaController")
public class ReporteAuditoriaController extends BaseController {
	
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteAuditoriaController.class);
	
	/**
	 * Exportar datos del reporte
	 */
	private String tipoReporte = null;
	
	@ManagedProperty(value="#{vistaHistoricoTransaccionDataManager}")
	private VistaHistoricoTransaccionDataManager vistaHistoricoTransaccionDataManager;
	

	/**
	 * @param vistaHistoricoTransaccionDataManager the vistaHistoricoTransaccionDataManager to set
	 */
	public void setVistaHistoricoTransaccionDataManager(
			VistaHistoricoTransaccionDataManager vistaHistoricoTransaccionDataManager) {
		this.vistaHistoricoTransaccionDataManager = vistaHistoricoTransaccionDataManager;
	}
	
	public ReporteAuditoriaController () {}
	
	@PostConstruct
	public void inicializarObjetos() {}
	
	
	public void generarReporteHistoricoTransaccion () {
		slf4jLogger.info("buscarVistaUsuario");
		
//		try {
//			
//			List<DccvVistaHistoricoTransaccion> listaHistorico = administracionService
//					.obtenerVistaHistoricoTransaccion(vistaHistoricoTransaccionDataManager
//							.getVistaHistoricoTransaccionCriterios());
//			
//			if (CollectionUtils.isEmpty(listaHistorico) && listaHistorico.size() == 0) {
//				aniadirMensajeAdvertencia("ec.edu.uce.dcc.reporte.busqueda.vacia");
//			} else {
//				JasperPrint jp = ReporteUtil.jasperPrint(getFacesContext(), listaHistorico, "auditoriaTransacciones");
//				ReporteUtil.generarReporte(jp, getTipoReporte(), "reporteAuditoriaUsuarios");
//			}
//			
//		} catch (DccException e) {
//			slf4jLogger.info("No se pudo generarReporteHistoricoTransaccion: {} {}", e.toString(), e.getCause());
//			aniadirMensajeError("ec.edu.uce.dcc.error.generar.reporte");
//		}	catch (Exception e) {
//			slf4jLogger.info("No se pudo generarReporteHistoricoTransaccion: {} {}", e.toString(), e.getCause());
//			aniadirMensajeError("ec.edu.uce.dcc.error.generar.reporte");
//		}
		
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
