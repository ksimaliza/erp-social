package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.Date;
import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;

public interface MatrimonioDAO extends AbstractFacade<MatrimonioDTO>{

	List<MatrimonioListDTO> obtenerMatrimonio(
			MatrimonioListDTO matrimonioListDTO) throws SeguridadesException;
	
	Date obtenerFechaMinMatrimonio(
			MatrimonioListDTO matrimonioListDTO)throws SeguridadesException;
	
	Date obtenerFechaMaxMatrimonio(
			MatrimonioListDTO matrimonioListDTO)throws SeguridadesException;
	
	List<MatrimonioListDTO> getDistinctReporteBautizoByAnd(
			MatrimonioListDTO objetoDTO) throws SeguridadesException;

}
