package ec.edu.uce.erp.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;

@Local
public interface DoctorDAO extends AbstractFacade<DoctorDTO>{

	List<DoctorListDTO> obtenerDoctor(DoctorListDTO Doctor)
			throws SeguridadesException;

}
