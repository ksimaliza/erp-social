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
import ec.edu.uce.erp.ejb.persistence.dao.TransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;

/**
 * @author wilmerPC
 *
 */
@Stateless
public class TransaccionDAOImpl extends AbstractFacadeImpl<Transaccion> implements TransaccionDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(TransaccionDAOImpl.class);
	
	public TransaccionDAOImpl() {}
	
	public TransaccionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Transaccion> obtenerTransaccionCriterios(Transaccion transaccion) throws SeguridadesException {
		slf4jLogger.info("obtenerTransaccionCriterios");
		
		List<Transaccion> listTransaccion = null;
		
		try {
			
			Predicate predicate = null;
			List<Predicate> criteriaList = new ArrayList<Predicate>();
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Transaccion> criteriaQuery = criteriaBuilder.createQuery(Transaccion.class);
			
			Root<Transaccion> fromTransaccion = criteriaQuery.from(Transaccion.class);
			criteriaQuery.select(fromTransaccion);
			
			//por id de bien
			if (transaccion.getBieFk() != null && transaccion.getBieFk()>0) {
				predicate = criteriaBuilder.equal(fromTransaccion.get("bieFk"), transaccion.getBieFk());
				criteriaList.add(predicate);
			}
			
			//por estado
			if (StringUtils.isNotBlank(transaccion.getTraEstado())) {
				predicate = criteriaBuilder.equal(fromTransaccion.get("traEstado"), transaccion.getTraEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			TypedQuery<Transaccion> typedQuery = entityManager.createQuery(criteriaQuery);
			
			listTransaccion = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerTransaccionCriterios {} - {}", e.toString(), e.getCause().getMessage());
			throw new SeguridadesException(e);
		}
		
		return listTransaccion;
	}
	
	

}
