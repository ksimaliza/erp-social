/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.DetalleBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;

/**
 * @author
 *
 */
@Stateless
public class DetalleBienDAOImpl extends AbstractFacadeImpl<DetalleBien> implements DetalleBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DetalleBienDAOImpl.class);
	
	public DetalleBienDAOImpl () {}
	
	public DetalleBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<DetalleBien> obtenerDetalleBienCriterios(DetalleBien detalleBien) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDetalleBienCriterios");
		
		List<DetalleBien> listDetalleBien = null;
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<DetalleBien> criteriaQuery = criteriaBuilder.createQuery(DetalleBien.class);
			
			Root<DetalleBien> fromUsuario = criteriaQuery.from(DetalleBien.class);
			criteriaQuery.select(fromUsuario);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id cabecera
			if (StringUtils.isNotBlank(detalleBien.getId().getCabBienFk())) {
				predicate = criteriaBuilder.equal(fromUsuario.get("id").get("cabBienFk"), detalleBien.getId().getCabBienFk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(detalleBien.getDetBienEstado())) {
				predicate = criteriaBuilder.equal(fromUsuario.get("detBienEstado"), detalleBien.getDetBienEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<DetalleBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listDetalleBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerDetalleBienCriterios {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return listDetalleBien;
	}

}
