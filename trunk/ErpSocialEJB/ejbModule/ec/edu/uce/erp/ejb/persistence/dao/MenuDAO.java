/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Menu;

/**
 * @author 
 *
 */
@Local
public interface MenuDAO extends AbstractFacade<Menu> {
	
	/**
	 * Obtener un <code>Menu</code> por criterios en la bd
	 * @return
	 * @throws SeguridadesException
	 */
	List<Menu> buscarMenuCriterios (Menu menu) throws SeguridadesException;
	
}
