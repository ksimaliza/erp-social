package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;

public class ProfesorVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ProfesorDTO profesor;
	private Persona persona;
	
	public ProfesorVO()
	{
		profesor = new ProfesorDTO();
		persona = new Persona();
	}

	public ProfesorDTO getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorDTO profesor) {
		this.profesor = profesor;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
