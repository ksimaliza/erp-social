package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;

public interface TipoNichoDAO extends AbstractFacade<TipoNichoDTO> {

	List<TipoNichoDTO> obtenerTipoNicho(TipoNichoDTO tipoNichoDTO)
			throws SeguridadesException;

}
