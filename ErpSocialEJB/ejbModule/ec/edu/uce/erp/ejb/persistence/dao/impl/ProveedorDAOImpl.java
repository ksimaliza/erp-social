/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.entity.inventario.Proveedor;

/**
 * @author 
 *
 */
@Stateless
public class ProveedorDAOImpl extends AbstractFacadeImpl<Proveedor> implements ProveedorDAO{
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ProveedorDAOImpl.class);
	
	public ProveedorDAOImpl () {}
	
	public ProveedorDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

}
