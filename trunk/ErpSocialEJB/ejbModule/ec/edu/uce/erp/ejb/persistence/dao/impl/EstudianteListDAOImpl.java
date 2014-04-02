package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteListDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteListDTO;

public class EstudianteListDAOImpl extends AbstractFacadeImpl<EstudianteListDTO> implements EstudianteListDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteListDAOImpl.class);
	
	public EstudianteListDAOImpl() {
		super();
		
	}

	public EstudianteListDAOImpl(EntityManager entityManager) {
		super(entityManager);
			}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstudianteListDTO> obtenerEstudiante(EstudianteListDTO estudiante) throws SeguridadesException {
		
		slf4jLogger.info("obtenerEstudiante");
		
		List<EstudianteListDTO> estudiantes = null;
		
		try {
			
			Query query = entityManager.createQuery("select m from mat_estudiante_vie m");
			estudiantes = query.getResultList();
			
						
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return estudiantes;
	}
}
