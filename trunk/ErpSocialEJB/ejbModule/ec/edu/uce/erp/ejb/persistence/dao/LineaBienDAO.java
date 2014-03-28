/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.LineaBien;

/**
 * @author 
 *
 */
@Local
public interface LineaBienDAO extends AbstractFacade<LineaBien>{
	
	/**
	 * Obtener <code>LineaBien</code> por criterios de la BD
	 * @param lineaBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<LineaBien> obtenerLineaBienCriterios (LineaBien lineaBien) throws SeguridadesException; 

}
