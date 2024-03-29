package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.Date;
import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SepulturaListDTO;

public interface SepulturaDAO extends AbstractFacade<SepulturaDTO>{

	List<SepulturaListDTO> getByAnd(SepulturaListDTO objetoDTO)
			throws SeguridadesException;
	
	Date obtenerFechaMinSepultura(
			SepulturaListDTO objetoDTO)throws SeguridadesException;
	
	Date obtenerFechaMaxSepultura(
			SepulturaListDTO objetoDTO)throws SeguridadesException;

	List<SepulturaListDTO> getDistinctReporteSepulturaByAnd(
			SepulturaListDTO objetoDTO) throws SeguridadesException;
	

}
