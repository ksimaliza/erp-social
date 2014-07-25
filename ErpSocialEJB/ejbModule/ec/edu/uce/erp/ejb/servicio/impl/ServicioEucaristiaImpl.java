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
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.BautizoListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.CatalogoEucaristiaDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ComunionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.ConfirmacionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DefuncionListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.DoctorListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.MatrimonioListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.NivelNichoDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.PrimeraComunionDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.SacerdoteListDTO;
import ec.edu.uce.erp.ejb.persistence.entity.eucaristia.TipoNichoDTO;
import ec.edu.uce.erp.ejb.persistence.vo.BautizoVO;
import ec.edu.uce.erp.ejb.persistence.vo.ComunionVO;
import ec.edu.uce.erp.ejb.persistence.vo.ConfirmacionVO;
import ec.edu.uce.erp.ejb.persistence.vo.DefuncionVO;
import ec.edu.uce.erp.ejb.persistence.vo.DoctorVO;
import ec.edu.uce.erp.ejb.persistence.vo.MatrimonioVO;
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
		Persona bautizadoPersona;
		Persona padrino;
		Persona madrina;
		SacerdoteDTO sacerdote;
		
		List<Persona> listPersona;
			
		try {
			bautizadoPersona = bautizoVO.getBautizado();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(bautizadoPersona);
			if(listPersona.size()<=0)
				bautizadoPersona=factoryDAO.getPersonaDAOImpl().create(bautizadoPersona);
			else
				bautizadoPersona=listPersona.get(0);
			
			padrino=bautizoVO.getPadrino();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(padrino);
			if(listPersona.size()<=0)
				padrino=factoryDAO.getPersonaDAOImpl().create(padrino);
			else
				padrino=listPersona.get(0);
			
			madrina=bautizoVO.getMadrina();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(madrina);
			if(listPersona.size()<=0)
				madrina=factoryDAO.getPersonaDAOImpl().create(madrina);
			else
				madrina=listPersona.get(0);

			bautizoVO.getBautizo().setBauBautizado(bautizadoPersona.getPerPk());
			bautizoVO.getBautizo().setBauPadrino(padrino.getPerPk());
			bautizoVO.getBautizo().setBauMadrina(madrina.getPerPk());
			
			if(bautizoVO.getBautizo().getBauCodigo()!=null){
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(bautizoVO.getSacerdote().getSacCodigo());							
				bautizoVO.getBautizo().setEucSacerdote(sacerdote);
				
				return  eucaristiaFactoryDAO.getBautizoDAOImpl().update(bautizoVO.getBautizo());
			
			}
			else{
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(bautizoVO.getSacerdote().getSacCodigo());	
				bautizoVO.getBautizo().setEucSacerdote(sacerdote);
				
				return  eucaristiaFactoryDAO.getBautizoDAOImpl().create(bautizoVO.getBautizo());
				
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateBautizo {}", e.toString());
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
	
	@Override
	public List<BautizoListDTO> buscarPartidaBautizo(BautizoListDTO bautizoListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarPartidaBautizo");
		List<BautizoListDTO> listBautizo = null;
		try {
			listBautizo=eucaristiaFactoryDAO.getBautizoDAOImpl().obtenerBautizo(bautizoListDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPartidaBautizo {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener los Bautizados de la base de datos");
		}
		
		return listBautizo;
	}
	
	@Override
	public BautizoVO obtenerBautizoPorId(BautizoListDTO bautizoListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerComunionPorId");
		
		BautizoVO bautizo=new BautizoVO();
		
		bautizo.setBautizado(factoryDAO.getPersonaDAOImpl().find(bautizoListDTO.getPerPk()));
		bautizo.setBautizo(eucaristiaFactoryDAO.getBautizoDAOImpl().find(bautizoListDTO.getBauCodigo()));
		bautizo.setMadrina(factoryDAO.getPersonaDAOImpl().find(bautizoListDTO.getBauMadrina()));
		bautizo.setPadrino(factoryDAO.getPersonaDAOImpl().find(bautizoListDTO.getBauPadrino()));
	
		
		return bautizo;
	}
	
	
	
	
	@Override
	public ConfirmacionDTO createOrUpdateConfirmacion(ConfirmacionVO confirmacionVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateConfirmacion");
		Persona confirmadoPersona;
		Persona mad_pad;
		SacerdoteDTO sacerdote;
				 
		List<Persona> listPersona;
			
		try {
			confirmadoPersona = confirmacionVO.getConfirmado();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(confirmadoPersona);
			if(listPersona.size()<=0)
				confirmadoPersona=factoryDAO.getPersonaDAOImpl().create(confirmadoPersona);
			else
				confirmadoPersona=listPersona.get(0);
			
			mad_pad=confirmacionVO.getMad_pad();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(mad_pad);
			if(listPersona.size()<=0)
				mad_pad=factoryDAO.getPersonaDAOImpl().create(mad_pad);
			else
				mad_pad=listPersona.get(0);
			
			confirmacionVO.getConfirmacion().setConConfirmado(confirmadoPersona.getPerPk());
			confirmacionVO.getConfirmacion().setConPadrino(mad_pad.getPerPk());
						
			if(confirmacionVO.getConfirmacion().getConCodigo()!=null){
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(confirmacionVO.getSacerdote().getSacCodigo());	
				
				confirmacionVO.getConfirmacion().setEucSacerdote(sacerdote);
						
				return  eucaristiaFactoryDAO.getConfirmacionDAOImpl().update(confirmacionVO.getConfirmacion());
			
			}
			else{
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(confirmacionVO.getSacerdote().getSacCodigo());	
				confirmacionVO.getConfirmacion().setEucSacerdote(sacerdote);				
				return  eucaristiaFactoryDAO.getConfirmacionDAOImpl().create(confirmacionVO.getConfirmacion());
				
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateConfirmacion {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	@Override
	public List<ConfirmacionListDTO> buscarPartidaConfirmacion(ConfirmacionListDTO confirmacionListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarPartidaConfirmacion");
		List<ConfirmacionListDTO> listConfirmacion = null;
		try {
			listConfirmacion=eucaristiaFactoryDAO.getConfirmacionDAOImpl().obtenerConfirmacion(confirmacionListDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPartidaConfirmacion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarPartidaConfirmacion de la base de datos");
		}
		
		return listConfirmacion;
	}
	
	@Override
	public ConfirmacionVO obtenerConfirmacionPorId(ConfirmacionListDTO confirmacionListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerConfirmacionPorId");
		
		ConfirmacionVO confirmacion =new ConfirmacionVO();
		confirmacion.setConfirmado(factoryDAO.getPersonaDAOImpl().find(confirmacionListDTO.getConConfirmado()));
		confirmacion.setConfirmacion(eucaristiaFactoryDAO.getConfirmacionDAOImpl().find(confirmacionListDTO.getConCodigo()));	
		confirmacion.setMad_pad(factoryDAO.getPersonaDAOImpl().find(confirmacionListDTO.getConPadrino()));
		
		
		return confirmacion;
	}
	
	@Override
	public List<CatalogoEucaristiaDTO> buscarCatalogo(CatalogoEucaristiaDTO catalogoEucaristiaDTO) throws SeguridadesException {
		slf4jLogger.info("buscarCatalogo");
		List<CatalogoEucaristiaDTO> listCatalogo = null;
		try {
			listCatalogo = eucaristiaFactoryDAO.getCatalogoDAOImpl().getAll(catalogoEucaristiaDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarCatalogo {}", e.getMessage());
			throw new SeguridadesException("No se pudo buscarCatalogo de la base de datos");
		}
		
		return listCatalogo;
	}
	
	
	@Override
	public PrimeraComunionDTO createOrUpdateComunion(ComunionVO comunionVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateComunion");
		Persona asignadoPersona;
		Persona mad_pad;
		SacerdoteDTO sacerdote;
		
		
		List<Persona> listPersona;
			
		try {
			asignadoPersona = comunionVO.getAsignado();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(asignadoPersona);
			if(listPersona.size()<=0)
				asignadoPersona=factoryDAO.getPersonaDAOImpl().create(asignadoPersona);
			else
				asignadoPersona=listPersona.get(0);
			
			mad_pad=comunionVO.getMad_pad();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(mad_pad);
			if(listPersona.size()<=0)
				mad_pad=factoryDAO.getPersonaDAOImpl().create(mad_pad);
			else
				mad_pad=listPersona.get(0);
			
			comunionVO.getComunion().setPcoAsignado(asignadoPersona.getPerPk());
			comunionVO.getComunion().setPcoPadrino(mad_pad.getPerPk());
									
			if(comunionVO.getComunion().getPcoCodigo()!=null){
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(comunionVO.getSacerdote().getSacCodigo());	
				comunionVO.getComunion().setEucSacerdote(sacerdote);
													
				return  eucaristiaFactoryDAO.getPrimeraComunionDAOImpl().update(comunionVO.getComunion());
				
			}
			else{
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(comunionVO.getSacerdote().getSacCodigo());	
				comunionVO.getComunion().setEucSacerdote(sacerdote);
													
				return  eucaristiaFactoryDAO.getPrimeraComunionDAOImpl().create(comunionVO.getComunion());
				
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateComunion {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	@Override
	public List<ComunionListDTO> buscarPartidaComunion(ComunionListDTO comunionListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarPartidaComunion");
		List<ComunionListDTO> listComunion = null;
		try {
			listComunion=eucaristiaFactoryDAO.getPrimeraComunionDAOImpl().obtenerComunion(comunionListDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPartidaComunion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarPartidaComunion de la base de datos");
		}
		
		return listComunion;
	}
	
	@Override
	public ComunionVO obtenerComunionPorId(ComunionListDTO comunionListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerComunionPorId");
		
		ComunionVO comunion =new ComunionVO();
		comunion.setAsignado(factoryDAO.getPersonaDAOImpl().find(comunionListDTO.getPcoAsignado()));
		comunion.setComunion(eucaristiaFactoryDAO.getPrimeraComunionDAOImpl().find(comunionListDTO.getPcoCodigo()));	
		comunion.setMad_pad(factoryDAO.getPersonaDAOImpl().find(comunionListDTO.getPcoPadrino()));
		
		return comunion;
	}
	
	@Override
	public MatrimonioDTO createOrUpdateMatrimonio(MatrimonioVO matrimonioVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateMatrimonio");
		Persona novioPersona;
		Persona noviaPersona;
		Persona mad_novia;
		Persona mad_novio;
		Persona pad_novia;
		Persona pad_novio;
		SacerdoteDTO sacerdote;
				 
		List<Persona> listPersona;
			
		try {
			novioPersona = matrimonioVO.getNovio();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(novioPersona);
			if(listPersona.size()<=0)
				novioPersona=factoryDAO.getPersonaDAOImpl().create(novioPersona);
			else
				novioPersona=listPersona.get(0);
			
			noviaPersona = matrimonioVO.getNovia();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(noviaPersona);
			if(listPersona.size()<=0)
				noviaPersona=factoryDAO.getPersonaDAOImpl().create(noviaPersona);
			else
				noviaPersona=listPersona.get(0);
			
			mad_novio=matrimonioVO.getMad_novio();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(mad_novio);
			if(listPersona.size()<=0)
				mad_novio=factoryDAO.getPersonaDAOImpl().create(mad_novio);
			else
				mad_novio=listPersona.get(0);
			
			mad_novia=matrimonioVO.getMad_novia();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(mad_novia);
			if(listPersona.size()<=0)
				mad_novia=factoryDAO.getPersonaDAOImpl().create(mad_novia);
			else
				mad_novia=listPersona.get(0);
			
			pad_novio=matrimonioVO.getPad_novio();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(pad_novio);
			if(listPersona.size()<=0)
				pad_novio=factoryDAO.getPersonaDAOImpl().create(pad_novio);
			else
				pad_novio=listPersona.get(0);
			
			pad_novia=matrimonioVO.getPad_novia();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(pad_novia);
			if(listPersona.size()<=0)
				pad_novia=factoryDAO.getPersonaDAOImpl().create(pad_novia);
			else
				pad_novia=listPersona.get(0);
			
			
			matrimonioVO.getMatrimonio().setMatNovio(novioPersona.getPerPk());
			matrimonioVO.getMatrimonio().setMatNovia(noviaPersona.getPerPk());
			matrimonioVO.getMatrimonio().setMatMadrinaNovia(mad_novia.getPerPk());
			matrimonioVO.getMatrimonio().setMatMadrinaNovio(mad_novio.getPerPk());
			matrimonioVO.getMatrimonio().setMatPadrinoNovia(pad_novia.getPerPk());
			matrimonioVO.getMatrimonio().setMatPadrinoNovio(pad_novio.getPerPk());
			
						
			if(matrimonioVO.getMatrimonio().getMatCodigo()!=null){
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(matrimonioVO.getSacerdote().getSacCodigo());	
				matrimonioVO.getMatrimonio().setEucSacerdote(sacerdote);
					
				return  eucaristiaFactoryDAO.getMatrimonioDAOImpl().update(matrimonioVO.getMatrimonio());
			
			}
			else{
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(matrimonioVO.getSacerdote().getSacCodigo());	
				matrimonioVO.getMatrimonio().setEucSacerdote(sacerdote);
					
				return  eucaristiaFactoryDAO.getMatrimonioDAOImpl().create(matrimonioVO.getMatrimonio());
				
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateMatrimonio {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	@Override
	public List<MatrimonioListDTO> buscarPartidaMatrimonio(MatrimonioListDTO matrimonioListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarPartidaMatrimonio");
		List<MatrimonioListDTO> listMatrimonio = null;
		try {
			listMatrimonio=eucaristiaFactoryDAO.getMatrimonioDAOImpl().obtenerMatrimonio(matrimonioListDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarPartidaMatrimonio {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarPartidaMatrimonio de la base de datos");
		}
		
		return listMatrimonio;
	}
	
	@Override
	public MatrimonioVO obtenerMatrimonioPorId(MatrimonioListDTO matrimonioListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerConfirmacionPorId");
		
		MatrimonioVO matrimonio =new MatrimonioVO();
		matrimonio.setNovio(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatNovio()));
		matrimonio.setNovia(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatNovia()));
		matrimonio.setMatrimonio(eucaristiaFactoryDAO.getMatrimonioDAOImpl().find(matrimonioListDTO.getMatCodigo()));
		matrimonio.setMad_novia(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatMadrinaNovia()));
		matrimonio.setMad_novio(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatMadrinaNovio()));
		matrimonio.setPad_novia(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatPadrinoNovia()));
		matrimonio.setPad_novio(factoryDAO.getPersonaDAOImpl().find(matrimonioListDTO.getMatPadrinoNovio()));
				
		return matrimonio;
	}
	
	
	@Override
	public DefuncionDTO createOrUpdateDefuncion(DefuncionVO defuncionVO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateDefuncion");
		Persona difunto;
		Persona conyuge;
		SacerdoteDTO sacerdote;
		DoctorDTO doctor;
		
		List<Persona> listPersona;
			
		try {
			difunto = defuncionVO.getDifunto();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(difunto);
			if(listPersona.size()<=0)
				difunto=factoryDAO.getPersonaDAOImpl().create(difunto);
			else
				difunto=listPersona.get(0);
			
			conyuge=defuncionVO.getConyuge();
			listPersona=factoryDAO.getPersonaDAOImpl().buscarPersonaCriterios(conyuge);
			if(listPersona.size()<=0)
				conyuge=factoryDAO.getPersonaDAOImpl().create(conyuge);
			else
				conyuge=listPersona.get(0);
			
			defuncionVO.getDefuncion().setDefPersona(difunto.getPerPk());
			defuncionVO.getDefuncion().setDefConyugue(conyuge.getPerPk());
			
									
			if(defuncionVO.getDefuncion().getDefCodigo()!=null){
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(defuncionVO.getSacerdote().getSacCodigo());	
				doctor=eucaristiaFactoryDAO.getDoctorDAOImpl().find(defuncionVO.getDoctor().getDocCodigo());
				defuncionVO.getDefuncion().setEucSacerdote(sacerdote);
				defuncionVO.getDefuncion().setDefDoctorCertifica(doctor.getDocCodigo());
																
				return  eucaristiaFactoryDAO.getDefuncionDAOImpl().update(defuncionVO.getDefuncion());
			}
			else{
				sacerdote= eucaristiaFactoryDAO.getSacerdoteDAOImpl().find(defuncionVO.getSacerdote().getSacCodigo());	
				doctor=eucaristiaFactoryDAO.getDoctorDAOImpl().find(defuncionVO.getDoctor().getDocCodigo());
				defuncionVO.getDefuncion().setEucSacerdote(sacerdote);
				defuncionVO.getDefuncion().setDefDoctorCertifica(doctor.getDocCodigo());
																
				return  eucaristiaFactoryDAO.getDefuncionDAOImpl().create(defuncionVO.getDefuncion());
				
			}
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateDefuncion {}", e.toString());
			throw new SeguridadesException(e);
		}
		
		
	}
	
	
	
	@Override
	public List<DefuncionListDTO> buscarDefuncion(DefuncionListDTO defuncionListDTO) throws SeguridadesException {
		slf4jLogger.info("buscarDefuncion");
		List<DefuncionListDTO> listDefuncion = null;
		try {
			listDefuncion=eucaristiaFactoryDAO.getDefuncionDAOImpl().obtenerDefuncion(defuncionListDTO);
			
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarDefuncion {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarDefuncion de la base de datos");
		}
		
		return listDefuncion;
	}
	
	
	@Override
	public DefuncionVO obtenerDefuncionPorId(DefuncionListDTO defuncionListDTO) throws SeguridadesException {
		slf4jLogger.info("obtenerDefuncionPorId");
		DefuncionVO defuncionVO=new DefuncionVO();
		defuncionVO.setDifunto(factoryDAO.getPersonaDAOImpl().find(defuncionListDTO.getDefPersona()));
		defuncionVO.setConyuge(factoryDAO.getPersonaDAOImpl().find(defuncionListDTO.getDefConyugue()));
		defuncionVO.setDefuncion(eucaristiaFactoryDAO.getDefuncionDAOImpl().find(defuncionListDTO.getDefCodigo()));
				
		return defuncionVO;
	}
	
	
	
	
	@Override
	public TipoNichoDTO createOrUpdateTipoNicho(TipoNichoDTO tipoNichoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateTipoNicho");
		try {
		if(tipoNichoDTO.getTniCodigo()!=null)
			return eucaristiaFactoryDAO.getTipoNichoDAOImpl().update(tipoNichoDTO);
		else
			return eucaristiaFactoryDAO.getTipoNichoDAOImpl().create(tipoNichoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateTipoNicho {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
		
	@Override
	public List<TipoNichoDTO> buscarTipoNicho(TipoNichoDTO tipoNichoDTO) throws SeguridadesException {
		slf4jLogger.info("buscarTipoNicho");
		List<TipoNichoDTO> listTipoNicho = null;
		try {
			listTipoNicho =  eucaristiaFactoryDAO.getTipoNichoDAOImpl().obtenerTipoNicho(tipoNichoDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarTipoNicho {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarTipoNicho de la base de datos");
		}
		
		return listTipoNicho;
	}
	
	@Override
	public TipoNichoDTO obtenerTipoNichoPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerTipoNichoPorId");
		
		TipoNichoDTO tipoNicho=new TipoNichoDTO();
		
		tipoNicho = eucaristiaFactoryDAO.getTipoNichoDAOImpl().find(id);
		
		return tipoNicho;
	}
	
	
	@Override
	public NivelNichoDTO createOrUpdateNivelNicho(NivelNichoDTO nivelNichoDTO) throws SeguridadesException
	{
		slf4jLogger.info("createOrUpdateNivelNicho");
		try {
		if(nivelNichoDTO.getNniCodigo()!=null)
			return eucaristiaFactoryDAO.getNivelNichoDAOImpl().update(nivelNichoDTO);
		else
			return eucaristiaFactoryDAO.getNivelNichoDAOImpl().create(nivelNichoDTO);
		} catch (Exception e) {
			slf4jLogger.info("error al createOrUpdateNivelNicho {}", e.toString());
			throw new SeguridadesException(e);
		}
		
	}
	
		
	@Override
	public List<NivelNichoDTO> buscarNivelNicho(NivelNichoDTO nivelNichoDTO) throws SeguridadesException {
		slf4jLogger.info("buscarNivelNicho");
		List<NivelNichoDTO> listNivelNicho = null;
		try {
			listNivelNicho =  eucaristiaFactoryDAO.getNivelNichoDAOImpl().obtenerNivelNicho(nivelNichoDTO);
		} catch (Exception e) {
			slf4jLogger.info("Error al buscarNivelNicho {}", e.getMessage());
			throw new SeguridadesException("No se pudo obtener buscarNivelNicho de la base de datos");
		}
		
		return listNivelNicho;
	}
	
	@Override
	public NivelNichoDTO obtenerNivelNichoPorId(Integer id) throws SeguridadesException {
		slf4jLogger.info("obtenerNivelNichoPorId");
		
		NivelNichoDTO nivelNicho=new NivelNichoDTO();
		
		nivelNicho = eucaristiaFactoryDAO.getNivelNichoDAOImpl().find(id);
		
		return nivelNicho;
	}
	
}
