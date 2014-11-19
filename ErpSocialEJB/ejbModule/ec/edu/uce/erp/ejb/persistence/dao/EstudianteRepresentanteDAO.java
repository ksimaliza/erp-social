package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteRepresentanteDTO;


public interface EstudianteRepresentanteDAO extends AbstractFacade<EstudianteRepresentanteDTO>{

	List<EstudianteRepresentanteDTO> getByStudent(EstudianteDTO estudiante)
			throws SeguridadesException;

}
