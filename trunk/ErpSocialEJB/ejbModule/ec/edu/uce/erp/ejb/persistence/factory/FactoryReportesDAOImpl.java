/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.factory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.uce.erp.ejb.persistence.dao.VistaHistoricoTransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.dao.impl.VistaHistoricoTransaccionDAOImpl;

/**
 * @author 
 *
 */
@Stateless
public class FactoryReportesDAOImpl implements FactoryReportesDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private VistaHistoricoTransaccionDAO vistaHistoricoTransaccionDAO;

	@Override
	public VistaHistoricoTransaccionDAO getVistaHistoricoTransaccionDAO() {
		
		if (vistaHistoricoTransaccionDAO == null) {
			vistaHistoricoTransaccionDAO = new VistaHistoricoTransaccionDAOImpl(entityManager);
		}
		
		return vistaHistoricoTransaccionDAO;
	}

}
