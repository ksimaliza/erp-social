/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.util.dto;

import java.util.Comparator;

import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NotaDTO;

/**
 * @author Administrator
 * 
 */
public class ComparadorTipoNotas implements Comparator<NotaDTO> {

	@Override
	public int compare(NotaDTO o1, NotaDTO o2) {
		if (o1.getMatMatriculaDetalleBean() == null && o2.getMatMatriculaDetalleBean() != null) {
			return -1;
		} else if (o1.getMatMatriculaDetalleBean() != null && o2.getMatMatriculaDetalleBean() == null) {
			return 1;
		} else if (o1.getMatMatriculaDetalleBean() == null && o2.getMatMatriculaDetalleBean() == null) {
			return 0;
		}

		return o1.getTipoNotaBean().getParCodigo().compareTo(o2.getTipoNotaBean().getParCodigo());

	}
}
