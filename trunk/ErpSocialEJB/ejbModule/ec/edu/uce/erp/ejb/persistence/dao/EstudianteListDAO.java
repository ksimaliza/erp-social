package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;

public interface EstudianteListDAO extends AbstractFacade<EstudianteListDTO>{

	List<EstudianteListDTO> obtenerEstudiante(EstudianteListDTO estudiante)
			throws SeguridadesException;

	List<EstudianteListDTO> getByAnd(EstudianteListDTO objectDTO)
			throws SeguridadesException;

}
