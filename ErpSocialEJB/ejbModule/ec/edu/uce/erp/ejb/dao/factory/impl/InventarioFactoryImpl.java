/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.dao.factory.InventarioFactory;
import ec.edu.uce.erp.ejb.persistence.dao.ActaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.BienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.CabeceraBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.CategoriaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.DetalleBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.LineaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MarcaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroEmpresaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;
import ec.edu.uce.erp.ejb.persistence.dao.TransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.VistaActaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.VistaBienDAO;
import ec.edu.uce.erp.ejb.persistence.dao.VistaEmpleadoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.VistaTransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ActaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.BienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CabeceraBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.CategoriaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.DetalleBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.HistoricoTransaccioneDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.LineaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.MarcaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ParametroEmpresaDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.ProveedorDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.TransaccionDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaActaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaBienDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaEmpleadoDAOImpl;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaTransaccionDAOImpl;

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
	private ActaBienDAO actaBienDAO;
	
	private VistaBienDAO vistaBienDAO;
	private VistaEmpleadoDAO vistaEmpleadoDAO;
	private VistaTransaccionDAO vistaTransaccionDAO;
	private VistaActaBienDAO vistaActaBienDAO;
	
	private ParametroEmpresaDAO parametroEmpresaDAO;
	
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

	@Override
	public VistaEmpleadoDAO getVistaEmpleadoDAOImpl() {
		if (vistaEmpleadoDAO == null) {
			vistaEmpleadoDAO = new VistaEmpleadoDAOImpl(entityManager);
		}
		return vistaEmpleadoDAO;
	}

	@Override
	public VistaTransaccionDAO getVistaTransaccionDAOImpl() {
		if (vistaTransaccionDAO == null) {
			vistaTransaccionDAO = new VistaTransaccionDAOImpl(entityManager);
		}
		return vistaTransaccionDAO;
	}

	/**
	 * @return the actaBienDAO
	 */
	public ActaBienDAO getActaBienDAOImpl() {
		if (actaBienDAO == null) {
			actaBienDAO = new ActaBienDAOImpl(entityManager);
		}
		return actaBienDAO;
	}

	@Override
	public VistaActaBienDAO getVistaActaBienDAOImpl() {
		if (vistaActaBienDAO == null) {
			vistaActaBienDAO = new VistaActaBienDAOImpl(entityManager);
		}
		return vistaActaBienDAO;
	}

	@Override
	public ParametroEmpresaDAO getParametroEmpresaDAOImpl() {
		if (parametroEmpresaDAO == null) {
			parametroEmpresaDAO = new ParametroEmpresaDAOImpl(entityManager);
		}
		return parametroEmpresaDAO;
	}

}
