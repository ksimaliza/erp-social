package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.ProfesorDTO;

public interface ProfesorDAO extends AbstractFacade<ProfesorDTO> {

	List<DocenteListDTO> obtenerDocente(DocenteListDTO docente)
			throws SeguridadesException;

	List<DocenteListDTO> getByAnd(DocenteListDTO objectDTO)
			throws SeguridadesException;

}
