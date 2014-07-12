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
import ec.edu.uce.erp.ejb.persistence.dao.MenuUsuarioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.MenuUsuario;

/**
 * @author home
 *
 */
@Stateless
public class MenuUsuarioDAOImpl extends AbstractFacadeImpl<MenuUsuario> implements MenuUsuarioDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(MenuUsuarioDAOImpl.class);
	
	public MenuUsuarioDAOImpl () {}
	
	public MenuUsuarioDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public List<MenuUsuario> obtenerMenuUsuarioCriterios (MenuUsuario menuUsuario) throws SeguridadesException {
		
		slf4jLogger.info("obtenerMenuUsuarioCriterios");
		
		List<MenuUsuario> usuarioCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<MenuUsuario> criteriaQuery = criteriaBuilder.createQuery(MenuUsuario.class);
			
			Root<MenuUsuario> fromUsuario = criteriaQuery.from(MenuUsuario.class);
			criteriaQuery.select(fromUsuario);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id usuario
			if (menuUsuario.getIdUsuario() != null && menuUsuario.getIdUsuario()>0) {
				predicate = criteriaBuilder.equal(fromUsuario.get("idUsuario"), menuUsuario.getIdUsuario());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<MenuUsuario> typedQuery = entityManager.createQuery(criteriaQuery);
			
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
