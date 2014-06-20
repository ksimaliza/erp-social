package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;

public interface ConfirmacionDAO extends AbstractFacade<ConfirmacionDTO> {

	List<ConfirmacionListDTO> obtenerConfirmacion(
			ConfirmacionListDTO confirmacion) throws SeguridadesException;

}
