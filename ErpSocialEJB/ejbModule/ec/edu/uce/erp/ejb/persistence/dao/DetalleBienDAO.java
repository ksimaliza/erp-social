/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.DetalleBien;

/**
 * @author
 *
 */
@Local
public interface DetalleBienDAO extends AbstractFacade<DetalleBien>{
	
	/**
	 * Obtener <code>DetalleBien</code> de la BD por criterios
	 * @param detalleBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<DetalleBien> obtenerDetalleBienCriterios (DetalleBien detalleBien) throws SeguridadesException;

}
