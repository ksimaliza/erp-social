package ec.edu.uce.erp.web.controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.datamanager.ReporteHorasDataManager;

@ViewScoped
@ManagedBean(name="reporteHorasController")
public class ReporteHorasController extends BaseController {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteHorasController.class);
	private static final long serialVersionUID = 1L;

	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{reporteHorasDataManager}")
	private ReporteHorasDataManager reporteHorasDataManager;
	
	
	public ReporteHorasController() {
		
	}

	@PostConstruct
	private void init(){
		
	}
	
		
	public ReporteHorasDataManager getReporteHorasDataManager() {
		return reporteHorasDataManager;
	}

	public void setReporteHorasDataManager(
			ReporteHorasDataManager reporteHorasDataManager) {
		this.reporteHorasDataManager = reporteHorasDataManager;
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
