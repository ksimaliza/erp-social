/**
 * 
 */
package ec.edu.uce.erp.ejb.persistence.dao;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.security.HistoricoTransaccione;
import ec.edu.uce.erp.ejb.persistence.util.dto.AuditoriaDTO;

/**
 * @author 
 *
 */
@Local
public interface HistoricoTransaccioneDAO extends AbstractFacade<HistoricoTransaccione>{
	
	/**
	 * Registrar el objeto de auditoria de la aplicacion
	 * @param auditoriaDTO
	 * @throws SeguridadesException
	 */
	void registrarHistoricoTransaccion (AuditoriaDTO auditoriaDTO) throws SeguridadesException;

}
