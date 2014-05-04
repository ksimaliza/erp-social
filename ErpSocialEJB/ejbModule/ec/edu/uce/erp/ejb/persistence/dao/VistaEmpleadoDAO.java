/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaEmpleado;

/**
 * @author 
 *
 */
public interface VistaEmpleadoDAO extends AbstractFacade<VistaEmpleado> {
	
	/**
	 * Obtener <code>VistaEmpleado</code> de la BD
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaEmpleado> obtenerVistaEmpleadoCriterios (VistaEmpleado vistaEmpleado) throws SeguridadesException;

}
