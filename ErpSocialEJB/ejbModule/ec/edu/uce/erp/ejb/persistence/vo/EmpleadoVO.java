package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Empleado;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;

public class EmpleadoVO implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Empleado empleado;
	private Persona persona;
	private EmpleadoDTO empleadoDTO;
	private HorarioEmpleadoDTO horarioEmpleadoDTO;
	
	public EmpleadoVO()  {
		empleadoDTO=new EmpleadoDTO();
		persona=new Persona();
		empleado=new Empleado();
		horarioEmpleadoDTO=new HorarioEmpleadoDTO();
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public EmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}

	public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}

	public HorarioEmpleadoDTO getHorarioEmpleadoDTO() {
		return horarioEmpleadoDTO;
	}

	public void setHorarioEmpleadoDTO(HorarioEmpleadoDTO horarioEmpleadoDTO) {
		this.horarioEmpleadoDTO = horarioEmpleadoDTO;
	}
	
	
	
}
