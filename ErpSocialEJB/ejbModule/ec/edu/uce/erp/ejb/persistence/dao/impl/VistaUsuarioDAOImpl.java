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
import ec.edu.uce.erp.ejb.persistence.dao.VistaUsuarioDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaUsuario;

/**
 * @author 
 *
 */
@Stateless
public class VistaUsuarioDAOImpl extends AbstractFacadeImpl<VistaUsuario> implements VistaUsuarioDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaUsuarioDAOImpl.class);
	
	public VistaUsuarioDAOImpl () {}
	
	public VistaUsuarioDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaUsuario> obtenerVistaUsuarioCriterios(VistaUsuario vistaUsuario) throws SeguridadesException {
		slf4jLogger.info("obtenerVistaUsuarioCriterios");
		
		List<VistaUsuario> usuarioCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaUsuario> criteriaQuery = criteriaBuilder.createQuery(VistaUsuario.class);
			
			Root<VistaUsuario> fromUsuario = criteriaQuery.from(VistaUsuario.class);
			criteriaQuery.select(fromUsuario);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por estado
			if (StringUtils.isNotBlank(vistaUsuario.getEstado())) {
				predicate = criteriaBuilder.equal(fromUsuario.get("estado"), vistaUsuario.getEstado());
				criteriaList.add(predicate);
			}
			
			//por id empresa
			if (vistaUsuario.getEmrPk() != null && vistaUsuario.getEmrPk() > 0) {
				predicate = criteriaBuilder.equal(fromUsuario.get("emrPk"), vistaUsuario.getEmrPk());
				criteriaList.add(predicate);
			}
			
//			//por nombre de usuario
//			if (StringUtils.isNotBlank(vistaUsuario.ge)) {
//				Expression<String> nombreUsuario = 
//						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getNombresUsuario(), "%").toString()));
//				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromUsuario.<String>get("nombresUsuario")), nombreUsuario);
//				criteriaList.add(predicate);
//			}
//			
//			//por apellido de usuario
//			if (StringUtils.isNotBlank(vistaUsuario.getApellidosUsuario())) {
//				Expression<String> apellidoUsuario = 
//						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", usuario.getApellidosUsuario(), "%").toString()));
//				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromUsuario.<String>get("apellidosUsuario")), apellidoUsuario);
//				criteriaList.add(predicate);
//			}
			
			//por ci de usuario
			if (StringUtils.isNotBlank(vistaUsuario.getCiUsuario())) {
				Expression<String> ciUsuario = 
						criteriaBuilder.upper(criteriaBuilder.literal(UtilAplication.appendStringBuilder("%", vistaUsuario.getCiUsuario(), "%").toString()));
				predicate = criteriaBuilder.like(criteriaBuilder.upper(fromUsuario.<String>get("ciUsuario")), ciUsuario);
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<VistaUsuario> typedQuery = entityManager.createQuery(criteriaQuery);
			
			usuarioCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al obtenerUsuarioCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return usuarioCol;
	}

}
