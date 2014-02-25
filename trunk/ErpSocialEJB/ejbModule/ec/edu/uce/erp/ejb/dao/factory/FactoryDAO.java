/**
 * 
 */
package ec.edu.uce.erp.ejb.dao.factory;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.dao.EmpresaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistorialClaveDAO;
import ec.edu.uce.erp.ejb.persistence.dao.HistoricoTransaccioneDAO;
import ec.edu.uce.erp.ejb.persistence.dao.MenuDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ModuloDAO;
import ec.edu.uce.erp.ejb.persistence.dao.ParametroDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PerfilDAO;
import ec.edu.uce.erp.ejb.persistence.dao.PersonaDAO;
import ec.edu.uce.erp.ejb.persistence.dao.UsuarioDAO;

/**
 * @author 
 *
 */
@Local
public interface FactoryDAO {
	
	UsuarioDAO getUsuarioDAOImpl();
	EmpresaDAO getEmpresaDAOImpl();
	ModuloDAO getModuloDAOImpl();
	PerfilDAO getPerfilDAOImpl();
	MenuDAO getMenuDAOImpl();
	
	HistoricoTransaccioneDAO getHistoricoTransaccioneDAOImpl();
	HistorialClaveDAO getHistorialClaveDAOImpl();
	
	ParametroDAO getParametroDAOImpl();
	
	PersonaDAO getPersonaDAOImpl();
	
}
