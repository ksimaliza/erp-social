package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "nivelDataManager")
public class NivelDataManager extends BaseDataManager{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private NivelDTO nivelInstancia;
	private NivelDTO nivelEditar;
	private NivelDTO nivelBuscar;
	private java.util.List<NivelDTO> nivelDTOs;
	
	private int nivelCode;
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.nivelInstancia = new NivelDTO();		
		this.nivelEditar = new NivelDTO();
		this.nivelBuscar = new NivelDTO();
		this.nivelDTOs = new ArrayList<NivelDTO>();
		
			
	}

	
	
	
	
	public NivelDTO getNivelInstancia() {
		return nivelInstancia;
	}

	public void setNivelInstancia(NivelDTO nivelInstancia) {
		this.nivelInstancia = nivelInstancia;
	}

	public NivelDTO getNivelEditar() {
		return nivelEditar;
	}

	public void setNivelEditar(NivelDTO nivelEditar) {
		this.nivelEditar = nivelEditar;
	}

	public NivelDTO getNivelBuscar() {
		return nivelBuscar;
	}

	public void setNivelBuscar(NivelDTO nivelBuscar) {
		this.nivelBuscar = nivelBuscar;
	}

	public java.util.List<NivelDTO> getNivelDTOs() {
		return nivelDTOs;
	}

	public void setNivelDTOs(java.util.List<NivelDTO> nivelDTOs) {
		this.nivelDTOs = nivelDTOs;
	}





	public int getNivelCode() {
		return nivelCode;
	}





	public void setNivelCode(int nivelCode) {
		this.nivelCode = nivelCode;
	}
	
	

}
