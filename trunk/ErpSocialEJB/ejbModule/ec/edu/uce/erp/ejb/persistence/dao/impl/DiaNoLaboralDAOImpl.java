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
import ec.edu.uce.erp.ejb.persistence.dao.DiaNoLaboralDAO;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.DiaNoLaboralDTO;

public class DiaNoLaboralDAOImpl extends AbstractFacadeImpl<DiaNoLaboralDTO> implements DiaNoLaboralDAO{

	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DiaNoLaboralDAOImpl.class);
	
	public DiaNoLaboralDAOImpl() {
		super();
	}

	public DiaNoLaboralDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<DiaNoLaboralDTO> getAll(int year) throws SeguridadesException
	{
		slf4jLogger.info("getAll");
		
		List<DiaNoLaboralDTO> list = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<DiaNoLaboralDTO> criteriaQuery = criteriaBuilder.createQuery(DiaNoLaboralDTO.class);
			
			Root<DiaNoLaboralDTO> fromUsuario = criteriaQuery.from(DiaNoLaboralDTO.class);
			criteriaQuery.select(fromUsuario);
			
			criteriaList = new ArrayList<Predicate>();
						
			//por anio
			if (year!=0) {
				predicate = criteriaBuilder.equal(fromUsuario.get("dnlAnio"), year);
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<DiaNoLaboralDTO> typedQuery = entityManager.createQuery(criteriaQuery);
			
			list = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al getAll {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return list;
		
	}
	
}
