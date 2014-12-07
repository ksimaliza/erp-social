/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.ParametroEmpresa;

/**
 * @author
 *
 */
public interface ParametroEmpresaDAO extends AbstractFacade<ParametroEmpresa> {
	
	/**
	 * 
	 * @param parametroEmpresa
	 * @return
	 * @throws SeguridadesException
	 */
	List<ParametroEmpresa> obtenerParametroEmpresaCriterios (ParametroEmpresa parametroEmpresa) throws SeguridadesException;
	
	/**
	 * 
	 * @param parametroEmpresa
	 * @return
	 * @throws SeguridadesException
	 */
	ParametroEmpresa obtenerParametroEmpresaUnique(ParametroEmpresa parametroEmpresa) throws SeguridadesException;

}
