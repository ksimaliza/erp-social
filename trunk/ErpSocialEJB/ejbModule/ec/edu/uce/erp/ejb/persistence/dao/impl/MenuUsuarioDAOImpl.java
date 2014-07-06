/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.MenuUsuarioDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.MenuUsuario;

/**
 * @author home
 *
 */
public class MenuUsuarioDAOImpl extends AbstractFacadeImpl<MenuUsuario> implements MenuUsuarioDAO{
	
	public MenuUsuarioDAOImpl () {}
	
	public MenuUsuarioDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

}
