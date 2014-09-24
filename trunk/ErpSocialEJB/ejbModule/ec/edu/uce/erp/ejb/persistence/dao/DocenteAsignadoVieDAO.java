package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.DocenteAsignadoVieDTO;

public interface DocenteAsignadoVieDAO extends AbstractFacade<DocenteAsignadoVieDTO> {

	List<DocenteAsignadoVieDTO> getByAndDistinct(DocenteAsignadoVieDTO objectDTO)
			throws SeguridadesException;

	
}
