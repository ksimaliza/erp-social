/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.TransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;

/**
 * @author wilmerPC
 *
 */
@Stateless
public class TransaccionDAOImpl extends AbstractFacadeImpl<Transaccion> implements TransaccionDAO {
	
	public TransaccionDAOImpl() {}
	
	public TransaccionDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
