package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;

public interface BautizoDAO extends AbstractFacade<BautizoDTO> {

	List<BautizoListDTO> obtenerBautizo(BautizoListDTO bautizo)
			throws SeguridadesException;

}
