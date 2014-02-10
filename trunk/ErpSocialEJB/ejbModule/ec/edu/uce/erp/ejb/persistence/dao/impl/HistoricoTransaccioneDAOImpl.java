/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.persistence.EntityManager;

import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistoricoTransaccione;

/**
 * @author
 * 
 */
public class HistoricoTransaccioneDAOImpl extends AbstractFacadeImpl<HistoricoTransaccione> implements HistoricoTransaccioneDAO {
	
	public HistoricoTransaccioneDAOImpl () {}
	
	public HistoricoTransaccioneDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

}
