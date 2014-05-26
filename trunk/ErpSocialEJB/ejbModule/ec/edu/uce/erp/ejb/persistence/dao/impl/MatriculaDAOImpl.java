package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.MatriculaDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MatriculaDTO;

public class MatriculaDAOImpl extends AbstractFacadeImpl<MatriculaDTO> implements MatriculaDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	
	public MatriculaDAOImpl() {
		super();
	}

	public MatriculaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<MatriculaDTO> getAll(MatriculaDTO matricula) throws SeguridadesException
	{
		slf4jLogger.info("getAll");
		
		List<MatriculaDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<MatriculaDTO> cq=cb.createQuery(MatriculaDTO.class);
			Root<MatriculaDTO> from = cq.from(MatriculaDTO.class);
			
			//por estudiante
			if (matricula.getMatEstudiante().getEstCodigo()!=null) {
				predicate = cb.equal(from.get("regEstudiante"),matricula.getMatEstudiante().getEstCodigo());
				criteriaList.add(predicate);
			}
				
			if(criteriaList!=null && criteriaList.size()>0)
				cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<MatriculaDTO> typedQuery = entityManager.createQuery(cq);
			resultado = typedQuery.getResultList();
			
			criteriaList = new ArrayList<Predicate>();
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;		
	}
	

	
}
