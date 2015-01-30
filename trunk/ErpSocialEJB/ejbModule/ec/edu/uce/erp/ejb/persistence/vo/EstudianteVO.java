package ec.edu.uce.erp.ejb.persistence.vo;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Empresa;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;

public class EstudianteVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EstudianteDTO estudiante;
	private Persona persona;
	private Empresa empresa;
	private RepresentanteDTO representante;
	private Persona personaRepresentante;
	private Persona madre;
	private Persona padre;
	private Persona representanteEst;
	
	public EstudianteVO() {
		estudiante=new EstudianteDTO();
		persona=new Persona();
		empresa=new Empresa();
		representante=new RepresentanteDTO();
		madre=new Persona();
		padre=new Persona();
		representanteEst=new Persona();
	}

	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public RepresentanteDTO getRepresentante() {
		return representante;
	}

	public void setRepresentante(RepresentanteDTO representante) {
		this.representante = representante;
	}

	public Persona getPersonaRepresentante() {
		return personaRepresentante;
	}

	public void setPersonaRepresentante(Persona personaRepresentante) {
		this.personaRepresentante = personaRepresentante;
	}

	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Persona getRepresentanteEst() {
		return representanteEst;
	}

	public void setRepresentanteEst(Persona representanteEst) {
		this.representanteEst = representanteEst;
	}
	
	
	
}
