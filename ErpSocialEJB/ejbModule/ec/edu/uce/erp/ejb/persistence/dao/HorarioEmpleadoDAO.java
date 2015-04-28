package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioEmpleadoDTO;

public interface HorarioEmpleadoDAO extends AbstractFacade<HorarioEmpleadoDTO> {

	List<HorarioEmpleadoDTO> getByAnd(HorarioEmpleadoDTO objectDTO)
			throws SeguridadesException;

}
