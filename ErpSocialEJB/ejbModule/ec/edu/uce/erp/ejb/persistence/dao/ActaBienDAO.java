package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.inventory.ActaBien;

@Local
public interface ActaBienDAO extends AbstractFacade<ActaBien>{
	
	List<ActaBien> obtenerActaBienCriterios (ActaBien actaBien) throws SeguridadesException;

}
