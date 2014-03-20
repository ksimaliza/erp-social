package ec.edu.uce.erp.web.controladores;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.ejb.servicio.ServicioAsistencia;
import ec.edu.uce.erp.web.common.controladores.MensajesWebController;
import ec.edu.uce.erp.web.datamanager.EmpleadoDataManager;


@ViewScoped
@ManagedBean (name = "EmpleadoController")
public class EmpleadoController {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpleadoController.class);
	
	@EJB
	private ServicioAsistencia servicioAsistencia;
	
	@ManagedProperty(value="#{EmpleadoDataManager}")
	private EmpleadoDataManager empleadoDataManager;
	
	public void setEmpleadoDataManager(EmpleadoDataManager empleadoDataManager) {
		this.empleadoDataManager = empleadoDataManager;
	}
	

	public void registrarEmpleado () {
		
		slf4jLogger.info("registrarEmpleado");
		try {
			EmpleadoDTO empleadonuevo = this.servicioAsistencia.createOrUpdateEmpleado(this.empleadoDataManager.getEmpleadoInstancia());
			if (empleadonuevo != null) {
				empleadoDataManager.setEmpleadoInstancia(new EmpleadoVO());
				MensajesWebController.aniadirMensajeInformacion("erp.matricula.empleado.registrar.exito");
			}
			
		} catch (SeguridadesException e) {
			slf4jLogger.info(e.toString());
			MensajesWebController.aniadirMensajeError(e.getMessage());
		}
		
	}

	
}
