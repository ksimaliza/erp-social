package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloListDTO;

public interface NivelParaleloDAO extends AbstractFacade<NivelParaleloDTO>{

	List<NivelParaleloListDTO> findAll(NivelParaleloListDTO nivelParalelo)
			throws SeguridadesException;

}
