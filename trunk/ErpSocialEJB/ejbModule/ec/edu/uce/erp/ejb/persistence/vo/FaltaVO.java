package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;

public class FaltaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmpleadoDTO empleado;
	private FaltaDTO falta;
	
	public FaltaVO() {
		empleado=new EmpleadoDTO();
		falta=new FaltaDTO();
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public FaltaDTO getFalta() {
		return falta;
	}

	public void setFalta(FaltaDTO falta) {
		this.falta = falta;
	}
	
}
