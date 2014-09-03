package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;

public class SepulturaVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	private SepulturaDTO sepultura;
	private NichoDTO nichoDTO;

		
	public SepulturaVO() {
	
		sepultura=new SepulturaDTO();
		nichoDTO=new NichoDTO();
			
	}

	public SepulturaDTO getSepultura() {
		return sepultura;
	}

	public NichoDTO getNichoDTO() {
		return nichoDTO;
	}

	public void setSepultura(SepulturaDTO sepultura) {
		this.sepultura = sepultura;
	}

	public void setNichoDTO(NichoDTO nichoDTO) {
		this.nichoDTO = nichoDTO;
	}

	
	
}
