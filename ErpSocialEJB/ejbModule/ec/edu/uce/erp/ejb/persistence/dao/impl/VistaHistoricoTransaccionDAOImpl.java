/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.dao.VistaHistoricoTransaccionDAO;
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;

/**
 * @author 
 *
 */
public class VistaHistoricoTransaccionDAOImpl extends
		AbstractFacadeImpl<VistaHistoricoTransaccion> implements VistaHistoricoTransaccionDAO {
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(VistaHistoricoTransaccionDAOImpl.class);

	public VistaHistoricoTransaccionDAOImpl () {}
	
	public VistaHistoricoTransaccionDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<VistaHistoricoTransaccionDAO> obtenerVistaHistoricoTransaccionCriterios(
			VistaHistoricoTransaccion vistaHistoricoTransaccion) throws SeguridadesException {
		
		slf4jLogger.info("obtenerVistaHistoricoTransaccionCriterios");
		
		// TODO Auto-generated method stub
		return null;
	}

}
