package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "sepulturaDataManager")
public class SepulturaDataManager extends BaseDataManager {
private static final Logger slf4jLogger = LoggerFactory.getLogger(SepulturaDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private SepulturaDTO sepulturaDTO;
	private int codigoNicho;
	private List<NichoListDTO> nichoListDTOs2;
	private SepulturaListDTO sepulturaListDTO;
	private List<SepulturaListDTO> sepulturaListDTOs;
	private Persona difuntoInsertar;
	private List<DefuncionListDTO> defuncionListDTOs;
	private DefuncionListDTO defuncionlistDTO; 
	private NichoListDTO nichoListDTO;
	
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.sepulturaDTO = new SepulturaDTO();		
		this.nichoListDTOs2= new ArrayList<NichoListDTO>();
		this.sepulturaListDTO= new SepulturaListDTO();
		this.sepulturaListDTOs = new ArrayList<SepulturaListDTO>();
		this.difuntoInsertar=new Persona();		
		this.defuncionListDTOs=new ArrayList<DefuncionListDTO>();
		this.defuncionlistDTO=new DefuncionListDTO();
		this.nichoListDTO=new NichoListDTO();
	}


	public SepulturaDTO getSepulturaDTO() {
		return sepulturaDTO;
	}


	public int getCodigoNicho() {
		return codigoNicho;
	}


	public List<NichoListDTO> getNichoListDTOs2() {
		return nichoListDTOs2;
	}


	public SepulturaListDTO getSepulturaListDTO() {
		return sepulturaListDTO;
	}


	public List<SepulturaListDTO> getSepulturaListDTOs() {
		return sepulturaListDTOs;
	}


	public void setSepulturaDTO(SepulturaDTO sepulturaDTO) {
		this.sepulturaDTO = sepulturaDTO;
	}


	public void setCodigoNicho(int codigoNicho) {
		this.codigoNicho = codigoNicho;
	}


	public void setNichoListDTOs2(List<NichoListDTO> nichoListDTOs2) {
		this.nichoListDTOs2 = nichoListDTOs2;
	}


	public void setSepulturaListDTO(SepulturaListDTO sepulturaListDTO) {
		this.sepulturaListDTO = sepulturaListDTO;
	}


	public void setSepulturaListDTOs(List<SepulturaListDTO> sepulturaListDTOs) {
		this.sepulturaListDTOs = sepulturaListDTOs;
	}


	public Persona getDifuntoInsertar() {
		return difuntoInsertar;
	}


	public void setDifuntoInsertar(Persona difuntoInsertar) {
		this.difuntoInsertar = difuntoInsertar;
	}


	public List<DefuncionListDTO> getDefuncionListDTOs() {
		return defuncionListDTOs;
	}


	public void setDefuncionListDTOs(List<DefuncionListDTO> defuncionListDTOs) {
		this.defuncionListDTOs = defuncionListDTOs;
	}


	public DefuncionListDTO getDefuncionlistDTO() {
		return defuncionlistDTO;
	}


	public void setDefuncionlistDTO(DefuncionListDTO defuncionlistDTO) {
		this.defuncionlistDTO = defuncionlistDTO;
	}


	public NichoListDTO getNichoListDTO() {
		return nichoListDTO;
	}


	public void setNichoListDTO(NichoListDTO nichoListDTO) {
		this.nichoListDTO = nichoListDTO;
	}


}
