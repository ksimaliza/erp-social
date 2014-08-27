package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;

public class PagoVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private ContratoDTO contrato;
	private PagoDTO pago;
	

	public PagoVO() {
	
		setContrato(new ContratoDTO());
		setPago(new PagoDTO());	
	}


	public ContratoDTO getContrato() {
		return contrato;
	}


	public void setContrato(ContratoDTO contrato) {
		this.contrato = contrato;
	}


	public PagoDTO getPago() {
		return pago;
	}


	public void setPago(PagoDTO pago) {
		this.pago = pago;
	}


	
}
