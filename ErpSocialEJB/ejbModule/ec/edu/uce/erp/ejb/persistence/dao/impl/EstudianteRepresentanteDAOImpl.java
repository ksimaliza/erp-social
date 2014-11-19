package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.EstudianteRepresentanteDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.EstudianteRepresentanteDTO;

public class EstudianteRepresentanteDAOImpl extends AbstractFacadeImpl<EstudianteRepresentanteDTO> implements EstudianteRepresentanteDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EstudianteRepresentanteDAOImpl.class);
	
	public EstudianteRepresentanteDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstudianteRepresentanteDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<EstudianteRepresentanteDTO> getByStudent(EstudianteDTO estudiante) throws SeguridadesException
	{
		slf4jLogger.info("obtenerEstudiante");
		List<EstudianteRepresentanteDTO> resultado = null;
		
		
		try {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<EstudianteRepresentanteDTO> cq=cb.createQuery(EstudianteRepresentanteDTO.class);
		Root<EstudianteRepresentanteDTO> from = cq.from(EstudianteRepresentanteDTO.class);
		
		CriteriaQuery<EstudianteRepresentanteDTO> select = cq.select(from);
		

		cq.where(cb.equal(from.get("matEstudiante"),estudiante.getEstCodigo()));
	
		/*
		 * 
		 * criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromUsuario.get("fechaRegistro")));
			
			TypedQuery<Usuario> typedQuery = entityManager.createQuery(criteriaQuery);
			
			usuarioCol = typedQuery.getResultList();
		 * 
		 */
		
		TypedQuery<EstudianteRepresentanteDTO> typedQuery = entityManager.createQuery(select);
		resultado = typedQuery.getResultList();
		
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;
	}

}
