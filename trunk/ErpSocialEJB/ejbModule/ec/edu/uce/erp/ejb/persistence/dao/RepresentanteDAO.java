package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.RepresentanteListDTO;

public interface RepresentanteDAO extends AbstractFacade<RepresentanteDTO> {

	List<RepresentanteListDTO> obtenerRepresentante(
			RepresentanteListDTO representante) throws SeguridadesException;

	List<RepresentanteListDTO> getByAnd(RepresentanteListDTO representante)
			throws SeguridadesException;

}
