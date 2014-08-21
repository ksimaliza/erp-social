package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;

public class ExhumacionVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	private Persona difunto;
	private AutorizaExhumacionDTO autorizaExhumacionDTO;
	private ExumacionDTO exumacionDTO;
	
	public ExhumacionVO() {
	
		difunto=new Persona();
		autorizaExhumacionDTO=new AutorizaExhumacionDTO();
		exumacionDTO=new ExumacionDTO();
		
	}

	public Persona getDifunto() {
		return difunto;
	}

	public void setDifunto(Persona difunto) {
		this.difunto = difunto;
	}

	public AutorizaExhumacionDTO getAutorizaExhumacionDTO() {
		return autorizaExhumacionDTO;
	}

	public void setAutorizaExhumacionDTO(AutorizaExhumacionDTO autorizaExhumacionDTO) {
		this.autorizaExhumacionDTO = autorizaExhumacionDTO;
	}

	public ExumacionDTO getExumacionDTO() {
		return exumacionDTO;
	}

	public void setExumacionDTO(ExumacionDTO exumacionDTO) {
		this.exumacionDTO = exumacionDTO;
	}

}
