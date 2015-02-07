package ec.edu.uce.erp.web.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.ReporteCarnetDataManager;

@ViewScoped
@ManagedBean(name="reporteCarnetController")
public class ReporteCarnetController extends BaseController{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ReporteCarnetController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;
	
	private List<PeriodoDTO> periodoList;
	
	@ManagedProperty(value="#{reporteCarnetDataManager}")
	private ReporteCarnetDataManager reporteCarnetDataManager;

	
	public ReporteCarnetDataManager getReporteCarnetDataManager() {
		return reporteCarnetDataManager;
	}

	public void setReporteCarnetDataManager(
			ReporteCarnetDataManager reporteCarnetDataManager) {
		this.reporteCarnetDataManager = reporteCarnetDataManager;
	}
	
	@PostConstruct
	private void init(){
		
		buscarPeriodo();
		
	}


	public void buscar()
	{
		try
		{
			
			reporteCarnetDataManager.getMatriculaVieDTO().setEstEmpresa(getEmpresaCode());
			if(reporteCarnetDataManager.getMatriculaVieDTO().getMatCodigo()==0)
				reporteCarnetDataManager.getMatriculaVieDTO().setMatCodigo(null);
			reporteCarnetDataManager.setMatriculaVieDTOList(servicioMatricula.readRepCarnetBuscar(reporteCarnetDataManager.getMatriculaVieDTO()));
			//reporteCarnetDataManager.setMatriculaVieDTO(new MatriculaVieDTO());
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	

	public void carnet(MatriculaVieDTO rep)
	{
		MatriculaVieDTO vie;
		try {
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(rep.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readCarnet(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "carnetEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "carnet");
			
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void certificado(MatriculaVieDTO rep)
	{
		MatriculaVieDTO vie;
		try {
			
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(rep.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readCarnet(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "comprobanteMatriculaEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "matricula");
			
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void fichaEstudiantil(MatriculaVieDTO rep)
	{
		MatriculaVieDTO vie;
		try {
			
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(rep.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readFicha(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "fichaEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "FichaEstudiantil");
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void buscarPeriodo () {
		slf4jLogger.info("buscarPeriodo");
		
		List<PeriodoDTO> listaperiodos=null;
		PeriodoDTO periodoDTO;
		
		try {
							
			periodoDTO=new PeriodoDTO();
			periodoDTO.setPerEmpresa(getEmpresaCode());
			listaperiodos = this.servicioMatricula.buscarPeriodo(periodoDTO);
			
			if (CollectionUtils.isEmpty(listaperiodos) && listaperiodos.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.setPeriodoList(listaperiodos);
				
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPeriodo {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}

	public List<PeriodoDTO> getPeriodoList() {
		return periodoList;
	}

	public void setPeriodoList(List<PeriodoDTO> periodoList) {
		this.periodoList = periodoList;
	}

	
	
	
}
