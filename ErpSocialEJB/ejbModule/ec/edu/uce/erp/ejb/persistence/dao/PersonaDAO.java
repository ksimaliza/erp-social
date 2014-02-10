/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;

/**
 * @author 
 *
 */
@Local
public interface PersonaDAO extends AbstractFacade<Persona>{
	
	/**
	 * Buscar una persona por diferentes criterios
	 * @param persona
	 * @return
	 * @throws SeguridadesException
	 */
	List<Persona> buscarPersonaCriterios (Persona persona) throws SeguridadesException;

}
