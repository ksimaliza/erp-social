package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoListDTO;

public interface EmpleadoDAO extends AbstractFacade<EmpleadoDTO> {

	List<EmpleadoListDTO> findAll(EmpleadoListDTO empleado)
			throws SeguridadesException;

}
