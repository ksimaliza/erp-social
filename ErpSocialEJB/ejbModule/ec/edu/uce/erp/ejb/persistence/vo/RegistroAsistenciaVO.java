package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;

public class RegistroAsistenciaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private EmpleadoDTO empleadoDTO;
	
	private RegistroDTO registroDTO;
	

	public RegistroAsistenciaVO()
	{
		empleadoDTO=new EmpleadoDTO();
		registroDTO=new RegistroDTO();
	}


	public EmpleadoDTO getEmpleadoDTO() {
		return empleadoDTO;
	}


	public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
		this.empleadoDTO = empleadoDTO;
	}


	public RegistroDTO getRegistroDTO() {
		return registroDTO;
	}


	public void setRegistroDTO(RegistroDTO registroDTO) {
		this.registroDTO = registroDTO;
	}
	
	
	
}
