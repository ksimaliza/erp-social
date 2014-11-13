/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.io.Serializable;
import java.util.List;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDetalleDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.PeriodoDTO;

/**
 * @author Administrator
 * 
 */
public class DatosEstudianteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private EstudianteListDTO estudianteListDTO;
	private EstudianteDTO estudianteDTO;
	private List<PeriodoDTO> periodoDTOsMatriculados;
	private List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs;

	private NivelDTO nivelDTO;
	private ParaleloDTO paraleloDTO;
	private PeriodoDTO periodoDTO;

	public EstudianteListDTO getEstudianteListDTO() {
		return estudianteListDTO;
	}

	public void setEstudianteListDTO(EstudianteListDTO estudianteListDTO) {
		this.estudianteListDTO = estudianteListDTO;
	}

	public EstudianteDTO getEstudianteDTO() {
		return estudianteDTO;
	}

	public void setEstudianteDTO(EstudianteDTO estudianteDTO) {
		this.estudianteDTO = estudianteDTO;
	}

	public List<PeriodoDTO> getPeriodoDTOsMatriculados() {
		return periodoDTOsMatriculados;
	}

	public void setPeriodoDTOsMatriculados(List<PeriodoDTO> periodoDTOsMatriculados) {
		this.periodoDTOsMatriculados = periodoDTOsMatriculados;
	}

	public List<MatriculaDetalleDTO> getListaMatriculaDetalleDTOs() {
		return listaMatriculaDetalleDTOs;
	}

	public void setListaMatriculaDetalleDTOs(List<MatriculaDetalleDTO> listaMatriculaDetalleDTOs) {
		this.listaMatriculaDetalleDTOs = listaMatriculaDetalleDTOs;
	}

	public NivelDTO getNivelDTO() {
		return nivelDTO;
	}

	public void setNivelDTO(NivelDTO nivelDTO) {
		this.nivelDTO = nivelDTO;
	}

	public ParaleloDTO getParaleloDTO() {
		return paraleloDTO;
	}

	public void setParaleloDTO(ParaleloDTO paraleloDTO) {
		this.paraleloDTO = paraleloDTO;
	}

	public PeriodoDTO getPeriodoDTO() {
		return periodoDTO;
	}

	public void setPeriodoDTO(PeriodoDTO periodoDTO) {
		this.periodoDTO = periodoDTO;
	}

}
