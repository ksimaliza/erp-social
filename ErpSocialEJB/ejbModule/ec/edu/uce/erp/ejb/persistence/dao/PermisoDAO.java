package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.PermisoListDTO;

public interface PermisoDAO extends AbstractFacade<PermisoDTO> {

	List<PermisoListDTO> findAll(PermisoListDTO permiso)
			throws SeguridadesException;

}
