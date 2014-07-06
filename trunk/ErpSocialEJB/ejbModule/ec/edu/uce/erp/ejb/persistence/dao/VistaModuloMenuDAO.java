/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaModuloMenu;

/**
 * @author 
 *
 */
@Local
public interface VistaModuloMenuDAO extends AbstractFacade<VistaModuloMenu> {
	
	/**
	 * Obtener <code>VistaModuloMenu</code> desde la BD por criterios
	 * @param vistaModuloMenu
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaModuloMenu> obtenerVistaModuloMenuCriterios (VistaModuloMenu vistaModuloMenu) throws SeguridadesException; 

}
