package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.AutorizaExhumacionListDTO;

public interface AutorizacionExhumacionDAO extends AbstractFacade<AutorizaExhumacionDTO> {

	List<AutorizaExhumacionListDTO> obtenerAutorizacion(
			AutorizaExhumacionListDTO autorizaExhumacionListDTO)
			throws SeguridadesException;
	

}
