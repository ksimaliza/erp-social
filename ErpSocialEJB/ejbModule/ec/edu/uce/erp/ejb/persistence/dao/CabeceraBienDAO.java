/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.CabeceraBien;

/**
 * @author 
 *
 */
@Local
public interface CabeceraBienDAO extends AbstractFacade<CabeceraBien>{
	
	/**
	 * Obtener <code>CabeceraBien</code> de la BD por criterios
	 * @param cabeceraBien
	 * @return
	 * @throws SeguridadesException
	 */
	List<CabeceraBien> obtenerCabeceraBienCriterios (CabeceraBien cabeceraBien) throws SeguridadesException;

}
