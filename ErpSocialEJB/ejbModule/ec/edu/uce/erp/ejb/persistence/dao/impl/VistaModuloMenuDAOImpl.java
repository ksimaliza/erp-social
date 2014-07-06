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
import ec.edu.uce.erp.ejb.persistence.dao.VistaModuloMenuDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaModuloMenu;

/**
 * @author home
 *
 */
@Stateless
public class VistaModuloMenuDAOImpl extends AbstractFacadeImpl<VistaModuloMenu> implements VistaModuloMenuDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaModuloMenuDAOImpl.class);
	
	public VistaModuloMenuDAOImpl () {}
	
	public VistaModuloMenuDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaModuloMenu> obtenerVistaModuloMenuCriterios(VistaModuloMenu vistaModuloMenu) throws SeguridadesException {
		slf4jLogger.info("obtenerVistaModuloMenuCriterios");
		
		List<VistaModuloMenu> vistaModuloMenuCol = null;
		
		Predicate predicate = null;
		List<Predicate> criteriaList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<VistaModuloMenu> criteriaQuery = criteriaBuilder.createQuery(VistaModuloMenu.class);
			
			Root<VistaModuloMenu> fromVistaModuloMenu = criteriaQuery.from(VistaModuloMenu.class);
			criteriaQuery.select(fromVistaModuloMenu);
			
			criteriaList = new ArrayList<Predicate>();
			
			//por id perfil
			if (vistaModuloMenu.getIdPerfil()!=null && vistaModuloMenu.getIdPerfil().intValue()>0) {
				predicate = criteriaBuilder.equal(fromVistaModuloMenu.get("idPerfil"), vistaModuloMenu.getIdPerfil());
				criteriaList.add(predicate);
			}
			
			//por id modulo
			if (vistaModuloMenu.getIdModulo()!=null && vistaModuloMenu.getIdModulo().intValue()>0) {
				predicate = criteriaBuilder.equal(fromVistaModuloMenu.get("idModulo"), vistaModuloMenu.getIdModulo());
				criteriaList.add(predicate);
			}
			
			//por id menu
			if (vistaModuloMenu.getIdMenu()!=null && vistaModuloMenu.getIdMenu().intValue()>0) {
				predicate = criteriaBuilder.equal(fromVistaModuloMenu.get("idMenu"), vistaModuloMenu.getIdMenu());
				criteriaList.add(predicate);
			}
			
			//por estado perfil
			if (StringUtils.isNotBlank(vistaModuloMenu.getPerfilEstado())) {
				predicate = criteriaBuilder.equal(fromVistaModuloMenu.get("perfilEstado"), vistaModuloMenu.getPerfilEstado());
				criteriaList.add(predicate);
			}
			
			//por estado modulo
			if (StringUtils.isNotBlank(vistaModuloMenu.getModuloEstado())) {
				predicate = criteriaBuilder.equal(fromVistaModuloMenu.get("moduloEstado"), vistaModuloMenu.getModuloEstado());
				criteriaList.add(predicate);
			}
			
			criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
			
			TypedQuery<VistaModuloMenu> typedQuery = entityManager.createQuery(criteriaQuery);
			
			vistaModuloMenuCol = typedQuery.getResultList();
			
		} catch (Exception e) {
			slf4jLogger.info("Error al obtener al obtenerUsuarioCriterios {}", e);
			throw new SeguridadesException(e);
		} finally {
			predicate = null; criteriaList = null;
		}
		
		return vistaModuloMenuCol;
	}

}
