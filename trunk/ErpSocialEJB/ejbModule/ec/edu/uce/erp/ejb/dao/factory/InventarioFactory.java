/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

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

/**
 * @author
 *
 */
@Local
public interface InventarioFactory {
	
	ProveedorDAO getProveedorDAOImpl();
	
	HistoricoTransaccioneDAO getHistoricoTransaccioneDAOImpl();
	
	BienDAO getBienDAOImpl();
	
	DetalleBienDAO getDetalleBienDAOImpl();
	
	CabeceraBienDAO getCabeceraBienDAOImpl();
	
	LineaBienDAO getLineaBienDAOImpl();
	
	CategoriaBienDAO getCategoriaBienDAOImpl(); 
	
	MarcaBienDAO getMarcaBienDAOImpl();
	
	TransaccionDAO getTransaccionDAOImpl();
	
	VistaBienDAO getVistaBienDAOImpl();
	
}
