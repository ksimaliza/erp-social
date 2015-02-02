package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;

public class EstudianteNotaSuspensa implements Serializable {

	private static final long serialVersionUID = 1L;

	private EstudianteDTO estudianteDTO;
	private NotaDTO notaSuspensoDTO;
	private Persona persona;

	private MatriculaDetalleDTO matriculaDetalleDTO;

	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}

	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}

	public NotaDTO getNotaSuspensoDTO() {
		return notaSuspensoDTO;
	}

	public void setNotaSuspensoDTO(NotaDTO notaSuspensoDTO) {
		this.notaSuspensoDTO = notaSuspensoDTO;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public MatriculaDetalleDTO getMatriculaDetalleDTO() {
		return matriculaDetalleDTO;
	}

	public void setMatriculaDetalleDTO(MatriculaDetalleDTO matriculaDetalleDTO) {
		this.matriculaDetalleDTO = matriculaDetalleDTO;
	}

}
