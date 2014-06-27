package ec.edu.uce.erp.ejb.servicio;

import java.util.List;

import javax.ejb.Local;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.persistence.vo.ComunionVO;
import ec.edu.uce.erp.ejb.persistence.vo.ConfirmacionVO;
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

	List<BautizoListDTO> buscarPartidaBautizo(BautizoListDTO bautizoListDTO)
			throws SeguridadesException;

	BautizoVO obtenerBautizoPorId(Integer idBautizado, Integer idBautizo)
			throws SeguridadesException;

	ConfirmacionDTO createOrUpdateConfirmacion(ConfirmacionVO confirmacionVO)
			throws SeguridadesException;

	List<ConfirmacionListDTO> buscarPartidaConfirmacion(
			ConfirmacionListDTO confirmacionListDTO)
			throws SeguridadesException;

	ConfirmacionVO obtenerConfirmacionPorId(Integer idConfirmado,
			Integer idConfirmacion) throws SeguridadesException;

	List<CatalogoEucaristiaDTO> buscarCatalogo(
			CatalogoEucaristiaDTO catalogoEucaristiaDTO)
			throws SeguridadesException;

	PrimeraComunionDTO createOrUpdateComunion(ComunionVO comunionVO)
			throws SeguridadesException;

	List<ComunionListDTO> buscarPartidaComunion(ComunionListDTO comunionListDTO)
			throws SeguridadesException;

	ComunionVO obtenerComunionPorId(Integer idAsignado, Integer idComunion)
			throws SeguridadesException;

}
