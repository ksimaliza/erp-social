/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistorialClave;

/**
 * @author 
 *
 */
@Local
public interface HistorialClaveDAO extends AbstractFacade<HistorialClave>{
	
	/**
	 * Buscar en el historico de claves por criterios
	 * @param historialClave
	 * @return
	 * @throws SeguridadesException
	 */
	List<HistorialClave> obtenerHistorialClaveCriterios (HistorialClave historialClave) throws SeguridadesException;

}
