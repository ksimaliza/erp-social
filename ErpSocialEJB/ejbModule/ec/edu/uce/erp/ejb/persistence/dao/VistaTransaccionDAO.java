/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaTransaccion;

/**
 * @author
 *
 */
@Local
public interface VistaTransaccionDAO extends AbstractFacade<VistaTransaccion> {

	/**
	 * Obtener <code>VistaTransaccion</code> de la bd
	 * @param vistaTransaccion
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaTransaccion> obtenerVistaTransaccionCriterios(VistaTransaccion vistaTransaccion) throws SeguridadesException;

}
