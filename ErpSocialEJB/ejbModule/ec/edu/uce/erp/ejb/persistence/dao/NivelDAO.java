package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.NivelDTO;

public interface NivelDAO extends AbstractFacade<NivelDTO>{

	List<NivelDTO> obtenerNivel(NivelDTO nivel) throws SeguridadesException;

}
