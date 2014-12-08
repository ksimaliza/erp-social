package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.ParametroCatalogoDTO;

public interface ParametroCatalogoDAO extends AbstractFacade<ParametroCatalogoDTO> {

	List<ParametroCatalogoDTO> getByAnd(ParametroCatalogoDTO objetoDTO)
			throws SeguridadesException;

}
