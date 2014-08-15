package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "autorizaExhumacionDataManager")
public class AutorizaExhumacionDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(AutorizaExhumacionDataManager.class);
	private static final long serialVersionUID = 1L;
	
			
	private Persona autorizaExhuPerInsertar;
	private AutorizaExhumacionDTO autorizaExhumacionDTO;
	private AutorizaExhumacionListDTO autorizaExhuBuscar;
	private List<AutorizaExhumacionListDTO> autorizaExhumacionListDTOs;
	
			
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.autorizaExhuPerInsertar=new Persona();
		this.autorizaExhumacionDTO=new AutorizaExhumacionDTO();
		this.autorizaExhuBuscar=new AutorizaExhumacionListDTO();
		this.autorizaExhumacionListDTOs=new ArrayList<AutorizaExhumacionListDTO>();	
	}


	
	public Persona getAutorizaExhuPerInsertar() {
		return autorizaExhuPerInsertar;
	}


	public void setAutorizaExhuPerInsertar(Persona autorizaExhuPerInsertar) {
		this.autorizaExhuPerInsertar = autorizaExhuPerInsertar;
	}


	public AutorizaExhumacionDTO getAutorizaExhumacionDTO() {
		return autorizaExhumacionDTO;
	}


	public void setAutorizaExhumacionDTO(AutorizaExhumacionDTO autorizaExhumacionDTO) {
		this.autorizaExhumacionDTO = autorizaExhumacionDTO;
	}


	public AutorizaExhumacionListDTO getAutorizaExhuBuscar() {
		return autorizaExhuBuscar;
	}


	public void setAutorizaExhuBuscar(AutorizaExhumacionListDTO autorizaExhuBuscar) {
		this.autorizaExhuBuscar = autorizaExhuBuscar;
	}


	public List<AutorizaExhumacionListDTO> getAutorizaExhumacionListDTOs() {
		return autorizaExhumacionListDTOs;
	}


	public void setAutorizaExhumacionListDTOs(
			List<AutorizaExhumacionListDTO> autorizaExhumacionListDTOs) {
		this.autorizaExhumacionListDTOs = autorizaExhumacionListDTOs;
	}

	
	

}
