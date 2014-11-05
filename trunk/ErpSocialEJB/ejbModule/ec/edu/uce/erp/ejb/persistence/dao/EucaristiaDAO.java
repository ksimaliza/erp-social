package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;
import ec.edu.uce.erp.ejb.persistence.util.dto.FiltroFechaDTO;

public interface EucaristiaDAO extends AbstractFacade<EucaristiaDTO> {


	List<EucaristiaListDTO> obtenerEucaristia(
			EucaristiaListDTO eucaristiaListDTO, FiltroFechaDTO filtro)
			throws SeguridadesException;


}
