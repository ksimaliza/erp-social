package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoAtrasoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;

public interface RegistroDAO extends AbstractFacade<RegistroDTO>{

	RegistroDTO getUltimate(EmpleadoDTO empleado) throws SeguridadesException;

	
	List<EmpleadoAtrasoListDTO> getByAnd(EmpleadoAtrasoListDTO objetoDTO)
			throws SeguridadesException;

}
