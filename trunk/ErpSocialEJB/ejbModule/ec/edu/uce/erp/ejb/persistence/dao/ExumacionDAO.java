package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.Date;
import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ExumacionListDTO;

public interface ExumacionDAO extends AbstractFacade<ExumacionDTO>{

	List<ExumacionListDTO> obtenerExhumacion(ExumacionListDTO exumacionListDTO)
			throws SeguridadesException;
	
	Date obtenerFechaMinExhumacion(
			ExumacionListDTO exumacionListDTO)throws SeguridadesException;
	
	Date obtenerFechaMaxExhumacion(
			ExumacionListDTO exumacionListDTO)throws SeguridadesException;

	List<ExumacionListDTO> getDistinctReporteExhumacionByAnd(
			ExumacionListDTO objetoDTO) throws SeguridadesException;

}
