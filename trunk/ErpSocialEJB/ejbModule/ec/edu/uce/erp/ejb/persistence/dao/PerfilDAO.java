/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Perfil;

/**
 * @author 
 *
 */
@Local
public interface PerfilDAO extends AbstractFacade<Perfil>{
	
	/**
	 * Consultar <code>Perfil</code> en la BD usando diferentes criterios
	 * @param perfil
	 * @return
	 * @throws SecurityException
	 */
	List<Perfil> obtenerPerfilCriterios (Perfil perfil) throws SeguridadesException;
	
	
	/**
	 * Consultar <code>Perfil</code> por el id de una empresa
	 * @param perfil
	 * @return
	 * @throws SeguridadesException
	 */
	List<Perfil> obtenerPerfilEmpresa (Perfil perfil) throws SeguridadesException; 

}
