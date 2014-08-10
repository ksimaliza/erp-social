/**
 * 
 */
package ec.edu.uce.erp.web.controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.servicio.ServicioInventario;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.controladores.componentes.BuscarUsuarioComponent;
import ec.edu.uce.erp.web.datamanager.VistaHistoricoTransaccionDataManager;

/**
 * @author
 *
 */
@ViewScoped
@ManagedBean(name="reporteInventarioController")
public class ReporteInventarioController extends BaseController {
	
	private static final long serialVersionUID = 1L;
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteInventarioController.class);
	
	@EJB
	private ServicioInventario servicioInventario;
	
	/**
	 * Exportar datos del reporte
	 */
	private String tipoReporte = null;
	
	private BuscarUsuarioComponent buscarUsuarioComponent;
	
	@ManagedProperty(value="#{vistaHistoricoTransaccionDataManager}")
	private VistaHistoricoTransaccionDataManager vistaHistoricoTransaccionDataManager;
	
	/**
	 * @param vistaHistoricoTransaccionDataManager the vistaHistoricoTransaccionDataManager to set
	 */
	public void setVistaHistoricoTransaccionDataManager(
			VistaHistoricoTransaccionDataManager vistaHistoricoTransaccionDataManager) {
		this.vistaHistoricoTransaccionDataManager = vistaHistoricoTransaccionDataManager;
	}
	
	public ReporteInventarioController () {}
	
	@PostConstruct
	public void inicializarObjetos() {
		this.buscarUsuarioComponent = new BuscarUsuarioComponent(servicioInventario);
	}
	
	public void generarReporte (String formatoReporte) {
		slf4jLogger.info("generarReporte...");
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

	/**
	 * @return the buscarUsuarioComponent
	 */
	public BuscarUsuarioComponent getBuscarUsuarioComponent() {
		return buscarUsuarioComponent;
	}

	/**
	 * @param buscarUsuarioComponent the buscarUsuarioComponent to set
	 */
	public void setBuscarUsuarioComponent(
			BuscarUsuarioComponent buscarUsuarioComponent) {
		this.buscarUsuarioComponent = buscarUsuarioComponent;
	}

	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
