/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.ejb.persistence.dao.BienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.CabeceraBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DetalleBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.BienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CabeceraBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DetalleBienDAOImpl;
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
	
	private BienDAO bienDAO;
	private DetalleBienDAO detalleBienDAO;
	private CabeceraBienDAO cabeceraBienDAO;

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

	@Override
	public BienDAO getBienDAOImpl() {
		if (bienDAO == null) {
			bienDAO = new BienDAOImpl(entityManager);
		}
		return bienDAO;
	}

	@Override
	public DetalleBienDAO getDetalleBienDAOImpl() {
		if (detalleBienDAO == null) {
			detalleBienDAO = new DetalleBienDAOImpl(entityManager);
		}
		return detalleBienDAO;
	}

	@Override
	public CabeceraBienDAO getCabeceraBienDAOImpl() {
		if (cabeceraBienDAO == null) {
			cabeceraBienDAO = new CabeceraBienDAOImpl(entityManager);
		}
		return cabeceraBienDAO;
	}


}
