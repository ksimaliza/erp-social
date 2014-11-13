/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;

/**
 * @author Administrator
 * 
 */
public class EstudianteNotasParcial {
	private EstudianteDTO estudianteDTO;
	private NotaDTO notaParcialDTO;
	private Persona persona;

	private NotaDTO notaDTOTareas;
	private NotaDTO notaDTOIndividuales;
	private NotaDTO notaDTOGrupales;
	private NotaDTO notaDTOOrales;
	private NotaDTO notaDTOEscrita;

	private MatriculaDetalleDTO matriculaDetalleDTO;

	/**
	 * @return the estudianteDTO
	 */
	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}

	/**
	 * @param estudianteDTO
	 *            the estudianteDTO to set
	 */
	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}

	/**
	 * @return the notaParcialDTO
	 */
	public NotaDTO getNotaParcialDTO() {
		return notaParcialDTO;
	}

	/**
	 * @param notaParcialDTO
	 *            the notaParcialDTO to set
	 */
	public void setNotaParcialDTO(NotaDTO notaParcialDTO) {
		this.notaParcialDTO = notaParcialDTO;
	}

	/**
	 * @return the persona
	 */
	public Persona getPersona() {
		return persona;
	}

	/**
	 * @param persona
	 *            the persona to set
	 */
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/**
	 * @return the notaDTOTareas
	 */
	public NotaDTO getNotaDTOTareas() {
		return notaDTOTareas;
	}

	/**
	 * @param notaDTOTareas
	 *            the notaDTOTareas to set
	 */
	public void setNotaDTOTareas(NotaDTO notaDTOTareas) {
		this.notaDTOTareas = notaDTOTareas;
	}

	/**
	 * @return the notaDTOIndividuales
	 */
	public NotaDTO getNotaDTOIndividuales() {
		return notaDTOIndividuales;
	}

	/**
	 * @param notaDTOIndividuales
	 *            the notaDTOIndividuales to set
	 */
	public void setNotaDTOIndividuales(NotaDTO notaDTOIndividuales) {
		this.notaDTOIndividuales = notaDTOIndividuales;
	}

	/**
	 * @return the notaDTOGrupales
	 */
	public NotaDTO getNotaDTOGrupales() {
		return notaDTOGrupales;
	}

	/**
	 * @param notaDTOGrupales
	 *            the notaDTOGrupales to set
	 */
	public void setNotaDTOGrupales(NotaDTO notaDTOGrupales) {
		this.notaDTOGrupales = notaDTOGrupales;
	}

	/**
	 * @return the notaDTOOrales
	 */
	public NotaDTO getNotaDTOOrales() {
		return notaDTOOrales;
	}

	/**
	 * @param notaDTOOrales
	 *            the notaDTOOrales to set
	 */
	public void setNotaDTOOrales(NotaDTO notaDTOOrales) {
		this.notaDTOOrales = notaDTOOrales;
	}

	/**
	 * @return the notaDTOEscrita
	 */
	public NotaDTO getNotaDTOEscrita() {
		return notaDTOEscrita;
	}

	/**
	 * @param notaDTOEscrita
	 *            the notaDTOEscrita to set
	 */
	public void setNotaDTOEscrita(NotaDTO notaDTOEscrita) {
		this.notaDTOEscrita = notaDTOEscrita;
	}

	/**
	 * @return the matriculaDetalleDTO
	 */
	public MatriculaDetalleDTO getMatriculaDetalleDTO() {
		return matriculaDetalleDTO;
	}

	/**
	 * @param matriculaDetalleDTO
	 *            the matriculaDetalleDTO to set
	 */
	public void setMatriculaDetalleDTO(MatriculaDetalleDTO matriculaDetalleDTO) {
		this.matriculaDetalleDTO = matriculaDetalleDTO;
	}

}
