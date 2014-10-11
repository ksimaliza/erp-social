package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.VistaActaBienDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaActaBien;

public class VistaActaBienDAOImpl extends AbstractFacadeImpl<VistaActaBien> implements VistaActaBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaActaBien.class);
	
	public VistaActaBienDAOImpl () {}
	
	public VistaActaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaActaBien> obtenerActaBienCriterios(VistaActaBien vistaActaBien) throws SeguridadesException {
		
		List<VistaActaBien> colVistaActaBien = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaActaBien> criteriaQuery = criteriaBuilder.createQuery(VistaActaBien.class);
			
			Root<VistaActaBien> fromVistaActaBien = criteriaQuery.from(VistaActaBien.class);
			criteriaQuery.select(fromVistaActaBien);
			
			criteriaList = new ArrayList<Predicate>();
			
			if (CollectionUtils.isNotEmpty(vistaActaBien.getNpColActBiePk())) {
				predicate = criteriaBuilder.in(fromVistaActaBien.get("actBiePk")).value(vistaActaBien.getNpColActBiePk());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<VistaActaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			colVistaActaBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtenerActaBienCriterios {}", e.toString());
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return colVistaActaBien;
	}

}
