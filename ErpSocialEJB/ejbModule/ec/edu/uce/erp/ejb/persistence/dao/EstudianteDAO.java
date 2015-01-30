package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;

public interface EstudianteDAO extends AbstractFacade<EstudianteDTO> {

	List<EstudianteListDTO> obtenerEstudiante(EstudianteListDTO estudiante)
			throws SeguridadesException;
	
	public void updateEstadoEstudiante(Integer codEstudiante) throws SeguridadesException;
	
	public void eliminarEstudiante(Integer idPersona) throws SeguridadesException;

	
}