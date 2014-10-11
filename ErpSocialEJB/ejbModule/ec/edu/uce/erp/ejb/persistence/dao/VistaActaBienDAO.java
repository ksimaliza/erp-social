package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.view.VistaActaBien;

public interface VistaActaBienDAO extends AbstractFacade<VistaActaBien> {
	
	List<VistaActaBien> obtenerActaBienCriterios (VistaActaBien vistaActaBien) throws SeguridadesException;

}
