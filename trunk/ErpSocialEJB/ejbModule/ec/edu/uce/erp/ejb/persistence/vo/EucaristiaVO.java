package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;

public class EucaristiaVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EucaristiaDTO eucaristiaDTO;
	private SacerdoteDTO sacerdoteDTO;
	
	public EucaristiaVO ()
	{
		eucaristiaDTO=new EucaristiaDTO();
		sacerdoteDTO=new SacerdoteDTO();
	}

	public EucaristiaDTO getEucaristiaDTO() {
		return eucaristiaDTO;
	}

	public void setEucaristiaDTO(EucaristiaDTO eucaristiaDTO) {
		this.eucaristiaDTO = eucaristiaDTO;
	}

	public SacerdoteDTO getSacerdoteDTO() {
		return sacerdoteDTO;
	}

	public void setSacerdoteDTO(SacerdoteDTO sacerdoteDTO) {
		this.sacerdoteDTO = sacerdoteDTO;
	}
	
	
}
