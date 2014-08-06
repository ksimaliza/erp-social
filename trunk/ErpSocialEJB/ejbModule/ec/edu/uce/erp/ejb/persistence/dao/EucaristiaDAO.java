package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.EucaristiaListDTO;

public interface EucaristiaDAO extends AbstractFacade<EucaristiaDTO> {

	List<EucaristiaListDTO> obtenerEucaristia(
			EucaristiaListDTO eucaristiaListDTO) throws SeguridadesException;

}
