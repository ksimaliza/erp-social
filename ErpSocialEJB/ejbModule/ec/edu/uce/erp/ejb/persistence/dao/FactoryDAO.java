/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import javax.ejb.Local;

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
