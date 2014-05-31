package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;

public interface SacerdoteDAO extends AbstractFacade<SacerdoteDTO>{

	List<SacerdoteListDTO> obtenerSacerdote(SacerdoteListDTO sacerdote)
			throws SeguridadesException;

}
