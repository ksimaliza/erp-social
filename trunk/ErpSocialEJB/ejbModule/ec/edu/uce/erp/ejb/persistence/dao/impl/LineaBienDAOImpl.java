/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
import ec.edu.uce.erp.ejb.persistence.dao.LineaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;

/**
 * @author 
 *
 */
public class LineaBienDAOImpl extends AbstractFacadeImpl<LineaBien> implements LineaBienDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(LineaBienDAOImpl.class);
	
	public LineaBienDAOImpl() {}
	
	public LineaBienDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<LineaBien> obtenerLineaBienCriterios(LineaBien lineaBien) throws SeguridadesException {
		slf4jLogger.info("obtenerLineaBienCriterios");
		List<LineaBien> listLineaBien = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<LineaBien> criteriaQuery = criteriaBuilder.createQuery(LineaBien.class);
			
			Root<LineaBien> fromLineaBien = criteriaQuery.from(LineaBien.class);
			criteriaQuery.select(fromLineaBien);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre 
			if (StringUtils.isNotBlank(lineaBien.getLinBienNombre())) {
				Expression<String> linBienNombre = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", lineaBien.getLinBienNombre(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromLineaBien.<String>get("linBienNombre")), linBienNombre);
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(lineaBien.getLinBienEstado())) {
				predicate = criteriaBuilder.equal(fromLineaBien.get("linBienEstado"), lineaBien.getLinBienEstado());
				criteriaList.add(predicate);
			}
			
			//por id del padre
			if (lineaBien.getCatBienPk()!=null && lineaBien.getCatBienPk()!=0) {
				predicate = criteriaBuilder.equal(fromLineaBien.get("catBienPk"), lineaBien.getCatBienPk());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<LineaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listLineaBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerLineaBienCriterios {}", e.getCause());
			throw new SeguridadesException(e);
		}
		
		return listLineaBien;
	}

}
