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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.VistaHistoricoTransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;

/**
 * @author 
 *
 */
@Stateless
public class VistaHistoricoTransaccionDAOImpl extends
		AbstractFacadeImpl<VistaHistoricoTransaccion> implements VistaHistoricoTransaccionDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaHistoricoTransaccionDAOImpl.class);

	public VistaHistoricoTransaccionDAOImpl () {}
	
	public VistaHistoricoTransaccionDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaHistoricoTransaccion> obtenerVistaHistoricoTransaccionCriterios(
			VistaHistoricoTransaccion vistaHistoricoTransaccion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerVistaHistoricoTransaccionCriterios");
		
		List<VistaHistoricoTransaccion> vistaCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaHistoricoTransaccion> criteriaQuery = criteriaBuilder.createQuery(VistaHistoricoTransaccion.class);
			
			Root<VistaHistoricoTransaccion> fromVista = criteriaQuery.from(VistaHistoricoTransaccion.class);
			criteriaQuery.select(fromVista);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id empresa
			if (vistaHistoricoTransaccion.getEmrPk()!=null && vistaHistoricoTransaccion.getEmrPk()>0) {
				predicate = criteriaBuilder.equal(fromVista.get("emrPk"), vistaHistoricoTransaccion.getEmrPk());
				criteriaList.add(predicate);
			}
			
			//por id usuario
			if (vistaHistoricoTransaccion.getIdUsuario()!=null && vistaHistoricoTransaccion.getIdUsuario()>0) {
				predicate = criteriaBuilder.equal(fromVista.get("idUsuario"), vistaHistoricoTransaccion.getIdUsuario());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<VistaHistoricoTransaccion> typedQuery = entityManager.createQuery(criteriaQuery);
			
			vistaCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al obtenerVistaHistoricoTransaccionCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return vistaCol;
	}

}
