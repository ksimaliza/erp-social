/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.dao.VistaHistoricoTransaccionDAO;

/**
 * @author
 *
 */
@Local
public interface FactoryReportesDAO {
	
	VistaHistoricoTransaccionDAO getVistaHistoricoTransaccionDAO();

}
