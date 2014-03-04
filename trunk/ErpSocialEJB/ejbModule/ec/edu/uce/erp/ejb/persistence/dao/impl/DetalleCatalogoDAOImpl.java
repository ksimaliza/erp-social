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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.DetalleCatalogoDAO;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;

/**
 * @author 
 *
 */
public class DetalleCatalogoDAOImpl extends AbstractFacadeImpl<DetalleCatalogo> implements DetalleCatalogoDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(DetalleCatalogoDAOImpl.class);
	
	public DetalleCatalogoDAOImpl () {}
	
	public DetalleCatalogoDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<DetalleCatalogo> obtenerDetalleCatalogoCriterios(
			DetalleCatalogo detalleCatalogo) throws SeguridadesException {
		
		slf4jLogger.info("obtenerDetalleCatalogoCriterios");
		
		List<DetalleCatalogo> listDetalleCatalogo = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<DetalleCatalogo> criteriaQuery = criteriaBuilder.createQuery(DetalleCatalogo.class);
			
			Root<DetalleCatalogo> fromUsuario = criteriaQuery.from(DetalleCatalogo.class);
			criteriaQuery.select(fromUsuario);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id cabecera
			if (StringUtils.isNotBlank(detalleCatalogo.getId().getCabCatalogoFk())) {
				predicate = criteriaBuilder.equal(fromUsuario.get("id.cabCatalogoFk"), detalleCatalogo.getId().getCabCatalogoFk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(detalleCatalogo.getDetCatalogoEstado())) {
				predicate = criteriaBuilder.equal(fromUsuario.get("detCatalogoEstado"), detalleCatalogo.getDetCatalogoEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<DetalleCatalogo> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listDetalleCatalogo = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerDetalleCatalogoCriterios {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return listDetalleCatalogo;
	}

}
