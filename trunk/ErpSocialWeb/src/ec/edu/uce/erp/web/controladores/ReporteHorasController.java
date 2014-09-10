package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.Calendar;
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
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorasTrabajadasListDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
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

	
	public void buscarEmpleado()
	{
		try {
			reporteHorasDataManager.setRegistroList(this.servicioAsistencia.readAtraso(this.reporteHorasDataManager.getRegistroListDTO()));
			this.reporteHorasDataManager.setRegistroListDTO(new EmpleadoAtrasoListDTO());
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	public void buscar() {
		slf4jLogger.info("buscarHoras");
		List<EmpleadoAtrasoListDTO> listResultado=new ArrayList<EmpleadoAtrasoListDTO>();
		FiltroFechaDTO filtro;
		;
		try {
			filtro=new FiltroFechaDTO();
			if(this.reporteHorasDataManager.getFecha()!=null)
			{	
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.reporteHorasDataManager.getFecha());
				
				int year = cal.get(Calendar.YEAR);
			    int month = cal.get(Calendar.MONTH);
			    int day = cal.get(Calendar.DAY_OF_MONTH);
			    
			    filtro.setAnio(year);
			    filtro.setMes(month+1);
			    filtro.setDia(day);
			}
			
			listResultado = this.servicioAsistencia.buscarHoras(reporteHorasDataManager.getRegistroListDTO(), filtro);
				
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.reporteHorasDataManager.setEmpleadoList(listResultado);
							
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarHoras {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void registros(EmpleadoAtrasoListDTO horasTrabajadasListDTO)
	{
		HorasTrabajadasListDTO horasTrabajadasListDTO2;
		try {
			horasTrabajadasListDTO2=new HorasTrabajadasListDTO();
			horasTrabajadasListDTO2.setRasEmpleado(horasTrabajadasListDTO.getRasEmpleado());
			reporteHorasDataManager.setHorasTrabajadasListDTOs(this.servicioAsistencia.readHoras(horasTrabajadasListDTO2));
						
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar registro {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}

	}
	
	
	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	

}
