package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ContratoListDTO;

public interface ContratoDAO extends AbstractFacade<ContratoDTO> {

	List<ContratoListDTO> obtenerContrato(ContratoListDTO contratoListDTO)
			throws SeguridadesException;

	List<ContratoListDTO> getByAnd(ContratoListDTO objetoDTO)
			throws SeguridadesException;

}
