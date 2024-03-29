package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.Date;
import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;

public interface DefuncionDAO extends AbstractFacade<DefuncionDTO> {

	List<DefuncionListDTO> obtenerDefuncion(DefuncionListDTO defuncion)
			throws SeguridadesException;
	
	Date obtenerFechaMinDefuncion(
			DefuncionListDTO defuncion)throws SeguridadesException;
	
	Date obtenerFechaMaxDefuncion(
			DefuncionListDTO defuncion)throws SeguridadesException;

	List<DefuncionListDTO> getDistinctReporteDefuncionByAnd(
			DefuncionListDTO objetoDTO) throws SeguridadesException;

	
}
