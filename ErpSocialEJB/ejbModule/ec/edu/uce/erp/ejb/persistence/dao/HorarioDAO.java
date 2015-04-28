package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.asistencia.HorarioDTO;

public interface HorarioDAO extends AbstractFacade<HorarioDTO>{

	List<HorarioDTO> getByAnd(HorarioDTO objectDTO) throws SeguridadesException;

}
