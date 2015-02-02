package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.AsinacionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;

public class AsinacionDAOImpl extends AbstractFacadeImpl<AsinacionDTO> implements AsinacionDAO {

	private static final Logger slf4jLogger = LoggerFactory.getLogger(FaltaDAOImpl.class);	
	
	public AsinacionDAOImpl() {
		super();
		
	}

	public AsinacionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	
	}
	
	@Override
	public List<AsinacionListDTO> findAll(AsinacionListDTO asinacion) throws SeguridadesException
	{
			slf4jLogger.info("findAll");
			List<AsinacionListDTO> resultado = null;
			
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			Predicate predicate = null;
			
			try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<AsinacionListDTO> cq=cb.createQuery(AsinacionListDTO.class);
			Root<AsinacionListDTO> from = cq.from(AsinacionListDTO.class);
			
				
			//por nivel
			if (asinacion.getNpaNivel()!=null) {
				predicate = cb.equal(from.get("npaNivel"),asinacion.getNpaNivel());
				criteriaList.add(predicate);
			}
			//por paralelo
			if (asinacion.getNpaParalelo()!=null) {
				predicate = cb.equal(from.get("npaParalelo"),asinacion.getNpaParalelo());
				criteriaList.add(predicate);
			}
			
			//por empresa
			if (asinacion.getAsiEmpresa()!=null) {
				predicate = cb.equal(from.get("asiEmpresa"),asinacion.getAsiEmpresa());
				criteriaList.add(predicate);
			}
					
			if(criteriaList!=null && criteriaList.size()>0)
				cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<AsinacionListDTO> typedQuery = entityManager.createQuery(cq);
			resultado = typedQuery.getResultList();
			
			criteriaList = new ArrayList<Predicate>();
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
			return resultado;		
	}
	@SuppressWarnings("unchecked")
	public List<AsinacionDTO> asignacionesPorPeriodoProfesor(Integer codPeriodo, Integer codProfesor) {

		Query query;
		String sql = "SELECT p from AsinacionDTO p" + " WHERE p.matPeriodo.perCodigo = :codPeriodo AND " + " p.matProfesor.proPersona = :codProfesor";

		query = entityManager.createQuery(sql);

		query.setParameter("codPeriodo", codPeriodo);
		query.setParameter("codProfesor", codProfesor);

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AsinacionDTO> asignacionesPorPeriodo(Integer codPeriodo) {

		Query query;
		String sql = "SELECT p from AsinacionDTO p" + " WHERE p.matPeriodo.perCodigo = :codPeriodo";

		query = entityManager.createQuery(sql);

		query.setParameter("codPeriodo", codPeriodo);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AsinacionDTO> asignacionesPorPeriodoNivelParaleloMateria(Integer codPeriodo, Integer codNivel, Integer codParelelo, Integer codMateria) {

		Query query;
		String sql = "SELECT p from AsinacionDTO p" + 
		" WHERE p.matPeriodo.perCodigo = :codPeriodo AND " 
				+ " p.matNivelParalelo.matNivel.nivCodigo = :codNivel AND "
				+ " p.matNivelParalelo.matParalelo.parCodigo = :codParelelo AND" 
				+ " p.matMateria.mtrCodigo = :codMateria";

		query = entityManager.createQuery(sql);

		query.setParameter("codPeriodo", codPeriodo);
		query.setParameter("codNivel", codNivel);
		query.setParameter("codParelelo", codParelelo);
		query.setParameter("codMateria", codMateria);

		return query.getResultList();
	}
	
	public List<AsinacionDTO> asignacionesPorPeriodoNivelParalelo(Integer codPeriodo, Integer codNivel, Integer codParelelo) {

		Query query;
		String sql = "SELECT p from AsinacionDTO p" + 
		" WHERE p.matPeriodo.perCodigo = :codPeriodo AND " 
				+ " p.matNivelParalelo.matNivel.nivCodigo = :codNivel AND "
				+ " p.matNivelParalelo.matParalelo.parCodigo = :codParelelo";

		query = entityManager.createQuery(sql);

		query.setParameter("codPeriodo", codPeriodo);
		query.setParameter("codNivel", codNivel);
		query.setParameter("codParelelo", codParelelo);

		return query.getResultList();
	}
}
