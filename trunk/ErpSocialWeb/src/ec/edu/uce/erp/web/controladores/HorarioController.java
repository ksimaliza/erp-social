package ec.edu.uce.erp.web.controladores;

import java.sql.Timestamp;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.common.util.CalendarUtil;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.HorarioDataManager;


@ViewScoped
@ManagedBean (name = "horarioController")
public class HorarioController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{horarioDataManager}")
	private HorarioDataManager horarioDataManager;
	
	public HorarioController() {
	
	}
	
	@PostConstruct
	private void init()
	{
		timesRead();
	}
	
	public HorarioDataManager getHorarioDataManager() {
		return horarioDataManager;
	}

	public void setHorarioDataManager(HorarioDataManager horarioDataManager) {
		this.horarioDataManager = horarioDataManager;
	}


	public void guardar()
	{
		HorarioDTO horarioDTO;
		DiaDTO diaDTO;
		TipoDTO tipoDTO;
		try {
			horarioDTO=new HorarioDTO();
			diaDTO=new DiaDTO();
			tipoDTO=new TipoDTO();
			
			diaDTO.setDiaCodigo(horarioDataManager.getDiaCode());
			tipoDTO.setTipCodigo(horarioDataManager.getTipoCode());
			
			horarioDTO.setHorEmpresa(getEmpresaCode());
			horarioDTO.setAsiDia(diaDTO);
			horarioDTO.setAsiTipo(tipoDTO);
			horarioDTO.setHorHoraInicio(
					new Timestamp(
							CalendarUtil.getCalendar(Integer.valueOf(horarioDataManager.getHoraDesdeCode()),
									Integer.valueOf(horarioDataManager.getMinutoDesdeCode())).getTimeInMillis()
									));

			horarioDTO.setHotHoraFin(
					new Timestamp(
							CalendarUtil.getCalendar(Integer.valueOf(horarioDataManager.getHoraHastaCode()),
									Integer.valueOf(horarioDataManager.getMinutoHastaCode())).getTimeInMillis()
									));

			
			servicioAsistencia.createOrUpdateHorario(horarioDTO);			
		} catch (Exception e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	public void buscar()
	{
		HorarioDTO horarioDTO;
		TipoDTO tipoDTO;
		try {
			horarioDTO=new HorarioDTO();
			tipoDTO=new TipoDTO();
			
			horarioDTO.setHorEmpresa(getEmpresaCode());
			tipoDTO.setTipCodigo(horarioDataManager.getTipoCodeBuscar());
			horarioDTO.setAsiTipo(tipoDTO);
			horarioDataManager.setHorarioList(servicioAsistencia.readHorario(horarioDTO));
		} catch (Exception e) {
			MensajesWebController.addErrorMessage(e.toString());
		}
	}
	
	public void timesRead()
	{
		try {
			horarioDataManager.setHoraDesde(servicioAsistencia.readHour());
			horarioDataManager.setHoraHasta(servicioAsistencia.readHour());
			horarioDataManager.setMinutoDesde(servicioAsistencia.readMinute());
			horarioDataManager.setMinutoHasta(servicioAsistencia.readMinute());
			
			horarioDataManager.setDiaList(servicioAsistencia.readDiaAll());
			
			horarioDataManager.setTipoList(servicioAsistencia.readAllTipo());
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
}
