package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name ="eucaristiaDataManager")
public class EucaristiaDataManager extends BaseDataManager {
private static final Logger slf4jLogger = LoggerFactory.getLogger(EucaristiaDataManager.class);
	
	private static final long serialVersionUID = 1L;

	private EucaristiaDTO eucaristiaInsertar;
	private List<EucaristiaListDTO> eucaristiaListDTOs;
	private EucaristiaListDTO eucaristiaListDTO;
	private int codigoSacerdote;
	private List<SacerdoteListDTO> sacerdoteDTOs;
	
	
	@PostConstruct
	public void inicializarObjetos () {
		slf4jLogger.info("inicializarObjetos");
		this.eucaristiaListDTOs=new ArrayList<EucaristiaListDTO>();
		this.sacerdoteDTOs=new ArrayList<SacerdoteListDTO>();
		this.eucaristiaListDTO=new EucaristiaListDTO();
		this.eucaristiaInsertar=new EucaristiaDTO();
	
	}


	public EucaristiaDTO getEucaristiaInsertar() {
		return eucaristiaInsertar;
	}


	public void setEucaristiaInsertar(EucaristiaDTO eucaristiaInsertar) {
		this.eucaristiaInsertar = eucaristiaInsertar;
	}


	public List<EucaristiaListDTO> getEucaristiaListDTOs() {
		return eucaristiaListDTOs;
	}


	public void setEucaristiaListDTOs(List<EucaristiaListDTO> eucaristiaListDTOs) {
		this.eucaristiaListDTOs = eucaristiaListDTOs;
	}


	public EucaristiaListDTO getEucaristiaListDTO() {
		return eucaristiaListDTO;
	}


	public void setEucaristiaListDTO(EucaristiaListDTO eucaristiaListDTO) {
		this.eucaristiaListDTO = eucaristiaListDTO;
	}


	public int getCodigoSacerdote() {
		return codigoSacerdote;
	}


	public void setCodigoSacerdote(int codigoSacerdote) {
		this.codigoSacerdote = codigoSacerdote;
	}


	public List<SacerdoteListDTO> getSacerdoteDTOs() {
		return sacerdoteDTOs;
	}


	public void setSacerdoteDTOs(List<SacerdoteListDTO> sacerdoteDTOs) {
		this.sacerdoteDTOs = sacerdoteDTOs;
	}
	
	
	

}
