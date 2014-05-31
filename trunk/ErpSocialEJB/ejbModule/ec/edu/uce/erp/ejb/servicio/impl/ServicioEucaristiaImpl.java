package ec.edu.uce.erp.ejb.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.erp.common.util.SeguridadesException;
import ec.edu.uce.erp.ejb.dao.factory.EucaristiaFactoryDAO;
import ec.edu.uce.erp.ejb.dao.factory.FactoryDAO;
import ec.edu.uce.erp.ejb.persistence.entity.Persona;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.persistence.vo.DoctorVO;
import ec.edu.uce.erp.ejb.persistence.vo.SacerdoteVO;
import ec.edu.uce.erp.ejb.servicio.ServicioEucaristia;

@Stateless
public class ServicioEucaristiaImpl implements ServicioEucaristia {
	@EJB
	private EucaristiaFactoryDAO eucaristiaFactoryDAO;
	
	@EJB
	private FactoryDAO factoryDAO;
	
	private static final Logger slf4jLogger = LoggerFactory.getLogger(ServicioMatriculaImpl.class);
	
	@Override
	public BautizoDTO createOrUpdateBautizo(BautizoVO bautizoVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateBautizo");
		Persona bautizado;
		Persona padrino;
		Persona madrina;
		SacerdoteDTO sacerdote;
		try {
		if(bautizoVO.getBautizo().getBauCodigo()!=null){
			bautizado= factoryDAO.getPersonaDAOImpl().update(bautizoVO.getBautizado());
			padrino= factoryDAO.getPersonaDAOImpl().update(bautizoVO.getPadrino());
			madrina= factoryDAO.getPersonaDAOImpl().update(bautizoVO.getMadrina());
			sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().update(bautizoVO.getSacerdote());
					
			bautizoVO.getBautizado().setPerPk(bautizado.getPerPk());
			bautizoVO.getPadrino().setPerPk(padrino.getPerPk());
			bautizoVO.getMadrina().setPerPk(madrina.getPerPk());
			bautizoVO.getSacerdote().setSacPersona(sacerdote.getSacPersona());
			return  eucaristiaFactoryDAO.getBautizoDAOImpl().update(bautizoVO.getBautizo());
			
		}
		else{
			bautizado= factoryDAO.getPersonaDAOImpl().create(bautizoVO.getBautizado());
			padrino= factoryDAO.getPersonaDAOImpl().create(bautizoVO.getPadrino());
			madrina= factoryDAO.getPersonaDAOImpl().create(bautizoVO.getMadrina());
			sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().create(bautizoVO.getSacerdote());
			bautizoVO.getBautizado().setPerPk(bautizado.getPerPk());
			bautizoVO.getPadrino().setPerPk(padrino.getPerPk());
			bautizoVO.getMadrina().setPerPk(madrina.getPerPk());
			bautizoVO.getSacerdote().setSacPersona(sacerdote.getSacPersona());
			return  eucaristiaFactoryDAO.getBautizoDAOImpl().create(bautizoVO.getBautizo());
			
		}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateEstudiante {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	

	@Override
	public SacerdoteDTO createOrUpdateSacerdote(SacerdoteVO sacerdoteVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateSacerdote");
		Persona personaNueva;
		try {
		if(sacerdoteVO.getSacerdoteDTO().getSacCodigo()!=null){
			personaNueva=factoryDAO.getPersonaDAOImpl().update(sacerdoteVO.getPersona());
			return eucaristiaFactoryDAO.getSacerdoteDAOImpl().update(sacerdoteVO.getSacerdoteDTO());
					
			
		}
		else{
			personaNueva=factoryDAO.getPersonaDAOImpl().create(sacerdoteVO.getPersona());
			sacerdoteVO.getSacerdoteDTO().setSacPersona(personaNueva.getPerPk());	
			return eucaristiaFactoryDAO.getSacerdoteDAOImpl().create(sacerdoteVO.getSacerdoteDTO());
			
		}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateSacerdote {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	@Override
	public List<SacerdoteListDTO> buscarSacerdote(SacerdoteListDTO sacerdoteListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarSacerdote");
		List<SacerdoteListDTO> listSacerdotes = null;
		try {
			listSacerdotes = eucaristiaFactoryDAO.getSacerdoteDAOImpl().obtenerSacerdote(sacerdoteListDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarSacerdote {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarSacerdote de la base de datos");
		}
		
		return listSacerdotes;
	}
	
	@Override
	public SacerdoteVO obtenerSacerdotePorId(Integer Idpersona, Integer Idsacerdote) throws SeguridadesException {
		slf4jLogger.info("obtenerSacerdotePorId");
		
		SacerdoteVO sacerdote=new SacerdoteVO();
		
		sacerdote.setPersona(factoryDAO.getPersonaDAOImpl().find(Idpersona));
		sacerdote.setSacerdoteDTO(eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(Idsacerdote));
		return sacerdote;
	}
	
	@Override
	public DoctorDTO createOrUpdateDoctor(DoctorVO doctorVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateDoctor");
		Persona personaNueva;
		try {
		if(doctorVO.getDoctorDTO().getDocCodigo()!=null){
			personaNueva=factoryDAO.getPersonaDAOImpl().update(doctorVO.getPersona());
			return  eucaristiaFactoryDAO.getDoctorDAOImpl().update(doctorVO.getDoctorDTO());
					
			
		}
		else{
			personaNueva=factoryDAO.getPersonaDAOImpl().create(doctorVO.getPersona());
			doctorVO.getDoctorDTO().setDocPersona(personaNueva.getPerPk());	
			return eucaristiaFactoryDAO.getDoctorDAOImpl().create(doctorVO.getDoctorDTO());
			
		}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateSacerdote {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	@Override
	public List<DoctorListDTO> buscarDoctor(DoctorListDTO doctorListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarDoctor");
		List<DoctorListDTO> listDoctores = null;
		try {
			listDoctores = eucaristiaFactoryDAO.getDoctorDAOImpl().obtenerDoctor(doctorListDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarDoctor {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarDoctor de la base de datos");
		}
		
		return listDoctores;
	}
	
	@Override
	public DoctorVO obtenerDoctorPorId(Integer Idpersona, Integer Iddoctor) throws SeguridadesException {
		slf4jLogger.info("obtenerDoctorPorId");
		
		DoctorVO doctor=new DoctorVO();
		doctor.setDoctorDTO(eucaristiaFactoryDAO.getDoctorDAOImpl().find(Iddoctor));
		doctor.setPersona(factoryDAO.getPersonaDAOImpl().find(Idpersona));

		return doctor;
	}
	
	
}
