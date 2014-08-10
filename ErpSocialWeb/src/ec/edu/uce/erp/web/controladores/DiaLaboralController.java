package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
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
	
    public ScheduleEvent getEvent() {
		return event;
	}


	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}


	public ScheduleModel getEventModel() {
        return eventModel;
    }
	
    
	public void sabadoDomingo()
	{
		slf4jLogger.info("sabadoDomingo");
		try {
			servicioAsistencia.createDiaNoLaboralSabadoDomingo(Integer.valueOf(diaLaboralDataManager.getAnio().toString()));
			sabadoDomingoLoad();
			MensajesWebController.aniadirMensajeInformacion("Generado Exitosamente");
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeInformacion(e.toString());
		}
	}
	
	private void sabadoDomingoLoad()
	{
		List<DiaNoLaboralDTO> listNoLaboral;
		String anio, mes, dia;
		SimpleDateFormat dateFormat;
		Date desde,hasta;
		try {
			
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			listNoLaboral= servicioAsistencia.readDiaNoLaboral(CalendarUtil.getYear());
			eventModel=new  DefaultScheduleModel();
			for(DiaNoLaboralDTO diaNo:listNoLaboral)
			{
				anio=diaNo.getDnlAnio().toString();
				mes=diaNo.getDnlMes().toString().length()==1?"0"+diaNo.getDnlMes().toString():diaNo.getDnlMes().toString();
				dia=diaNo.getDnlDia().toString().length()==1?"0"+diaNo.getDnlDia().toString():diaNo.getDnlDia().toString();
				desde=dateFormat.parse(anio+"-"+mes+"-"+dia+" 01:00:00");
				hasta=dateFormat.parse(anio+"-"+mes+"-"+dia+" 23:00:00");
				eventModel.addEvent(new DefaultScheduleEvent(diaNo.getDnlObservacion(), desde,hasta));
			}
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		} catch (ParseException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void agregarDia()
	{		
		try {
			diaLaboralDataManager.getDiaNoLaboral().setDnlAnio(CalendarUtil.getYear(new Timestamp(this.event.getStartDate().getTime())));
			diaLaboralDataManager.getDiaNoLaboral().setDnlMes(CalendarUtil.getMonth(new Timestamp(this.event.getStartDate().getTime())));
			diaLaboralDataManager.getDiaNoLaboral().setDnlDia(CalendarUtil.getDay(new Timestamp(this.event.getStartDate().getTime())));
			servicioAsistencia.createDiaNoLaboral(diaLaboralDataManager.getDiaNoLaboral());
			diaLaboralDataManager.setDiaNoLaboral(new DiaNoLaboralDTO());
			sabadoDomingoLoad();
			MensajesWebController.aniadirMensajeError("Guardado Exitosamente");
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
    public void onDateSelect(SelectEvent selectEvent) {
        try {
            event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
			diaLaboralDataManager.setFecha(event.getStartDate());
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
    }


	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
