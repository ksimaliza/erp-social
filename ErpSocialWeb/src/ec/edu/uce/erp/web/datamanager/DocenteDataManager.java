package ec.edu.uce.erp.web.datamanager;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;


@SessionScoped
@ManagedBean (name = "docenteDataManager")
public class DocenteDataManager extends BaseDataManager {

	/**
	 * 
	 */
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DocenteDataManager.class);
	
	private static final long serialVersionUID = 1L;
	
	private ProfesorDTO profesorInstancia;
	private ProfesorDTO profesorEditar;
	private DocenteListDTO profesorBuscar;
	private List<DocenteListDTO> listaDocenteListDTOs;
	
	private Persona personaInstancia;
	private Persona personaEditar;
	private Persona personaBuscar;
	private int docenteCod;
	


	public DocenteDataManager() {
		super();
	}
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.profesorInstancia = new ProfesorDTO();		
		this.profesorBuscar = new DocenteListDTO();
		this.profesorEditar = new ProfesorDTO();
		this.personaInstancia=new Persona();
		this.personaBuscar=new Persona();
		this.personaEditar=new Persona();
		this.listaDocenteListDTOs=new ArrayList<DocenteListDTO>();
		
			
	}

	public ProfesorDTO getProfesorInstancia() {
		return profesorInstancia;
	}

	public void setProfesorInstancia(ProfesorDTO profesorInstancia) {
		this.profesorInstancia = profesorInstancia;
	}

	public ProfesorDTO getProfesorEditar() {
		return profesorEditar;
	}

	public void setProfesorEditar(ProfesorDTO profesorEditar) {
		this.profesorEditar = profesorEditar;
	}

	public DocenteListDTO getProfesorBuscar() {
		return profesorBuscar;
	}

	public void setProfesorBuscar(DocenteListDTO profesorBuscar) {
		this.profesorBuscar = profesorBuscar;
	}

	public Persona getPersonaInstancia() {
		return personaInstancia;
	}

	public void setPersonaInstancia(Persona personaInstancia) {
		this.personaInstancia = personaInstancia;
	}

	public Persona getPersonaEditar() {
		return personaEditar;
	}

	public void setPersonaEditar(Persona personaEditar) {
		this.personaEditar = personaEditar;
	}

	public Persona getPersonaBuscar() {
		return personaBuscar;
	}

	public void setPersonaBuscar(Persona personaBuscar) {
		this.personaBuscar = personaBuscar;
	}

	public List<DocenteListDTO> getListaDocenteListDTOs() {
		return listaDocenteListDTOs;
	}

	public void setListaDocenteListDTOs(List<DocenteListDTO> listaDocenteListDTOs) {
		this.listaDocenteListDTOs = listaDocenteListDTOs;
	}

	public int getDocenteCod() {
		return docenteCod;
	}

	public void setDocenteCod(int docenteCod) {
		this.docenteCod = docenteCod;
	}

	
	
}

	

	
