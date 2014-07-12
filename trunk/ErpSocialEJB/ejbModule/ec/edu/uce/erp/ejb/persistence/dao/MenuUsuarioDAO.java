/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.MenuUsuario;

/**
 * @author 
 *
 */
@Local
public interface MenuUsuarioDAO extends AbstractFacade<MenuUsuario>{
	
	/**
	 * 
	 * @param menuUsuario
	 * @return
	 * @throws SeguridadesException
	 */
	List<MenuUsuario> obtenerMenuUsuarioCriterios (MenuUsuario menuUsuario) throws SeguridadesException;

}
