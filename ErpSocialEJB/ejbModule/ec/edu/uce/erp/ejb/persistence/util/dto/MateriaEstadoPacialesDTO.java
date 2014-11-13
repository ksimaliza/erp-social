/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.TipoNotaDTO;

/**
 * @author Administrator
 * 
 */
public class MateriaEstadoPacialesDTO {

	private AsinacionDTO asinacionDTO;
	private TipoNotaDTO tipoNotaDTO;

	public AsinacionDTO getAsinacionDTO() {
		return asinacionDTO;
	}

	public void setAsinacionDTO(AsinacionDTO asinacionDTO) {
		this.asinacionDTO = asinacionDTO;
	}

	public TipoNotaDTO getTipoNotaDTO() {
		return tipoNotaDTO;
	}

	public void setTipoNotaDTO(TipoNotaDTO tipoNotaDTO) {
		this.tipoNotaDTO = tipoNotaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asinacionDTO == null) ? 0 : asinacionDTO.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MateriaEstadoPacialesDTO other = (MateriaEstadoPacialesDTO) obj;
		if (asinacionDTO == null) {
			if (other.asinacionDTO != null)
				return false;
		} else if (!asinacionDTO.equals(other.asinacionDTO))
			return false;
		return true;
	}

}
