/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.HistoricoTransaccioneDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ProveedorDAOImpl;

/**
 * @author 
 *
 */
@Stateless
public class InventarioFactoryImpl implements InventarioFactory{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private ProveedorDAO proveedorDAO;
	
	private HistoricoTransaccioneDAO historicoTransaccioneDAO;

	@Override
	public ProveedorDAO getProveedorDAOImpl() {
		
		if (proveedorDAO == null) {
			proveedorDAO = new ProveedorDAOImpl(entityManager);
		}
		return proveedorDAO;
	}
	
	@Override
	public HistoricoTransaccioneDAO getHistoricoTransaccioneDAOImpl() {
		if (historicoTransaccioneDAO == null) {
			historicoTransaccioneDAO = new HistoricoTransaccioneDAOImpl(entityManager);
		}
		return historicoTransaccioneDAO;
	}

}
