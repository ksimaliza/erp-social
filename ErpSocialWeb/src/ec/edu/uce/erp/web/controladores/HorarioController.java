package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
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
	
	
	public HorarioDataManager getHorarioDataManager() {
		return horarioDataManager;
	}

	public void setHorarioDataManager(HorarioDataManager horarioDataManager) {
		this.horarioDataManager = horarioDataManager;
	}


	public void guardar()
	{
		
	}
	
}
