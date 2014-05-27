package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaDTO;

public interface DiaDAO extends AbstractFacade<DiaDTO> {

	List<DiaDTO> getByAnd(DiaDTO objectDTO) throws SeguridadesException;

}
