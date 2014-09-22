package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.ReporteComprobanteMatriculaDataManager;

@ViewScoped
@ManagedBean(name="reporteComprobanteMatriculaController")
public class ReporteComprobanteMatriculaController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	@ManagedProperty(value="#{reporteComprobanteMatriculaDataManager}")
	private ReporteComprobanteMatriculaDataManager reporteComprobanteMatriculaDataManager;

	
	public ReporteComprobanteMatriculaController() {
	
	}
	
	public ReporteComprobanteMatriculaDataManager getReporteComprobanteMatriculaDataManager() {
		return reporteComprobanteMatriculaDataManager;
	}


	public void setReporteComprobanteMatriculaDataManager(
			ReporteComprobanteMatriculaDataManager reporteComprobanteMatriculaDataManager) {
		this.reporteComprobanteMatriculaDataManager = reporteComprobanteMatriculaDataManager;
	}



	public void buscarNivel () {
		List<NivelDTO> listaNivel=null;
		NivelDTO nivel;
		try {			
			nivel=new NivelDTO();
			nivel.setNivEmpresa(getEmpresaCode());
			listaNivel = this.servicioMatricula.buscarNivel(nivel);
			this.reporteComprobanteMatriculaDataManager.setNivelList(listaNivel);
			
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
			nivelDTO.setNivCodigo(reporteComprobanteMatriculaDataManager.getNivelCodigo());
			nivelParaleloDTO.setMatNivel(nivelDTO);
			listaNivelParalelo = this.servicioMatricula.buscarNivelParalelo(nivelParaleloDTO);			
			this.reporteComprobanteMatriculaDataManager.setNivelParaleloList(listaNivelParalelo);
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscar()
	{
		try {
			if(reporteComprobanteMatriculaDataManager.getNivelCodigo()!=0)
				reporteComprobanteMatriculaDataManager.getMatriculaVieDTO().setNivCodigo(reporteComprobanteMatriculaDataManager.getNivelCodigo());
			
			reporteComprobanteMatriculaDataManager.setMatriculaVieList(servicioMatricula.readRepComprobanteMatricula(reporteComprobanteMatriculaDataManager.getMatriculaVieDTO()));
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportar(MatriculaVieDTO matriculaVieDTO)
	{
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

}
