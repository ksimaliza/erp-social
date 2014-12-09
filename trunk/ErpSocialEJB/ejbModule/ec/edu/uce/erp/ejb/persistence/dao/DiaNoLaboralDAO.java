package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;

public interface DiaNoLaboralDAO extends AbstractFacade<DiaNoLaboralDTO> {

	List<DiaNoLaboralDTO> getAll(int year, int entidad)
			throws SeguridadesException;

	
	
}
