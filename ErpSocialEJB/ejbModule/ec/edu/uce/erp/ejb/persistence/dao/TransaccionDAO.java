/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Transaccion;

/**
 * @author 
 *
 */
@Local
public interface TransaccionDAO extends AbstractFacade<Transaccion>{
	
	/**
	 * Obtener <code>Transaccion</code> de la BD
	 * @return
	 * @throws SeguridadesException
	 */
	List<Transaccion> obtenerTransaccionCriterios(Transaccion transaccion) throws SeguridadesException;

}
