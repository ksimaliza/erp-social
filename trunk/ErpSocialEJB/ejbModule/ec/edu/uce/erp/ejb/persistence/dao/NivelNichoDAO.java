package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;

public interface NivelNichoDAO extends AbstractFacade<NivelNichoDTO>{

	List<NivelNichoDTO> obtenerNivelNicho(NivelNichoDTO nivelNichoDTO)
			throws SeguridadesException;

}
