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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.common.util.UtilAplication;
import ec.edu.uce.erp.ejb.persistence.dao.MenuDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;

/**
 * @author 
 *
 */
@Stateless
public class MenuDAOImpl extends AbstractFacadeImpl<Menu> implements MenuDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MenuDAOImpl.class);
	
	public MenuDAOImpl () {}
	
	public MenuDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Menu> buscarMenuCriterios(Menu menuDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerCompaniaCriterios");
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		List<Menu> menuCol = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Menu> criteriaQuery = criteriaBuilder.createQuery(Menu.class);
			
			Root<Menu> fromMenu = criteriaQuery.from(Menu.class);
			criteriaQuery.select(fromMenu);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por nombre 
			if (StringUtils.isNotBlank(menuDTO.getNombreMenu())) {
				
				Expression<String> nombreMenu = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", menuDTO.getNombreMenu(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromMenu.<String>get("nombreMenu")), nombreMenu);
				criteriaList.add(predicate);
			}
			
			//por descripcion 
			if (StringUtils.isNotBlank(menuDTO.getDescMenu())) {
				
				Expression<String> descMenu = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", menuDTO.getDescMenu(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromMenu.<String>get("descMenu")), descMenu);
				criteriaList.add(predicate);
			}
			
			//por url
			if (StringUtils.isNotBlank(menuDTO.getUrlMenu())) {
				Expression<String> urlMenu = 
						criteriaBuilder.upper(criteriaBuilder.literal((UtilAplication.appendStringBuilder("%", menuDTO.getUrlMenu(), "%").toString())));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromMenu.<String>get("urlMenu")), urlMenu);
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			criteriaQuery.orderBy(criteriaBuilder.asc(fromMenu.get("fechaRegistro")));
			
			TypedQuery<Menu> typedQuery = entityManager.createQuery(criteriaQuery);
			
			menuCol = typedQuery.getResultList();
			
			if (CollectionUtils.isNotEmpty(menuCol)) {
				CollectionUtils.select(menuCol, new org.apache.commons.collections.Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						Menu menu = (Menu)arg0;
						menu.getSegtModulos().size();
						return true;
					}
				});
			}
			
		}catch (Exception e) {
			throw new SeguridadesException(e);
		}
		
		return menuCol;
	}

}
