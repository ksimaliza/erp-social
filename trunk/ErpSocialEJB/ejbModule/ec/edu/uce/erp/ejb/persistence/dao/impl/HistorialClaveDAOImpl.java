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
import ec.edu.uce.erp.ejb.persistence.dao.HistorialClaveDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistorialClave;

/**
 * @author 
 *
 */
public class HistorialClaveDAOImpl extends AbstractFacadeImpl<HistorialClave> implements HistorialClaveDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(HistorialClaveDAOImpl.class);
	
	public HistorialClaveDAOImpl () {}
	
	public HistorialClaveDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<HistorialClave> obtenerHistorialClaveCriterios(HistorialClave historialClaveDTO) throws SeguridadesException {
		
		slf4jLogger.info("metodo obtenerHistorialClaveCriterios	");
		
		List<HistorialClave> listaClavesEncontradas = null;
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<HistorialClave> criteriaQuery = criteriaBuilder.createQuery(HistorialClave.class);
			
			Root<HistorialClave> rootHistorialClave = criteriaQuery.from(HistorialClave.class);
			
			CriteriaQuery<HistorialClave> select = criteriaQuery.select(rootHistorialClave);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por idUsuario
			if (historialClaveDTO.getSegtUsuario().getIdUsuario()!=null) {
				predicate = criteriaBuilder.equal(rootHistorialClave.get("segtUsuario").get("idUsuario"), historialClaveDTO.getSegtUsuario().getIdUsuario());
				criteriaList.add(predicate);
			}
			
			//por passUsuario
			if (!StringUtils.isEmpty(historialClaveDTO.getPassUsuario())) {
				predicate = criteriaBuilder.equal(rootHistorialClave.get("passUsuario"), historialClaveDTO.getPassUsuario());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<HistorialClave> typedQuery = entityManager.createQuery(select);
			listaClavesEncontradas = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("error al obtenerHistorioClavesCriterios: {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		return listaClavesEncontradas;
		
	}

}
