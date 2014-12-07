/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.Bien;

/**
 * @author 
 *
 */
@Local
public interface BienDAO extends AbstractFacade<Bien> {
	
	/**
	 * Obtener <code>Bien</code> de la BD por criterios
	 * @param bien
	 * @return
	 * @throws SeguridadesException
	 */
	List<Bien> buscarBienCriterios (Bien bien) throws SeguridadesException;
	
	/**
	 * Genera el siguiente valor de la secuencia usada para generar el codigo del bien
	 * @return
	 * @throws SeguridadesException
	 */
	BigInteger generarNextValSecuenciaCodigo (String nombreSecuencia) throws SeguridadesException;

}
