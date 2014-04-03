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
import ec.edu.uce.erp.ejb.persistence.dao.MarcaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;

/**
 * @author 
 *
 */
@Stateless
public class MarcaBienDAOImpl extends AbstractFacadeImpl<MarcaBien> implements MarcaBienDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MarcaBienDAOImpl.class);
	
	public MarcaBienDAOImpl () {}
	
	public MarcaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<MarcaBien> buscarMarcaBienCriterios(MarcaBien marcaBien) throws SeguridadesException {
		
		slf4jLogger.info("buscarMarcaBienCriterios");
		
		List<MarcaBien> listMarcaBien = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<MarcaBien> criteriaQuery = criteriaBuilder.createQuery(MarcaBien.class);
			
			Root<MarcaBien> fromLineaBien = criteriaQuery.from(MarcaBien.class);
			criteriaQuery.select(fromLineaBien);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre 
			if (StringUtils.isNotBlank(marcaBien.getMarBienNombre())) {
				Expression<String> marcaBienNombre = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", marcaBien.getMarBienNombre(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromLineaBien.<String>get("marBienNombre")), marcaBienNombre);
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(marcaBien.getMarBienEstado())) {
				predicate = criteriaBuilder.equal(fromLineaBien.get("marBienEstado"), marcaBien.getMarBienEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<MarcaBien> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listMarcaBien = typedQuery.getResultList();
			
		} catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return listMarcaBien;
	}

}
