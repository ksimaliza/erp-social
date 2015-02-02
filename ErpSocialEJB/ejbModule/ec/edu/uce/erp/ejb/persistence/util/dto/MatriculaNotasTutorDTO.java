package ec.edu.uce.erp.ejb.persistence.util.dto;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaTutorDTO;

public class MatriculaNotasTutorDTO {
	private MatriculaDTO matriculaDTO;
	private EstudianteDTO estudianteDTO;
	private Persona persona;
	private NotaTutorDTO notaTutorDTO;

	public MatriculaDTO getMatriculaDTO() {
		return matriculaDTO;
	}

	public void setMatriculaDTO(MatriculaDTO matriculaDTO) {
		this.matriculaDTO = matriculaDTO;
	}

	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}

	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public NotaTutorDTO getNotaTutorDTO() {
		return notaTutorDTO;
	}

	public void setNotaTutorDTO(NotaTutorDTO notaTutorDTO) {
		this.notaTutorDTO = notaTutorDTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matriculaDTO == null) ? 0 : matriculaDTO.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatriculaNotasTutorDTO other = (MatriculaNotasTutorDTO) obj;
		if (matriculaDTO == null) {
			if (other.matriculaDTO != null)
				return false;
		} else if (!matriculaDTO.equals(other.matriculaDTO))
			return false;
		return true;
	}

}
