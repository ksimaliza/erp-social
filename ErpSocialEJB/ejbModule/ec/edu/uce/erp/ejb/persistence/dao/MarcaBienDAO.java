package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.MarcaBien;

/**
 * 
 * @author 
 *
 */
@Local
public interface MarcaBienDAO extends AbstractFacade<MarcaBien>{
	
	/**
	 * Buscar <code>MarcaBien</code> por criterios en la BD
	 * @param marcaBien
	 * @return
	 * @throws SecurityException
	 */
	List<MarcaBien> buscarMarcaBienCriterios (MarcaBien marcaBien) throws SeguridadesException;

}
