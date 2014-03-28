/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.ejb.persistence.entity.inventory.CategoriaBien;

/**
 * @author
 *
 */
@Local
public interface CategoriaBienDAO extends AbstractFacade<CategoriaBien>{
	
	/**
	 * Buscar <code>CategoriaBien</code> en la BD por diferentes criterios
	 * @param categoriaBien
	 * @return
	 * @throws SecurityException
	 */
	List<CategoriaBien> obtenerCategoriaBienCriterios (CategoriaBien categoriaBien) throws SecurityException;

}
