package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "registroAsistenciaDataManager")
public class RegistroAsistenciaDataManager extends BaseDataManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger slf4jLogger = LoggerFactory.getLogger(RegistroAsistenciaDataManager.class);
	
	private RegistroDTO registro;
	
	private EmpleadoDTO empleado;
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		registro=new RegistroDTO();
		empleado=new EmpleadoDTO();
	}

	public RegistroDTO getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroDTO registro) {
		this.registro = registro;
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}
	
}
