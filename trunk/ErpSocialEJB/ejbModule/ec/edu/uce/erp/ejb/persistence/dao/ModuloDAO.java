/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Modulo;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;

/**
 * @author 
 *
 */
@Local
public interface ModuloDAO extends AbstractFacade<Modulo>{
	
	/**
	 * Obtener los m&oacute;dulos a los que el usuario tiene acceso
	 * @param usuarioDTO
	 * @return
	 * @throws SeguridadesException
	 */
	List<Modulo> obtenerModuloUsuario(Usuario usuario) throws SeguridadesException;
	
	/**
	 * Obtener <code>Modulo</code> por criterios
	 * @param modulo
	 * @return
	 * @throws SeguridadesException
	 */
	List<Modulo> obtenerModuloCriterios(Modulo modulo) throws SeguridadesException;

}
