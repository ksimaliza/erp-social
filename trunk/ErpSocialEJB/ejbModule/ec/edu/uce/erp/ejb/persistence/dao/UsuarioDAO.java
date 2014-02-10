package ec.edu.uce.erp.ejb.persistence.dao;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Usuario;
import ec.edu.uce.erp.ejb.persistence.util.dto.CredencialesDTO;

/**
 * 
 * @author 
 *
 */
@Local
public interface UsuarioDAO extends AbstractFacade<Usuario>{
	
	/**
	 * M&eacute;todo para obtener un usuario por sus credenciales
	 * @param credencialesDTO
	 * @return
	 * @throws SeguridadesException
	 */
	Usuario obtenerPorLogin (CredencialesDTO credencialesDTO) throws SeguridadesException;

}
