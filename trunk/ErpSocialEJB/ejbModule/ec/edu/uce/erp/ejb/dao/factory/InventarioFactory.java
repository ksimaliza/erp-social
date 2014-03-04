/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.dao.DetalleCatalogoDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ProveedorDAO;

/**
 * @author
 *
 */
@Local
public interface InventarioFactory {
	
	ProveedorDAO getProveedorDAOImpl();
	
	HistoricoTransaccioneDAO getHistoricoTransaccioneDAOImpl();
	
	DetalleCatalogoDAO getCatalogoDAOImpl();

}
