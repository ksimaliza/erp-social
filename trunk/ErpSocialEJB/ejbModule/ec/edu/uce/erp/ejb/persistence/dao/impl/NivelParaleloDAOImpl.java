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
import ec.edu.uce.erp.ejb.persistence.dao.NivelParaleloDAO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelParaleloDTO;

public class NivelParaleloDAOImpl extends AbstractFacadeImpl<NivelParaleloDTO> implements NivelParaleloDAO{

	private static final Logger slf4jLogger = LoggerFactory.getLogger(NivelParaleloDAOImpl.class);
	
	public NivelParaleloDAOImpl() {
		super();
	}

	public NivelParaleloDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<NivelParaleloDTO> getAll(NivelParaleloDTO nivelParalelo) throws SeguridadesException
	{
		slf4jLogger.info("getAll");
		
		List<NivelParaleloDTO> resultado = null;
		
		List<Predicate> criteriaList = new ArrayList<Predicate>();
		Predicate predicate = null;
		
		try {
			CriteriaBuilder cb=entityManager.getCriteriaBuilder();
			CriteriaQuery<NivelParaleloDTO> cq=cb.createQuery(NivelParaleloDTO.class);
			Root<NivelParaleloDTO> from = cq.from(NivelParaleloDTO.class);
			
			//por nivel
			if (nivelParalelo.getMatNivel()!=null) {
				predicate = cb.equal(from.get("matNivel"),nivelParalelo.getMatNivel());
				criteriaList.add(predicate);
			}
	
			//por paralelo
			if (nivelParalelo.getMatParalelo()!=null) {
				predicate = cb.equal(from.get("matParalelo"),nivelParalelo.getMatParalelo());
				criteriaList.add(predicate);
			}
			
			//por empresa
			if (nivelParalelo.getNpaEmpresa()!=null) {
				predicate = cb.equal(from.get("npaEmpresa"),nivelParalelo.getNpaEmpresa());
				criteriaList.add(predicate);
			}
			
			
			if(criteriaList!=null && criteriaList.size()>0)
				cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<NivelParaleloDTO> typedQuery = entityManager.createQuery(cq);
			resultado = typedQuery.getResultList();
			
			criteriaList = new ArrayList<Predicate>();
		} catch (Exception e) {
			slf4jLogger.info("No se pudo obtener los parametros de la BD {}", e);
			throw new SeguridadesException(e);
		}
		return resultado;		
	}
	
	
	
}
