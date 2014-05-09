package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.TipoDTO;

public interface TipoDAO extends  AbstractFacade<TipoDTO> {

	List<TipoDTO> obtenerTipo(TipoDTO tipo) throws SeguridadesException;

}
