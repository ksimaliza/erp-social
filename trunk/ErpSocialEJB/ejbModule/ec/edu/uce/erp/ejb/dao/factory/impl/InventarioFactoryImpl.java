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
import ec.edu.uce.erp.ejb.persistence.dao.CategoriaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DetalleBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.LineaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MarcaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.TransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.VistaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.BienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CabeceraBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CategoriaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DetalleBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.HistoricoTransaccioneDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.LineaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MarcaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ProveedorDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.TransaccionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaBienDAOImpl;

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
	
	private LineaBienDAO lineaBienDAO;
	private CategoriaBienDAO categoriaBienDAO;
	private MarcaBienDAO marcaBienDAO;
	private TransaccionDAO transaccionDAO;
	
	private VistaBienDAO vistaBienDAO;

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

	@Override
	public LineaBienDAO getLineaBienDAOImpl() {
		if (lineaBienDAO == null) {
			lineaBienDAO = new LineaBienDAOImpl(entityManager);
		}
		return lineaBienDAO;
	}

	@Override
	public CategoriaBienDAO getCategoriaBienDAOImpl() {
		if (categoriaBienDAO == null) {
			categoriaBienDAO = new CategoriaBienDAOImpl(entityManager);
		}
		return categoriaBienDAO;
	}

	@Override
	public MarcaBienDAO getMarcaBienDAOImpl() {
		if (marcaBienDAO == null) {
			marcaBienDAO = new MarcaBienDAOImpl(entityManager);
		}
		return marcaBienDAO;
	}

	@Override
	public VistaBienDAO getVistaBienDAOImpl() {
		if (vistaBienDAO == null) {
			vistaBienDAO = new VistaBienDAOImpl(entityManager);
		}
		return vistaBienDAO;
	}

	@Override
	public TransaccionDAO getTransaccionDAOImpl() {
		if (transaccionDAO == null) {
			transaccionDAO = new TransaccionDAOImpl(entityManager);
		}
		return transaccionDAO;
	}

}
