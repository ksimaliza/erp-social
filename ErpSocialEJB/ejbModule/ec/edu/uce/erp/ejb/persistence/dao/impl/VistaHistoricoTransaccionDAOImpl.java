/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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
			
			//por estado
			if (StringUtils.isNotBlank(vistaHistoricoTransaccion.getEstado())) {
				predicate = criteriaBuilder.equal(fromVista.get("estado"), vistaHistoricoTransaccion.getEstado());
				criteriaList.add(predicate);
			}
			
			//por nombres completos de usuario
			if (StringUtils.isNotBlank(vistaHistoricoTransaccion.getUsuario())) {
				Expression<String> nombreUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaHistoricoTransaccion.getUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVista.<String>get("usuario")), nombreUsuario);
				criteriaList.add(predicate);
			}
			
			//por ci de usuario
			if (StringUtils.isNotBlank(vistaHistoricoTransaccion.getCiUsuario())) {
				Expression<String> ciUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaHistoricoTransaccion.getCiUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVista.<String>get("ciUsuario")), ciUsuario);
				criteriaList.add(predicate);
			}
			
			//por login usuario 
			if (StringUtils.isNotBlank(vistaHistoricoTransaccion.getLoginUsuario())) {
				Expression<String> loginUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaHistoricoTransaccion.getLoginUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromVista.<String>get("loginUsuario")), loginUsuario);
				criteriaList.add(predicate);
			}
			
			//between desde - hasta
			if (vistaHistoricoTransaccion.getNpFechaDesde()!=null) {
				slf4jLogger.info("vistaHistoricoTransaccion.getNpFechaDesde: {}" , vistaHistoricoTransaccion.getNpFechaDesde());
				
				if (vistaHistoricoTransaccion.getNpFechaHasta()==null) {
					vistaHistoricoTransaccion.setNpFechaHasta(UtilAplication.obtenerFechaActual());
				}
				
				vistaHistoricoTransaccion.setNpFechaDesde(UtilAplication.concatenarFecha(vistaHistoricoTransaccion.getNpFechaDesde(), 0, 0, 0));
				vistaHistoricoTransaccion.setNpFechaHasta(UtilAplication.concatenarFecha(vistaHistoricoTransaccion.getNpFechaHasta(), 23, 59, 59));
				
				slf4jLogger.info("desde: {}", vistaHistoricoTransaccion.getNpFechaDesde());
				slf4jLogger.info("hasta: {}", vistaHistoricoTransaccion.getNpFechaHasta());
				
				predicate = criteriaBuilder.between(fromVista.<Date>get("fechaUltimoIngreso"), vistaHistoricoTransaccion.getNpFechaDesde(), vistaHistoricoTransaccion.getNpFechaHasta());
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
