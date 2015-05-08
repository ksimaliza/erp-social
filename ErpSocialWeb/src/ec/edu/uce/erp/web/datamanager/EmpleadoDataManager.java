package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "empleadoDataManager")
public class EmpleadoDataManager extends BaseDataManager {
	
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private Persona personaInsertar;
	private Empleado empleadoInsertar;
	private EmpleadoDTO empleadoDTOInsertar;
	
	private EmpleadoListDTO empleadoBuscar;
	private List<EmpleadoListDTO> empleadoList;
	
	private List<TipoDTO> tipoList;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		personaInsertar=new Persona();
		empleadoDTOInsertar=new EmpleadoDTO();  
		empleadoInsertar=new Empleado();
		empleadoList=new ArrayList<EmpleadoListDTO>();
		empleadoBuscar=new EmpleadoListDTO();
		tipoList=new ArrayList<TipoDTO>();
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

	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public EmpleadoListDTO getEmpleadoBuscar() {
		return empleadoBuscar;
	}

	public void setEmpleadoBuscar(EmpleadoListDTO empleadoBuscar) {
		this.empleadoBuscar = empleadoBuscar;
	}

	public List<TipoDTO> getTipoList() {
		return tipoList;
	}

	public void setTipoList(List<TipoDTO> tipoList) {
		this.tipoList = tipoList;
	}
	
}
