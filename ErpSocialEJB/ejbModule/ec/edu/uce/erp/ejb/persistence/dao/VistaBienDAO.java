/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaBien;

/**
 * @author 
 *
 */
@Local
public interface VistaBienDAO extends AbstractFacade<VistaBien>{
	
	/**
	 * Obtener <code>Bien</code> en la BD a trav&eacute;s de una vista
	 * @return
	 * @throws SeguridadesException
	 */
	List<VistaBien> obtenerVistaBienCriterios (VistaBien vistaBien) throws SeguridadesException;

}
