package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;

public class EstudianteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstudianteDTO estudiante;
	private Persona persona;
	
	public EstudianteVO() {
		estudiante=new EstudianteDTO();
		persona=new Persona();
	}

	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
