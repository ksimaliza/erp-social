package ec.edu.uce.erp.web.datamanager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;
import ec.edu.uce.erp.web.common.datamanager.BaseDataManager;

@SessionScoped
@ManagedBean (name = "representanteDataManager")
public class RepresentanteDataManager extends BaseDataManager{
	private static final Logger slf4jLogger = LoggerFactory.getLogger(PerfilDataManager.class);
	private static final long serialVersionUID = 1L;
	
	private RepresentanteDTO representanteInstancia;
	private RepresentanteDTO representanteEditar;
	private RepresentanteListDTO representanteBuscar;
	private List<RepresentanteListDTO> representanteListDTOs;
	
	private Persona personaInstancia;
	private Persona personaEditar;
	private Persona personaBuscar;
	
	
	
	@PostConstruct
	public void inicializarObjetos () {
		
		slf4jLogger.info("inicializarObjetos");
		this.representanteInstancia = new RepresentanteDTO();		
		this.representanteEditar = new RepresentanteDTO();
		this.representanteBuscar = new RepresentanteListDTO();
		this.representanteListDTOs = new ArrayList<RepresentanteListDTO>();
		
		
		this.personaInstancia = new Persona();		
		this.personaEditar = new Persona();
		this.personaBuscar = new Persona();
	}



	public RepresentanteDTO getRepresentanteInstancia() {
		return representanteInstancia;
	}



	public void setRepresentanteInstancia(RepresentanteDTO representanteInstancia) {
		this.representanteInstancia = representanteInstancia;
	}



	public RepresentanteDTO getRepresentanteEditar() {
		return representanteEditar;
	}



	public void setRepresentanteEditar(RepresentanteDTO representanteEditar) {
		this.representanteEditar = representanteEditar;
	}



	public RepresentanteListDTO getRepresentanteBuscar() {
		return representanteBuscar;
	}



	public void setRepresentanteBuscar(RepresentanteListDTO representanteBuscar) {
		this.representanteBuscar = representanteBuscar;
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



	public List<RepresentanteListDTO> getRepresentanteListDTOs() {
		return representanteListDTOs;
	}



	public void setRepresentanteListDTOs(
			List<RepresentanteListDTO> representanteListDTOs) {
		this.representanteListDTOs = representanteListDTOs;
	}

	
}
