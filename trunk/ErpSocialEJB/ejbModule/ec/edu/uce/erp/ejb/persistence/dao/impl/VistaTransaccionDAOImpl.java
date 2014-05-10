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
import ec.edu.uce.erp.ejb.persistence.dao.VistaTransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;

/**
 * @author 
 *
 */
@Stateless
public class VistaTransaccionDAOImpl extends AbstractFacadeImpl<VistaTransaccion> implements VistaTransaccionDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaTransaccionDAOImpl.class);
	
	public VistaTransaccionDAOImpl () {}
	
	public VistaTransaccionDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<VistaTransaccion> obtenerVistaTransaccionCriterios (VistaTransaccion vistaTransaccion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerVistaTransaccionCriterios");
		
		List<VistaTransaccion> listVistaTransaccion = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaTransaccion> criteriaQuery = criteriaBuilder.createQuery(VistaTransaccion.class);
			
			Root<VistaTransaccion> fromVistaTransaccion = criteriaQuery.from(VistaTransaccion.class);
			criteriaQuery.select(fromVistaTransaccion);
			
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			Predicate predicate = null;
			
			//por id de bien
			if (vistaTransaccion.getBieFk() != null && vistaTransaccion.getBieFk() > 0) {
				predicate = criteriaBuilder.equal(fromVistaTransaccion.get("bieFk"), vistaTransaccion.getBieFk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(vistaTransaccion.getTraEstado())) {
				predicate = criteriaBuilder.equal(fromVistaTransaccion.get("traEstado"), vistaTransaccion.getTraEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<VistaTransaccion> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listVistaTransaccion = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerVistaTransaccionCriterios {} - {}", e.toString(), e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listVistaTransaccion;
		
	}

}
