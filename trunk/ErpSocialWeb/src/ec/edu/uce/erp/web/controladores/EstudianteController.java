package ec.edu.uce.erp.web.controladores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EstudianteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioMatricula;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.EstudianteDataManager;


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
		
		@ManagedProperty(value="#{estudianteDataManager}")
		private EstudianteDataManager estudianteDataManager;
		
		private List<Object> estudiantesSeleccionados;
		
		public void setEstudianteDataManager(EstudianteDataManager estudianteDataManager) {
			this.estudianteDataManager = estudianteDataManager;
		}
		

		public EstudianteController () {}
		
		@PostConstruct
		public void inicializarObjetos () {
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
				

				EstudianteDTO estudianteNuevo = this.servicioMatricula.createOrUpdateEstudiante(estudianteVO);
				if (estudianteNuevo != null) {
					estudianteDataManager.setEstudianteInstancia(new EstudianteDTO());
					estudianteDataManager.setEstudiantePersonaInsertar(new Persona());
					MensajesWebController.aniadirMensajeInformacion("erp.matricula.estudiante.registrar.exito");
				}
				
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
				EstudianteVO estudianteEncontrado = servicioMatricula.obtenerEstudiantePorId(estudiante.getEstPersona(),estudiante.getEstCodigo());
				
				this.estudianteDataManager.setEstudiantePersonaInsertar(estudianteEncontrado.getPersona());
				this.estudianteDataManager.setEstudianteInstancia(estudianteEncontrado.getEstudiante());
								
			} catch (SeguridadesException e) {
				slf4jLogger.info("Error al cargar los datos del estudiante seleccionado {}", e.getMessage());
				MensajesWebController.aniadirMensajeError("Error al cargar los datos del estudiante seleccionado");
			}
		}
		
		public void handleFileUpload(FileUploadEvent event) {
			estudianteDataManager.getEstudiantePersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), JsfUtil.getRandomName(event.getFile().getFileName().split("\\.")[1])));
			estudianteDataManager.getEstudiantePersonaInsertar().setPerFotoByte(event.getFile().getContents());
	    }


		@Override
		protected void refrescarFormulario() {
			// TODO Auto-generated method stub
			
		}

}
		
		

