package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.MateriaDTO;

public interface MateriaDAO extends AbstractFacade<MateriaDTO>{

	List<MateriaDTO> obtenerMateria(MateriaDTO materia)
			throws SeguridadesException;

}
