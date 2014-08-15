package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;

public class AutorizacionExhumacionVO implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Persona persona;
	private AutorizaExhumacionDTO autorizaExhumacionDTO;
	
	public AutorizacionExhumacionVO() {
	
		persona=new Persona();
		autorizaExhumacionDTO=new AutorizaExhumacionDTO();
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public AutorizaExhumacionDTO getAutorizaExhumacionDTO() {
		return autorizaExhumacionDTO;
	}

	public void setAutorizaExhumacionDTO(AutorizaExhumacionDTO autorizaExhumacionDTO) {
		this.autorizaExhumacionDTO = autorizaExhumacionDTO;
	} 
	

	
}
