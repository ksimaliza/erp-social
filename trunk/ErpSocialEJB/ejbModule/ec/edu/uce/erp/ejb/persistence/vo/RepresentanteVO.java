package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;

public class RepresentanteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Persona persona;
	private RepresentanteDTO representante;
	
	public RepresentanteVO()
	{
		persona = new Persona();
		representante = new RepresentanteDTO();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public RepresentanteDTO getRepresentante() {
		return representante;
	}

	public void setRepresentante(RepresentanteDTO representante) {
		this.representante = representante;
	}
	
	
}
