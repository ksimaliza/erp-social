/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;


/**
 * @author 
 *
 */
public interface VistaHistoricoTransaccionDAO extends AbstractFacade<VistaHistoricoTransaccion>{
	
	/**
	 * Obtener <code>VistaHistoricoTransaccion</code> por criterios
	 * @param vistaHistoricoTransaccion
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaHistoricoTransaccionDAO> obtenerVistaHistoricoTransaccionCriterios(
			VistaHistoricoTransaccion vistaHistoricoTransaccion) throws SeguridadesException; 

}
