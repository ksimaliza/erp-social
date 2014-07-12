/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaUsuario;

/**
 * @author 
 *
 */
@Local
public interface VistaUsuarioDAO extends AbstractFacade<VistaUsuario> {
	
	/**
	 * Obtener <code>VistaUsuario</code> por criterios
	 * @param VistaUsuario
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaUsuario> obtenerVistaUsuarioCriterios(VistaUsuario vistaUsuario) throws SeguridadesException;

}
