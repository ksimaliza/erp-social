package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;

public class ContratoVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	private Persona difunto;
	private ContratoDTO contratoDTO;
	private Persona beneficiario;
	private CatalogoEucaristiaDTO formaPago;
	
	
	public ContratoVO() {
	
		difunto=new Persona();
		contratoDTO=new ContratoDTO();
		beneficiario=new Persona();
		formaPago=new CatalogoEucaristiaDTO();
		
	}

	



	public Persona getDifunto() {
		return difunto;
	}





	public void setDifunto(Persona difunto) {
		this.difunto = difunto;
	}





	public Persona getBeneficiario() {
		return beneficiario;
	}





	public void setBeneficiario(Persona beneficiario) {
		this.beneficiario = beneficiario;
	}

	public ContratoDTO getContratoDTO() {
		return contratoDTO;
	}

	public void setContratoDTO(ContratoDTO contratoDTO) {
		this.contratoDTO = contratoDTO;
	}





	public CatalogoEucaristiaDTO getFormaPago() {
		return formaPago;
	}





	public void setFormaPago(CatalogoEucaristiaDTO formaPago) {
		this.formaPago = formaPago;
	}

	
	
}
