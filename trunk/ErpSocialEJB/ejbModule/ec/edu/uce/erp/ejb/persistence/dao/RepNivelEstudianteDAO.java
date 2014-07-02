package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepNivelEstudianteDTO;

public interface RepNivelEstudianteDAO extends AbstractFacade<RepNivelEstudianteDTO>{

	List<RepNivelEstudianteDTO> getByAnd(RepNivelEstudianteDTO objetoDTO)
			throws SeguridadesException;

}
