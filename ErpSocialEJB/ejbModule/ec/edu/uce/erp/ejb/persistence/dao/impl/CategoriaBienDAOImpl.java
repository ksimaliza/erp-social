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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.CategoriaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;

/**
 * @author 
 *
 */
@Stateless
public class CategoriaBienDAOImpl extends AbstractFacadeImpl<CategoriaBien> implements CategoriaBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(CategoriaBienDAOImpl.class);
	
	public CategoriaBienDAOImpl () {}
	
	public CategoriaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<CategoriaBien> obtenerCategoriaBienCriterios(CategoriaBien categoriaBien) throws SeguridadesException {
		
		slf4jLogger.info("obtenerCategoriaBienCriterios");
		
		List<CategoriaBien> listLineaBien = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<CategoriaBien> criteriaQuery = criteriaBuilder.createQuery(CategoriaBien.class);
			
			Root<CategoriaBien> fromLineaBien = criteriaQuery.from(CategoriaBien.class);
			criteriaQuery.select(fromLineaBien);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre 
			if (StringUtils.isNotBlank(categoriaBien.getCatBienNombre())) {
				Expression<String> catBienNombre = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", categoriaBien.getCatBienNombre(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromLineaBien.<String>get("catBienNombre")), catBienNombre);
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(categoriaBien.getCatBienEstado())) {
				predicate = criteriaBuilder.equal(fromLineaBien.get("catBienEstado"), categoriaBien.getCatBienEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<CategoriaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listLineaBien = typedQuery.getResultList();
		} catch (Exception e) {
			slf4jLogger.error("error al obtenerCategoriaBienCriterios {}", e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listLineaBien;
	}

}
