package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.Date;
import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;

public interface PrimeraComunionDAO extends AbstractFacade<PrimeraComunionDTO>{

	List<ComunionListDTO> obtenerComunion(ComunionListDTO comunion)
			throws SeguridadesException;
	

	Date obtenerFechaMinComunion(ComunionListDTO comunion)
			throws SeguridadesException;
	
	Date obtenerFechaMaxComunion(ComunionListDTO comunion)
			throws SeguridadesException;

	List<ComunionListDTO> getDistinctReporteComunionByAnd(
			ComunionListDTO objetoDTO) throws SeguridadesException;

}
