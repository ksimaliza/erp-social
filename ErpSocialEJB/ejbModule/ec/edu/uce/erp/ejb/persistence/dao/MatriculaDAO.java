package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;

public interface MatriculaDAO extends AbstractFacade<MatriculaDTO>{

	List<MatriculaDTO> getAll(MatriculaDTO matricula)
			throws SeguridadesException;

	
}
