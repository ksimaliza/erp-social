package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.CatalogoAsistenciaDTO;

@Local
public interface CatalogoAsistenciaDAO extends AbstractFacade<CatalogoAsistenciaDTO>{

	List<CatalogoAsistenciaDTO> getByAnd(CatalogoAsistenciaDTO objetoDTO)
			throws SeguridadesException;

}
