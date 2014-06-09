package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroDTO;

public interface ParametroAsistenciaDAO extends AbstractFacade<ParametroDTO> {

	List<ParametroDTO> findAll(ParametroDTO parametro)
			throws SeguridadesException;

	List<ParametroDTO> getByAnd(ParametroDTO objetoDTO)
			throws SeguridadesException;

}
