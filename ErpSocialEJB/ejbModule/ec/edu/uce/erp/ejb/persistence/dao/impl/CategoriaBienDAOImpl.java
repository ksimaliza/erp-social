/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.CategoriaBienDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;

/**
 * @author 
 *
 */
@Stateless
public class CategoriaBienDAOImpl extends AbstractFacadeImpl<CategoriaBien> implements CategoriaBienDAO{
	
	public CategoriaBienDAOImpl () {}
	
	public CategoriaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

}
