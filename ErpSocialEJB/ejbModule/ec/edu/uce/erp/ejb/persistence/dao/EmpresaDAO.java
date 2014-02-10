/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Empresa;

/**
 * @author 
 *
 */
public interface EmpresaDAO extends AbstractFacade<Empresa>{
	
	/**
	 * Consultar <code>Empresa</code> en la BD usando diferentes criterios
	 * @param empresa
	 * @return
	 * @throws SeguridadesException
	 */
	List<Empresa> obtenerEmpresaCriterios (Empresa empresa) throws SeguridadesException;

}
