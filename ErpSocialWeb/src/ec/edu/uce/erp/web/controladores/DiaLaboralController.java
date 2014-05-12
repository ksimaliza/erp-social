package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.CalendarUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.DiaLaboralDataManager;

@ViewScoped
@ManagedBean (name = "diaLaboralController")
public class DiaLaboralController extends BaseController{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DiaLaboralController.class);
	
	@EJB
	public ServicioAsistencia servicioAsistencia;

	
	@ManagedProperty(value="#{diaLaboralDataManager}")
	private DiaLaboralDataManager diaLaboralDataManager;


	public DiaLaboralDataManager getDiaLaboralDataManager() {
		return diaLaboralDataManager;
	}


	public void setDiaLaboralDataManager(DiaLaboralDataManager diaLaboralDataManager) {
		this.diaLaboralDataManager = diaLaboralDataManager;
	}

	
	private ScheduleModel eventModel;
    
    private ScheduleEvent event = new DefaultScheduleEvent();
	
	public DiaLaboralController() {
	
	}
	
	@PostConstruct
	private void init()
	{
		sabadoDomingoLoad();
	}
	
	
	public void sabadoDomingo()
	{
		slf4jLogger.info("sabadoDomingo");
		try {
			servicioAsistencia.createDiaNoLaboralSabadoDomingo(Integer.valueOf(diaLaboralDataManager.getAnio().toString()));
			MensajesWebController.aniadirMensajeInformacion("Generado Exitosamente");
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeInformacion(e.toString());
		}
	}
	
	private void sabadoDomingoLoad()
	{
		List<DiaNoLaboralDTO> listNoLaboral;
		try {
			listNoLaboral= servicioAsistencia.readDiaNoLaboral(CalendarUtil.getYear());
			eventModel=new  DefaultScheduleModel();
			//eventModel.addEvent(new DefaultScheduleEvent("Hola", start, end));
		} catch (SeguridadesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
