package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.Parametro;

/**
 * 
 * @author 
 *
 */
@Local
public interface ParametroDAO extends AbstractFacade<Parametro>{
	
	/**
	 * Buscar <code>Parametro</code> en la bd por criterios
	 * @param parametro
	 * @return Lista <code>List<Parametro></code> con los <code>Parametro</code> encontrados
	 * @throws SeguridadesException
	 */
	List<Parametro> buscarParametroCriterios(Parametro parametro) throws SeguridadesException;

}
