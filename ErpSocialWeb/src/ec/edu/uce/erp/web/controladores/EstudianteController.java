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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteRepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.common.util.ReporteUtil;
import ec.edu.uce.erp.web.datamanager.EstudianteDataManager;
import ec.edu.uce.erp.web.datamanager.ReporteCarnetDataManager;
import ec.edu.uce.erp.web.datamanager.RepresentanteDataManager;


@ViewScoped
@ManagedBean (name = "estudianteController")
public class EstudianteController extends BaseController{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteController.class);
	
	@EJB
	private ServicioMatricula servicioMatricula;

	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{estudianteDataManager}")
	private EstudianteDataManager estudianteDataManager;
	
	@ManagedProperty(value="#{representanteDataManager}")
	private RepresentanteDataManager representanteDataManager;
	
	private List<Object> estudiantesSeleccionados;

	public EstudianteController () {}
	@ManagedProperty(value="#{reporteCarnetDataManager}")
	private ReporteCarnetDataManager reporteCarnetDataManager;

	
	public ReporteCarnetDataManager getReporteCarnetDataManager() {
		return reporteCarnetDataManager;
	}

	public void setReporteCarnetDataManager(
			ReporteCarnetDataManager reporteCarnetDataManager) {
		this.reporteCarnetDataManager = reporteCarnetDataManager;
	}
	
	private Integer codEstudiante;
	
	@PostConstruct
	public void inicializarObjetos () {
		
	}
	
	/*
	 * Getters Setters
	 */
	public void setRepresentanteDataManager(RepresentanteDataManager representanteDataManager) {
		this.representanteDataManager = representanteDataManager;
	}

	public EstudianteDataManager getEstudianteDataManager() {
		return estudianteDataManager;
	}

	public void setEstudianteDataManager(EstudianteDataManager estudianteDataManager) {
		this.estudianteDataManager = estudianteDataManager;
	}
	
	/*
	 * Medodos
	 */
	
	public void registrarEstudiante () {
		
		slf4jLogger.info("registrarEstudiante");
		EstudianteVO estudianteVO;
		
		try {
			
						
			estudianteVO=new EstudianteVO();
			estudianteDataManager.getEstudianteInstancia().setEstEmpresa(getEmpresaCode());
			estudianteVO.setEstudiante(estudianteDataManager.getEstudianteInstancia());
			estudianteVO.setPersona(estudianteDataManager.getEstudiantePersonaInsertar());
			estudianteVO.setPersonaRepresentante(representanteDataManager.getPersonaInstancia());
			estudianteVO.setPadre(estudianteDataManager.getPadreInsertar());
			estudianteVO.setMadre(estudianteDataManager.getMadreInsertar());
			estudianteVO.setRepresentanteEst(estudianteDataManager.getRepresentanteInsertar());

			EstudianteDTO estudianteNuevo = this.servicioMatricula.createOrUpdateEstudiante(estudianteVO);
			if (estudianteNuevo != null) {
				estudianteDataManager.setEstudianteInstancia(new EstudianteDTO());
				estudianteDataManager.setEstudiantePersonaInsertar(new Persona());
				estudianteDataManager.setRepresentanteInsertar(new Persona());
				representanteDataManager.setPersonaInstancia(new Persona());
				estudianteDataManager.setMadreInsertar(new Persona());
				estudianteDataManager.setPadreInsertar(new Persona());
				estudianteDataManager.setRepresentanteInsertar(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.estudiante.registrar.exito");
			}
			buscarEstudiantes();
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void buscarEstudiantes () {
		slf4jLogger.info("buscarEstudiantes");
		
		List<EstudianteListDTO> listaestudiantes=null;
		
		try {
							
			estudianteDataManager.getEstudianteBuscar().setEstEmpresa(getEmpresaCode());
			listaestudiantes = this.servicioMatricula.buscarEstudiante(estudianteDataManager.getEstudianteBuscar());
			
			if (CollectionUtils.isEmpty(listaestudiantes) && listaestudiantes.size()==0) {
				this.estudianteDataManager.setListaEstudianteListDTOs(listaestudiantes);
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.estudianteDataManager.setListaEstudianteListDTOs(listaestudiantes);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el estudiante {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public List<Object> getEstudiantesSeleccionados() {
		return estudiantesSeleccionados;
	}


	public void setEstudiantesSeleccionados(List<Object> estudiantesSeleccionados) {
		this.estudiantesSeleccionados = estudiantesSeleccionados;
	}
	

	public void cargarDatosEstudiante (EstudianteListDTO estudiante) {
		try {
			EstudianteVO estudianteEncontrado = servicioMatricula.obtenerEstudiantePorId(estudiante.getEstPersona(),estudiante.getEstCodigo(), estudiante.getEstMadre(), estudiante.getEstPadre(), estudiante.getEstRepresentante());
			
			this.estudianteDataManager.setEstudiantePersonaInsertar(estudianteEncontrado.getPersona());
			this.estudianteDataManager.setEstudianteInstancia(estudianteEncontrado.getEstudiante());
			this.estudianteDataManager.setPadreInsertar(estudianteEncontrado.getPadre());
			this.estudianteDataManager.setMadreInsertar(estudianteEncontrado.getMadre());
			this.estudianteDataManager.setRepresentanteInsertar(estudianteEncontrado.getRepresentanteEst());
										
			estudianteEncontrado.getPersona().getPerFotoVerificar();
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargar los datos del estudiante seleccionado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargar los datos del estudiante seleccionado");
		}
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		estudianteDataManager.getEstudiantePersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), event.getFile().getFileName()));
		estudianteDataManager.getEstudiantePersonaInsertar().setPerFotoByte(event.getFile().getContents());
    }

	public void cancel()
	{
		estudianteDataManager.setEstudianteInstancia(new EstudianteDTO());
		estudianteDataManager.setEstudiantePersonaInsertar(new Persona());
		//representanteDataManager.setPersonaInstancia(new Persona());
		estudianteDataManager.setMadreInsertar(new Persona());
		estudianteDataManager.setPadreInsertar(new Persona());
		estudianteDataManager.setRepresentanteInsertar(new Persona());
		RequestContext.getCurrentInstance().execute("dlgNuevoEstudiante.hide()");
	}
			
	public void buscarPersonaEstudiante()
	{
		List<Persona> personaList = null;
		try {
			
			if(estudianteDataManager.getEstudiantePersonaInsertar().getPerCi()!=null ||estudianteDataManager.getEstudiantePersonaInsertar().getPerCi().equals("")){
			personaList=this.servicioAdministracion.buscarPersona(estudianteDataManager.getEstudiantePersonaInsertar());
			if(personaList.size()>0)
				estudianteDataManager.setEstudiantePersonaInsertar(personaList.get(0));
			}
			if (CollectionUtils.isEmpty(personaList)
					&& personaList.size() == 0) {
				MensajesWebController
						.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("buscarRepresentante {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
		
	}
	

	public void buscarPersonaRepresentante()
	{
		List<Persona> personaList;
		try {
			personaList=this.servicioAdministracion.buscarPersona(representanteDataManager.getPersonaInstancia());
			if(personaList.size()>0)
				representanteDataManager.setPersonaInstancia(personaList.get(0));
		} catch (SeguridadesException e) {
			slf4jLogger.info("buscarRepresentante {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}
	
	
	public void certificado(EstudianteListDTO estudiante)
	{
		MatriculaVieDTO vie;
		try {
			
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(estudiante.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readCarnet(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "comprobanteMatriculaEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "matricula");
			
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	public void carnet(EstudianteListDTO estudiante)
	{
		MatriculaVieDTO vie;
		try {
			
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(estudiante.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readCarnet(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "carnetEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "carnet");
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void fichaEstudiantil(EstudianteListDTO estudiante)
	{
		MatriculaVieDTO vie;
		try {
			
			vie=new MatriculaVieDTO();
			vie.setRegCodigo(estudiante.getRegCodigo());
			List<MatriculaVieDTO> list= servicioMatricula.readFicha(vie);
						
			Map<String, Object> mapParametros = new HashMap<String, Object>();
			mapParametros.put("imagesRealPath", getServletContext().getRealPath("resources/img"));
						
			JasperPrint jasperPrint = ReporteUtil.jasperPrint(getFacesContext(), list, "fichaEstudiante", mapParametros);
			ReporteUtil.generarReporte(jasperPrint, this.reporteCarnetDataManager.getFormatoPdf(), "FichaEstudiantil");
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	
	public void buscarMadre() {
		slf4jLogger.info("buscarMadre");

		List<Persona> listaPersona = null;

		try {
			if (estudianteDataManager.getMadreInsertar().getPerCi() != null
					&& estudianteDataManager.getMadreInsertar().getPerCi() != "") {
				estudianteDataManager.getMadreInsertar()
						.setPerNombres(null);
				estudianteDataManager.getMadreInsertar().setPerApellidos(
						null);
				listaPersona = this.servicioAdministracion
						.buscarPersona(estudianteDataManager
								.getMadreInsertar());

				if (CollectionUtils.isEmpty(listaPersona)
						&& listaPersona.size() == 0) {
					MensajesWebController
							.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.estudianteDataManager
							.setMadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarRepresentante() {
		slf4jLogger.info("buscarRepresentante");

		List<Persona> listaPersona = null;

		try {
			if (estudianteDataManager.getRepresentanteInsertar().getPerCi() != null
					&& estudianteDataManager.getRepresentanteInsertar().getPerCi() != "") {
				estudianteDataManager.getRepresentanteInsertar()
						.setPerNombres(null);
				estudianteDataManager.getRepresentanteInsertar().setPerApellidos(
						null);
				listaPersona = this.servicioAdministracion
						.buscarPersona(estudianteDataManager
								.getRepresentanteInsertar());

				if (CollectionUtils.isEmpty(listaPersona)
						&& listaPersona.size() == 0) {
					MensajesWebController
							.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.estudianteDataManager.setRepresentanteInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarMadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	public void buscarPadre() {
		slf4jLogger.info("buscarPadre");

		List<Persona> listaPersona = null;

		try {
			if (estudianteDataManager.getPadreInsertar().getPerCi() != null
					&& estudianteDataManager.getPadreInsertar().getPerCi() != "") {
				estudianteDataManager.getPadreInsertar()
						.setPerNombres(null);
				estudianteDataManager.getPadreInsertar().setPerApellidos(
						null);
				listaPersona = this.servicioAdministracion
						.buscarPersona(estudianteDataManager
								.getPadreInsertar());

				if (CollectionUtils.isEmpty(listaPersona)
						&& listaPersona.size() == 0) {
					MensajesWebController
							.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
				} else {
					this.estudianteDataManager
							.setPadreInsertar(listaPersona.get(0));
				}
			}
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscarPadre {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}
	
	
	//----->>>29/01/2015
	
	public void eliminarEstudiante()
	{
		
		try {
			
			servicioMatricula.eliminarEstudiante(codEstudiante);
			buscarEstudiantes();
			
			
		} catch (SeguridadesException e) {
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
	}


	
	public Integer getCodEstudiante() {
		return codEstudiante;
	}

	public void setCodEstudiante(Integer codEstudiante) {
		this.codEstudiante = codEstudiante;
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
	}
}
		
		