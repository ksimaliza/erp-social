package ec.edu.uce.erp.web.controladores;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ReporteAtrasoDataManager;

@ViewScoped
@ManagedBean(name="reporteAtrasoController")
public class ReporteAtrasoController extends BaseController {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteAtrasoController.class);
	private static final long serialVersionUID = 1L;

	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{reporteAtrasoDataManager}")
	private ReporteAtrasoDataManager reporteAtrasoDataManager;


	
	public ReporteAtrasoController() {
	
	}

	@PostConstruct
	private void init(){
		
	}
	
	public ReporteAtrasoDataManager getReporteAtrasoDataManager() {
		return reporteAtrasoDataManager;
	}


	public void setReporteAtrasoDataManager(
			ReporteAtrasoDataManager reporteAtrasoDataManager) {
		this.reporteAtrasoDataManager = reporteAtrasoDataManager;
	}
	
	
	public void buscarEmpleado()
	{
		try {
			reporteAtrasoDataManager.setAtrasoList(this.servicioAsistencia.readAtraso(this.reporteAtrasoDataManager.getEmpleadoAtrasoListDTO()));
			this.reporteAtrasoDataManager.setEmpleadoAtrasoListDTO(new EmpleadoAtrasoListDTO());
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void atrasos(EmpleadoAtrasoListDTO atrasoListDTO)
	{
		EmpleadoAtrasoListDTO atrasoListDTO2;
		try {
			atrasoListDTO2=new EmpleadoAtrasoListDTO();
			atrasoListDTO2.setAemEmpleado(atrasoListDTO.getAemEmpleado());
			reporteAtrasoDataManager.setAtrasoList2(this.servicioAsistencia.readAtraso(atrasoListDTO2));
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
