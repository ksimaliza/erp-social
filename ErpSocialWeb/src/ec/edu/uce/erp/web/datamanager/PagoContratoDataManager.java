package ec.edu.uce.erp.web.datamanager;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
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
	private ContratoDTO contratoDTO;
	private List<PagoContratoListDTO> pagoContratoListDTOs;
	private PagoContratoListDTO pagoContratoListDTO;
	private Date fechaPago;
	private List<Persona> difuntoList;
	private List<ContratoListDTO> contratoListDTO;
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.pagoDTO=new PagoDTO();
		this.contratoDTO=new ContratoDTO();
		this.pagoContratoListDTOs=new ArrayList<PagoContratoListDTO>();
		this.pagoContratoListDTO=new PagoContratoListDTO();
		this.fechaPago=new Date();
		this.difuntoList=new ArrayList<Persona>();
		this.contratoListDTO=new ArrayList<ContratoListDTO>();
	}


	public PagoDTO getPagoDTO() {
		return pagoDTO;
	}



	public void setPagoDTO(PagoDTO pagoDTO) {
		this.pagoDTO = pagoDTO;
	}



	public ContratoDTO getContratoDTO() {
		return contratoDTO;
	}



	public void setContratoDTO(ContratoDTO contratoDTO) {
		this.contratoDTO = contratoDTO;
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


	public List<ContratoListDTO> getContratoListDTO() {
		return contratoListDTO;
	}


	public void setContratoListDTO(List<ContratoListDTO> contratoListDTO) {
		this.contratoListDTO = contratoListDTO;
	}


	

}
