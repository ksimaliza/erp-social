package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class SacerdoteVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Persona persona;
	private SacerdoteDTO sacerdoteDTO;
	
	public SacerdoteVO ()
	{
		persona=new Persona();
		sacerdoteDTO=new SacerdoteDTO();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public SacerdoteDTO getSacerdoteDTO() {
		return sacerdoteDTO;
	}

	public void setSacerdoteDTO(SacerdoteDTO sacerdoteDTO) {
		this.sacerdoteDTO = sacerdoteDTO;
	}
	

}
