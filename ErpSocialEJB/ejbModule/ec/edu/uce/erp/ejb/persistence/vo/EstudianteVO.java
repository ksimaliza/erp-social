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
	
	public EstudianteVO() {
		estudiante=new EstudianteDTO();
		persona=new Persona();
		empresa=new Empresa();
		representante=new RepresentanteDTO();
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
	
}
