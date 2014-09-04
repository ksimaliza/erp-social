package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;

public class PagoVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	private PagoDTO pago;
	private ContratoDTO contratoDTO;
	private ContratoListDTO contratoListDTO;
	
	public PagoVO ()
	{
		pago=new PagoDTO();
		contratoDTO=new ContratoDTO();
		contratoListDTO=new ContratoListDTO();
	}

	public PagoDTO getPago() {
		return pago;
	}

	public ContratoDTO getContratoDTO() {
		return contratoDTO;
	}

	public void setPago(PagoDTO pago) {
		this.pago = pago;
	}

	public ContratoListDTO getContratoListDTO() {
		return contratoListDTO;
	}

	public void setContratoListDTO(ContratoListDTO contratoListDTO) {
		this.contratoListDTO = contratoListDTO;
	}

	public void setContratoDTO(ContratoDTO contratoDTO) {
		this.contratoDTO = contratoDTO;
	}
	
	
}


