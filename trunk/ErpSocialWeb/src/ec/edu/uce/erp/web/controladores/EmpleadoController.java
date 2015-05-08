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
import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAdministracion;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.common.util.JsfUtil;
import ec.edu.uce.erp.web.datamanager.EmpleadoDataManager;


@ViewScoped
@ManagedBean (name = "empleadoController")
public class EmpleadoController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpleadoController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@EJB
	private ServicioAdministracion servicioAdministracion;
	
	@ManagedProperty(value="#{empleadoDataManager}")
	private EmpleadoDataManager empleadoDataManager;
	
	public EmpleadoDataManager getEmpleadoDataManager() {
		return empleadoDataManager;
	}

	public void setEmpleadoDataManager(EmpleadoDataManager empleadoDataManager) {
		this.empleadoDataManager = empleadoDataManager;
	}
	
	@PostConstruct
	private void init()
	{
		readTipo();	
	}
	

	public void registrarEmpleado () {
		
		slf4jLogger.info("registrarEmpleado");
		EmpleadoVO empleadoVO;

		try {
			empleadoDataManager.getEmpleadoDTOInsertar().setAemEmpresa(getEmpresaCode());
			empleadoVO=new EmpleadoVO();
			empleadoDataManager.getEmpleadoInsertar().setEmrFk(getEmpresaCode());
			empleadoDataManager.getEmpleadoDTOInsertar().setAemEmpleado(getEmpresaCode());
			empleadoVO.setEmpleado(empleadoDataManager.getEmpleadoInsertar());
			empleadoVO.setEmpleadoDTO(empleadoDataManager.getEmpleadoDTOInsertar());
			empleadoVO.setPersona(empleadoDataManager.getPersonaInsertar());
			
			EmpleadoDTO empleadoNuevo = this.servicioAsistencia.createOrUpdateEmpleado(empleadoVO);
							
			if (empleadoNuevo != null) {
				empleadoDataManager.setEmpleadoDTOInsertar(new EmpleadoDTO());
				empleadoDataManager.setEmpleadoInsertar(new Empleado());
				empleadoDataManager.setPersonaInsertar(new Persona());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.empleado.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	
	public void buscar() {
		slf4jLogger.info("buscar");
		List<EmpleadoListDTO> listResultado=null;
		try {
			listResultado = this.servicioAsistencia.readEmpleado(empleadoDataManager.getEmpleadoBuscar());
			
			if (CollectionUtils.isEmpty(listResultado) && listResultado.size()==0) {
				MensajesWebController.aniadirMensajeAdvertencia("erp.mensaje.busqueda.vacia");
			} else {
				this.empleadoDataManager.setEmpleadoList(listResultado);
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al buscar el empleado {} ", e);
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		empleadoDataManager.getPersonaInsertar().setPerFoto(JsfUtil.saveToDiskUpdload(event.getFile().getContents(), JsfUtil.getRandomName(event.getFile().getFileName().split("\\.")[1])));
		empleadoDataManager.getPersonaInsertar().setPerFotoByte(event.getFile().getContents());
    }

	public void cargarDatosEmpleado (EmpleadoListDTO empleado) {
		try {
			EmpleadoVO empleadoEncontrado=servicioAsistencia.obtenerEmpleadoPorId(empleado);
			this.empleadoDataManager.setEmpleadoDTOInsertar(empleadoEncontrado.getEmpleadoDTO());
			this.empleadoDataManager.setEmpleadoInsertar(empleadoEncontrado.getEmpleado());
			this.empleadoDataManager.setPersonaInsertar(empleadoEncontrado.getPersona());
							
		} catch (SeguridadesException e) {
			slf4jLogger.info("Error al cargarDatosEmpleado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError("Error al cargarDatosEmpleado seleccionado");
		}
	}
	
	public void buscarPersonaEmpleado()
	{
		List<Persona> personaList;
		try {
			personaList=this.servicioAdministracion.buscarPersona(empleadoDataManager.getPersonaInsertar());
			if(personaList.size()>0)
				empleadoDataManager.setPersonaInsertar(personaList.get(0));
		} catch (SeguridadesException e) {
			slf4jLogger.info("buscarPersonaEmpleado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
	}

	private void readTipo()
	{
		TipoDTO tipoDTO;
		try {
			tipoDTO=new TipoDTO();
			tipoDTO.setTipEmpresa(getEmpresaCode());
			empleadoDataManager.setTipoList(servicioAsistencia.readTipo(tipoDTO));
		} catch (SeguridadesException e) {
			slf4jLogger.info("buscarPersonaEmpleado {}", e.getMessage());
			MensajesWebController.aniadirMensajeError(e.toString());
		}
		finally{
			tipoDTO=null;
		}
	}

	@Override
	public void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
