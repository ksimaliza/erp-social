package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoContratoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PagoDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "pagoContratoDataManager")

public class PagoContratoDataManager  extends BaseDataManager{

	
private static final Logger slf4jLogger = LoggerFactory.getLogger(PagoContratoDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private PagoDTO pagoDTO;
	private ContratoListDTO contratoListDTO;
	
	private List<PagoContratoListDTO> pagoContratoListDTOs;
	private PagoContratoListDTO pagoContratoListDTO;
	private Date fechaPago;
	private List<Persona> difuntoList;
	private List<ContratoListDTO> contratoListDTOs;
	private PagoContratoListDTO pagoContratoListDTOEditar;
	private Boolean exportDesactivado;
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.pagoDTO=new PagoDTO();
		this.contratoListDTO=new ContratoListDTO();
		this.pagoContratoListDTOs=new ArrayList<PagoContratoListDTO>();
		this.pagoContratoListDTO=new PagoContratoListDTO();
		this.fechaPago=new Date();
		this.difuntoList=new ArrayList<Persona>();
		this.contratoListDTOs=new ArrayList<ContratoListDTO>();
		this.pagoContratoListDTOEditar=new PagoContratoListDTO();
		this.exportDesactivado=true;
	}


	public PagoDTO getPagoDTO() {
		return pagoDTO;
	}



	public void setPagoDTO(PagoDTO pagoDTO) {
		this.pagoDTO = pagoDTO;
	}



	


	public ContratoListDTO getContratoListDTO() {
		return contratoListDTO;
	}


	public List<PagoContratoListDTO> getPagoContratoListDTOs() {
		return pagoContratoListDTOs;
	}



	public void setPagoContratoListDTOs(List<PagoContratoListDTO> pagoContratoListDTOs) {
		this.pagoContratoListDTOs = pagoContratoListDTOs;
	}



	public PagoContratoListDTO getPagoContratoListDTO() {
		return pagoContratoListDTO;
	}



	public void setPagoContratoListDTO(PagoContratoListDTO pagoContratoListDTO) {
		this.pagoContratoListDTO = pagoContratoListDTO;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public List<Persona> getDifuntoList() {
		return difuntoList;
	}


	public void setDifuntoList(List<Persona> difuntoList) {
		this.difuntoList = difuntoList;
	}





	public List<ContratoListDTO> getContratoListDTOs() {
		return contratoListDTOs;
	}


	public void setContratoListDTO(ContratoListDTO contratoListDTO) {
		this.contratoListDTO = contratoListDTO;
	}


	public void setContratoListDTOs(List<ContratoListDTO> contratoListDTOs) {
		this.contratoListDTOs = contratoListDTOs;
	}


	public PagoContratoListDTO getPagoContratoListDTOEditar() {
		return pagoContratoListDTOEditar;
	}


	public void setPagoContratoListDTOEditar(
			PagoContratoListDTO pagoContratoListDTOEditar) {
		this.pagoContratoListDTOEditar = pagoContratoListDTOEditar;
	}


	public Boolean getExportDesactivado() {
		return exportDesactivado;
	}


	public void setExportDesactivado(Boolean exportDesactivado) {
		this.exportDesactivado = exportDesactivado;
	}


	

}
