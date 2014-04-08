package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.BaseController;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
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
		Usuario user = null;
		try {
			if(getSessionParameter("usuario")!=null)
				user=(Usuario) getSessionParameter("usuario");
			else
				MensajesWebController.aniadirMensajeError("No se ha establecido empresa");
			empleadoVO=new EmpleadoVO();
			empleadoDataManager.getEmpleadoInsertar().setEmpresaTbl(user.getEmpresaTbl());
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
	
	
}
