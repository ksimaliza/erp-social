/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaHistoricoTransaccion;


/**
 * @author 
 *
 */
@Local
public interface VistaHistoricoTransaccionDAO extends AbstractFacade<VistaHistoricoTransaccion>{
	
	/**
	 * Obtener <code>VistaHistoricoTransaccion</code> por criterios
	 * @param vistaHistoricoTransaccion
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaHistoricoTransaccion> obtenerVistaHistoricoTransaccionCriterios(
			VistaHistoricoTransaccion vistaHistoricoTransaccion) throws SeguridadesException; 

}
