package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;

public interface NivelParaleloDAO extends AbstractFacade<NivelParaleloDTO>{

	List<NivelParaleloDTO> getAll(NivelParaleloDTO nivelParalelo)
			throws SeguridadesException;

}
