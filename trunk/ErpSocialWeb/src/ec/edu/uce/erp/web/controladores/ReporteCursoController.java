package ec.edu.uce.erp.web.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.logging.impl.Slf4jLogger;

import net.sf.jasperreports.engine.JasperPrint;
import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepNivelEstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
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
	List<MatriculaVieDTO> list=new ArrayList<MatriculaVieDTO>();
	
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
		NivelDTO nivel;
		try {			
			nivel=new NivelDTO();
			nivel.setNivEmpresa(getEmpresaCode());
			listaNivel = this.servicioMatricula.buscarNivel(nivel);
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
		rep=new RepNivelEstudianteDTO();
		rep.setNpaNivel(reporteCursoDataManager.getNivelCodigo());
		rep.setNpaParalelo(reporteCursoDataManager.getParaleloCodigo());
		//reporteCursoDataManager.setRepNivelEstudianteList(servicioMatricula.readNivelEstudiante(rep));
	}
	
	
	
	
	public void carnet(RepNivelEstudianteDTO rep)
	{
		MatriculaVieDTO vie;
		try {
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(rep.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readCarnet(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "carnetEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCursoDataManager.getFormatoPdf(), "carnet");
			
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void exportar()
	{
		MatriculaVieDTO vie;
		try {
			vie=new MatriculaVieDTO();
			vie.setNpaNivel(reporteCursoDataManager.getNivelCodigo());
			vie.setNpaParalelo(reporteCursoDataManager.getParaleloCodigo());
			
			List<MatriculaVieDTO> list= servicioMatricula.readRepNivelParalelo(vie);
			
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "reporteEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCursoDataManager.getFormatoPdf(), "reporteEstudiante");

		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void exportarExcel()
	{
		MatriculaVieDTO vie;
		try {
			vie=new MatriculaVieDTO();
			vie.setNpaNivel(reporteCursoDataManager.getNivelCodigo());
			vie.setNpaParalelo(reporteCursoDataManager.getParaleloCodigo());
			
			List<MatriculaVieDTO> list= servicioMatricula.readRepNivelParalelo(vie);
			
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "reporteEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCursoDataManager.getFormatoExcel(), "reporteEstudiante");

		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	
	public void buscarList()
	{
		MatriculaVieDTO vie;
		try {
			vie=new MatriculaVieDTO();
			vie.setNpaNivel(reporteCursoDataManager.getNivelCodigo());
			vie.setNpaParalelo(reporteCursoDataManager.getParaleloCodigo());
			
			list= servicioMatricula.readRepNivelParalelo(vie);
			
			//Map<String, Object> mapParametros = new HashMap<String, Object>();
			//mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
		//	JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "reporteEstudiante", mapParametros);
			//ReporteUtil.generarReporte(jasperPrint, this.reporteCursoDataManager.getFormatoPdf(), "reporteEstudiante");

		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

	public List<MatriculaVieDTO> getList() {
		return list;
	}

	public void setList(List<MatriculaVieDTO> list) {
		this.list = list;
	}
	
}
