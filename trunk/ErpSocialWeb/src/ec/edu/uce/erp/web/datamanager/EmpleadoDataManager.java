package ec.edu.uce.erp.web.datamanager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.vo.EmpleadoVO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "empleadoDataManager")
public class EmpleadoDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private EmpleadoVO empleadoInstancia;
	private EmpleadoVO empleadoEditar;
	private EmpleadoVO empleadoBuscar;
	private Persona empleadopersonainsertar;
	private Empleado empleadoempleadoinsertar;
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.empleadoInstancia = new EmpleadoVO();		
		this.empleadoEditar = new EmpleadoVO();
		this.empleadoBuscar = new EmpleadoVO();
		this.empleadopersonainsertar = new Persona();	
	}

	public EmpleadoVO getEmpleadoInstancia() {
		return empleadoInstancia;
	}

	public void setEmpleadoInstancia(EmpleadoVO empleadoInstancia) {
		this.empleadoInstancia = empleadoInstancia;
	}

	public EmpleadoVO getEmpleadoEditar() {
		return empleadoEditar;
	}

	public void setEmpleadoEditar(EmpleadoVO empleadoEditar) {
		this.empleadoEditar = empleadoEditar;
	}

	public EmpleadoVO getEmpleadoBuscar() {
		return empleadoBuscar;
	}

	public void setEmpleadoBuscar(EmpleadoVO empleadoBuscar) {
		this.empleadoBuscar = empleadoBuscar;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
