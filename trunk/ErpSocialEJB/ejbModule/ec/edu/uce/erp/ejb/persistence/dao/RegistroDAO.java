package ec.edu.uce.erp.ejb.persistence.dao;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.EmpleadoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.RegistroDTO;

public interface RegistroDAO extends AbstractFacade<RegistroDTO>{

	RegistroDTO getUltimate(EmpleadoDTO empleado) throws SeguridadesException;

}
