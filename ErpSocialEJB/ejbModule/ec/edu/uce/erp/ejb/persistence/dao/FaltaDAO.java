package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.FaltaListDTO;

public interface FaltaDAO extends AbstractFacade<FaltaDTO>{

	List<FaltaListDTO> findAll(FaltaListDTO falta)
			throws SeguridadesException;

}
