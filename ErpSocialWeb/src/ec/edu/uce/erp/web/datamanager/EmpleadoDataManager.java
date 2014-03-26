package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "empleadoDataManager")
public class EmpleadoDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private Persona personaInsertar;
	private Empleado empleadoInsertar;
	private EmpleadoDTO empleadoDTOInsertar;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		personaInsertar=new Persona();
		empleadoDTOInsertar=new EmpleadoDTO();  
		empleadoInsertar=new Empleado();
	}

	public Persona getPersonaInsertar() {
		return personaInsertar;
	}

	public void setPersonaInsertar(Persona personaInsertar) {
		this.personaInsertar = personaInsertar;
	}

	public Empleado getEmpleadoInsertar() {
		return empleadoInsertar;
	}

	public void setEmpleadoInsertar(Empleado empleadoInsertar) {
		this.empleadoInsertar = empleadoInsertar;
	}

	public EmpleadoDTO getEmpleadoDTOInsertar() {
		return empleadoDTOInsertar;
	}

	public void setEmpleadoDTOInsertar(EmpleadoDTO empleadoDTOInsertar) {
		this.empleadoDTOInsertar = empleadoDTOInsertar;
	}

	
	
}
