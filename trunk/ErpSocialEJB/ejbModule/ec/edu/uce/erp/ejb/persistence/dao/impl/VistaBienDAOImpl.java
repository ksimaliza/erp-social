/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.VistaBienDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;

/**
 * @author 
 *
 */
public class VistaBienDAOImpl extends AbstractFacadeImpl<VistaBien> implements VistaBienDAO {
	
	public VistaBienDAOImpl () {}
	
	public VistaBienDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

}
