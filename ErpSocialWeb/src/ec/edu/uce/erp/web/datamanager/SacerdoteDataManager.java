package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "sacerdoteDataManager")
public class SacerdoteDataManager extends BaseDataManager {
	private static final Logger slf4jLogger = LoggerFactory.getLogger(SacerdoteDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private SacerdoteDTO sacerdoteInsertar;
		
	private Persona sacerdotePersonaInsertar;
	private SacerdoteListDTO sacerdoteBuscar;
	private List<SacerdoteListDTO> sacerdoteListDTOs;
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.sacerdoteInsertar=new SacerdoteDTO();
		this.sacerdotePersonaInsertar=new Persona();
		this.sacerdoteBuscar=new SacerdoteListDTO();
		this.sacerdoteListDTOs=new ArrayList<SacerdoteListDTO>();	
	}


	public List<SacerdoteListDTO> getSacerdoteListDTOs() {
		return sacerdoteListDTOs;
	}


	public void setSacerdoteListDTOs(List<SacerdoteListDTO> sacerdoteListDTOs) {
		this.sacerdoteListDTOs = sacerdoteListDTOs;
	}


	public SacerdoteListDTO getSacerdoteBuscar() {
		return sacerdoteBuscar;
	}


	public void setSacerdoteBuscar(SacerdoteListDTO sacerdoteBuscar) {
		this.sacerdoteBuscar = sacerdoteBuscar;
	}


	public SacerdoteDTO getSacerdoteInsertar() {
		return sacerdoteInsertar;
	}


	public void setSacerdoteInsertar(SacerdoteDTO sacerdoteInsertar) {
		this.sacerdoteInsertar = sacerdoteInsertar;
	}


	public Persona getSacerdotePersonaInsertar() {
		return sacerdotePersonaInsertar;
	}


	public void setSacerdotePersonaInsertar(Persona sacerdotePersonaInsertar) {
		this.sacerdotePersonaInsertar = sacerdotePersonaInsertar;
	}
	
   
}
