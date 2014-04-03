package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ParaleloDTO;

public interface ParaleloDAO extends AbstractFacade<ParaleloDTO> {

	List<ParaleloDTO> obtenerParalelo(ParaleloDTO paralelo)
			throws SeguridadesException;

}
