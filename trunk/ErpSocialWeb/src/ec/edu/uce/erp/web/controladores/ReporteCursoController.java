package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepNivelEstudianteDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ReporteCursoDataManager;

@ViewScoped
@ManagedBean (name = "reporteCursoController")
public class ReporteCursoController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{reporteCursoDataManager}")
	private ReporteCursoDataManager reporteCursoDataManager;


	public ReporteCursoController() {
	
	}
	
	public ReporteCursoDataManager getReporteCursoDataManager() {
		return reporteCursoDataManager;
	}

	public void setReporteCursoDataManager(
			ReporteCursoDataManager reporteCursoDataManager) {
		this.reporteCursoDataManager = reporteCursoDataManager;
	}

	@PostConstruct
	private void init(){
		buscarNivel();
	}
	
	public void buscarNivel () {
		List<NivelDTO> listaNivel=null;
		
		try {							
			listaNivel = this.servicioMatricula.buscarNivel(new NivelDTO());
			this.reporteCursoDataManager.setNivelList(listaNivel);
			buscar();
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	
	public void buscarNivelParalelo () {
		List<NivelParaleloDTO> listaNivelParalelo=null;
		NivelParaleloDTO nivelParaleloDTO;
		NivelDTO nivelDTO;
		try {
			nivelParaleloDTO=new NivelParaleloDTO();
			nivelDTO=new NivelDTO();
			nivelDTO.setNivCodigo(reporteCursoDataManager.getNivelCodigo());
			nivelParaleloDTO.setMatNivel(nivelDTO);
			listaNivelParalelo = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDTO);			
			this.reporteCursoDataManager.setNivelParaleloList(listaNivelParalelo);
			buscar();
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	public void buscar()
	{
		RepNivelEstudianteDTO rep;
		try {
			rep=new RepNivelEstudianteDTO();
			rep.setNpaNivel(reporteCursoDataManager.getNivelCodigo());
			rep.setNpaParalelo(reporteCursoDataManager.getParaleloCodigo());
			reporteCursoDataManager.setRepNivelEstudianteList(servicioMatricula.readNivelEstudiante(rep));
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
}
