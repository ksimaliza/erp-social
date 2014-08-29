package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ReporteFaltasDataManager;


@ViewScoped
@ManagedBean(name="reporteFaltaController")
public class ReporteFaltaController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteFaltaController.class);

	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{reporteFaltasDataManager}")
	private ReporteFaltasDataManager reporteFaltasDataManager;


	public ReporteFaltaController() {
	
	}



	@PostConstruct
	private void init(){
		
	}
	

	public ReporteFaltasDataManager getReporteFaltasDataManager() {
		return reporteFaltasDataManager;
	}

	public void setReporteFaltasDataManager(
			ReporteFaltasDataManager reporteFaltasDataManager) {
		this.reporteFaltasDataManager = reporteFaltasDataManager;
	}

	
	public void buscar() {
		slf4jLogger.info("buscarEmpleado");
		List<FaltaListDTO> listResultado=new ArrayList<FaltaListDTO>();
		try {
			reporteFaltasDataManager.getFaltaListDTO();
			listResultado = this.servicioAsistencia.readFalta(this.reporteFaltasDataManager.getFaltaListDTO());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteFaltasDataManager.setFaltasList(listResultado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	

	public void faltas(FaltaListDTO faltaListDTO)
	{
		FaltaListDTO faltaListDTO2;
		try {
			faltaListDTO2=new FaltaListDTO();
			faltaListDTO2.setAemEmpleado(faltaListDTO.getAemEmpleado());
			reporteFaltasDataManager.setFaltasList2(this.servicioAsistencia.readFalta2(faltaListDTO2));
			
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
