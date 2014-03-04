/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.DetalleCatalogo;

/**
 * @author 
 *
 */
public interface DetalleCatalogoDAO extends AbstractFacade<DetalleCatalogo>{
	
	/**
	 * Obtener <code>DetalleCatalogo</code> por criterios
	 * @param detalleCatalogo
	 * @return
	 * @throws SeguridadesException
	 */
	List<DetalleCatalogo> obtenerDetalleCatalogoCriterios (DetalleCatalogo detalleCatalogo) throws SeguridadesException;

}
