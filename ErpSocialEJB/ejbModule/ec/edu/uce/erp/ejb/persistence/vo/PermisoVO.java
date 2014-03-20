package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;


public class PermisoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmpleadoDTO empleado;
	private PermisoDTO permiso;
	
	public PermisoVO ()
	{
		empleado = new EmpleadoDTO();
		permiso = new PermisoDTO();
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public PermisoDTO getPermiso() {
		return permiso;
	}

	public void setPermiso(PermisoDTO permiso) {
		this.permiso = permiso;
	}

	
	
}
