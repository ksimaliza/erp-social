package ec.edu.uce.erp.ejb.persistence.dao;




import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.matriculacion.AsinacionListDTO;

public interface AsinacionDAO extends AbstractFacade<AsinacionDTO> {

	List<AsinacionListDTO> findAll(AsinacionListDTO asinacion)
			throws SeguridadesException;

}
