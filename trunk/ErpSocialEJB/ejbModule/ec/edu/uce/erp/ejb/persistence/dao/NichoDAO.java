package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NichoListDTO;

public interface NichoDAO extends AbstractFacade<NichoDTO>{

	List<NichoListDTO> obtenerNicho(NichoListDTO nichoListDTO)
			throws SeguridadesException;

}
