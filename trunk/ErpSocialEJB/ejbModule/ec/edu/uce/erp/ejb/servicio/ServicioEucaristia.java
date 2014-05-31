package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.persistence.vo.DoctorVO;
import ec.edu.uce.erp.ejb.persistence.vo.SacerdoteVO;

@Local
public interface ServicioEucaristia {

	BautizoDTO createOrUpdateBautizo(BautizoVO bautizoVO)
			throws SeguridadesException;

	SacerdoteDTO createOrUpdateSacerdote(SacerdoteVO sacerdoteVO)
			throws SeguridadesException;

	List<SacerdoteListDTO> buscarSacerdote(SacerdoteListDTO sacerdoteListDTO)
			throws SeguridadesException;

	SacerdoteVO obtenerSacerdotePorId(Integer Idpersona, Integer Idsacerdote)
			throws SeguridadesException;

	DoctorDTO createOrUpdateDoctor(DoctorVO doctorVO)
			throws SeguridadesException;

	List<DoctorListDTO> buscarDoctor(DoctorListDTO doctorListDTO)
			throws SeguridadesException;

	DoctorVO obtenerDoctorPorId(Integer Idpersona, Integer Iddoctor)
			throws SeguridadesException;

}
