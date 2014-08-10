package ec.edu.uce.erp.web.controladores;

import java.util.List;

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
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
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
	
	@ManagedProperty(value="#{empleadoDataManager}")
	private EmpleadoDataManager empleadoDataManager;
	
	public void setEmpleadoDataManager(EmpleadoDataManager empleadoDataManager) {
		this.empleadoDataManager = empleadoDataManager;
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




	@Override
	protected void refrescarFormulario() {
		// TODO Auto-generated method stub
		
	}
	
}
