package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;

public class ContratoVO implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	private Persona difunto;
	private CatalogoEucaristiaDTO formaPago;
	private ContratoDTO contratoDTO;
	private NichoDTO nichoDTO;
	private Persona beneficiario;
	
	
	public ContratoVO() {
	
		difunto=new Persona();
		formaPago=new CatalogoEucaristiaDTO();
		nichoDTO=new NichoDTO();
		contratoDTO=new ContratoDTO();
		beneficiario=new Persona();
		
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





	public CatalogoEucaristiaDTO getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(CatalogoEucaristiaDTO formaPago) {
		this.formaPago = formaPago;
	}

	public ContratoDTO getContratoDTO() {
		return contratoDTO;
	}

	public void setContratoDTO(ContratoDTO contratoDTO) {
		this.contratoDTO = contratoDTO;
	}

	public NichoDTO getNichoDTO() {
		return nichoDTO;
	}

	public void setNichoDTO(NichoDTO nichoDTO) {
		this.nichoDTO = nichoDTO;
	}

	
	
}
