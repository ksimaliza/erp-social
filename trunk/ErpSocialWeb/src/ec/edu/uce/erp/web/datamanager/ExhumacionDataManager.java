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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "exhumacionDataManager")

public class ExhumacionDataManager extends BaseDataManager {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(ExhumacionDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private  Persona difuntoInsertar;
	private ExumacionDTO exumacionDTO;
	private List<ExumacionListDTO> exumacionListDTOs;
	private Date fechaExhumacion;
	private Date fechaSepelio;
	private int autorizaCodigo;
	private ExumacionListDTO exumacionListDTO;
	private List<AutorizaExhumacionListDTO> autorizaExhumacionListDTOs;
	private DefuncionListDTO defuncionListDTO;
	private Boolean desactivado;
	
	
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.difuntoInsertar = new Persona();		
		this.exumacionDTO= new ExumacionDTO();
		this.exumacionListDTO= new ExumacionListDTO();
		this.autorizaExhumacionListDTOs = new ArrayList<AutorizaExhumacionListDTO>();
		fechaExhumacion=new Date();
		fechaSepelio=new Date();
		this.exumacionListDTOs= new ArrayList<ExumacionListDTO>();	
		this.defuncionListDTO=new DefuncionListDTO();
		this.desactivado=false;
			
	}


	public Persona getDifuntoInsertar() {
		return difuntoInsertar;
	}



	public void setDifuntoInsertar(Persona difuntoInsertar) {
		this.difuntoInsertar = difuntoInsertar;
	}



	public ExumacionDTO getExumacionDTO() {
		return exumacionDTO;
	}





	public void setExumacionDTO(ExumacionDTO exumacionDTO) {
		this.exumacionDTO = exumacionDTO;
	}





	public List<ExumacionListDTO> getExumacionListDTOs() {
		return exumacionListDTOs;
	}





	public void setExumacionListDTOs(List<ExumacionListDTO> exumacionListDTOs) {
		this.exumacionListDTOs = exumacionListDTOs;
	}





	public Date getFechaExhumacion() {
		return fechaExhumacion;
	}





	public void setFechaExhumacion(Date fechaExhumacion) {
		this.fechaExhumacion = fechaExhumacion;
	}





	public Date getFechaSepelio() {
		return fechaSepelio;
	}





	public void setFechaSepelio(Date fechaSepelio) {
		this.fechaSepelio = fechaSepelio;
	}





	public int getAutorizaCodigo() {
		return autorizaCodigo;
	}





	public void setAutorizaCodigo(int autorizaCodigo) {
		this.autorizaCodigo = autorizaCodigo;
	}





	public ExumacionListDTO getExumacionListDTO() {
		return exumacionListDTO;
	}





	public void setExumacionListDTO(ExumacionListDTO exumacionListDTO) {
		this.exumacionListDTO = exumacionListDTO;
	}





	public List<AutorizaExhumacionListDTO> getAutorizaExhumacionListDTOs() {
		return autorizaExhumacionListDTOs;
	}





	public void setAutorizaExhumacionListDTOs(
			List<AutorizaExhumacionListDTO> autorizaExhumacionListDTOs) {
		this.autorizaExhumacionListDTOs = autorizaExhumacionListDTOs;
	}





	public DefuncionListDTO getDefuncionListDTO() {
		return defuncionListDTO;
	}





	public void setDefuncionListDTO(DefuncionListDTO defuncionListDTO) {
		this.defuncionListDTO = defuncionListDTO;
	}





	public Boolean getDesactivado() {
		return desactivado;
	}





	public void setDesactivado(Boolean desactivado) {
		this.desactivado = desactivado;
	}
	

	
}
