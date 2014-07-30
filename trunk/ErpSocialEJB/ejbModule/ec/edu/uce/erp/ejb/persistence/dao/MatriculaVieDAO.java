package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaVieDTO;

public interface MatriculaVieDAO extends AbstractFacade<MatriculaVieDTO>{

	List<MatriculaVieDTO> getByAnd(MatriculaVieDTO objectDTO)
			throws SeguridadesException;

	List<MatriculaVieDTO> getBySubquery(MatriculaVieDTO objetoDTO)
			throws SeguridadesException;

	
}
