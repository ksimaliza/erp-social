package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "permisoDataManager")
public class PermisoDataManager extends BaseDataManager{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PeriodoDataManager.class);
	
	private static final long serialVersionUID = 1L;
	

	private PermisoDTO permiso;

	private EmpleadoDTO empleado;
	
	private List<EmpleadoListDTO> empleadoList;
	
	private PermisoListDTO permisoBuscar;
	
	private List<PermisoListDTO> permisoList;
 	
	private Date fechaPermiso;
	
	private Object empleadoCodigo;
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		
		empleado=new EmpleadoDTO();
		permiso=new PermisoDTO();
		permisoBuscar=new PermisoListDTO();
		
		empleadoList=new ArrayList<EmpleadoListDTO>();
		
		permisoList=new ArrayList<PermisoListDTO>();
	}

	public PermisoDTO getPermiso() {
		return permiso;
	}

	public void setPermiso(PermisoDTO permiso) {
		this.permiso = permiso;
	}

	public List<EmpleadoListDTO> getEmpleadoList() {
		return empleadoList;
	}

	public void setEmpleadoList(List<EmpleadoListDTO> empleadoList) {
		this.empleadoList = empleadoList;
	}

	public PermisoListDTO getPermisoBuscar() {
		return permisoBuscar;
	}

	public void setPermisoBuscar(PermisoListDTO permisoBuscar) {
		this.permisoBuscar = permisoBuscar;
	}

	public Date getFechaPermiso() {
		return fechaPermiso;
	}

	public void setFechaPermiso(Date fechaPermiso) {
		this.fechaPermiso = fechaPermiso;
	}

	public Object getEmpleadoCodigo() {
		return empleadoCodigo;
	}

	public void setEmpleadoCodigo(Object empleadoCodigo) {
		this.empleadoCodigo = empleadoCodigo;
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public List<PermisoListDTO> getPermisoList() {
		return permisoList;
	}

	public void setPermisoList(List<PermisoListDTO> permisoList) {
		this.permisoList = permisoList;
	}

	
	
}