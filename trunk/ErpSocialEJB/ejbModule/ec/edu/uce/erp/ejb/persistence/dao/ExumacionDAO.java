package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;

public interface ExumacionDAO extends AbstractFacade<ExumacionDTO>{

	List<ExumacionListDTO> obtenerExhumacion(ExumacionListDTO exumacionListDTO)
			throws SeguridadesException;

}